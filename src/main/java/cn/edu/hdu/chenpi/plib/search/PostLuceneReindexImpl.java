/*
 * Copyright (c) JForum Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following  disclaimer.
 * 2)  Redistributions in binary form must reproduce the 
 * above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 * 3) Neither the name of "Rafael Steil" nor 
 * the names of its contributors may be used to endorse 
 * or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT 
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 * 
 * Created on 06/08/2007 15:20:23
 * 
 * The JForum Project
 * http://www.jforum.net
 */
package net.jforum.search;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.jforum.ConfigLoader;
import net.jforum.JForumExecutionContext;
import net.jforum.dao.DataAccessDriver;
import net.jforum.dao.LuceneDAO;
import net.jforum.entities.Post;
import net.jforum.exceptions.ForumException;
import net.jforum.search.constant.Config;
import net.jforum.search.constant.SearchFields;
import net.jforum.search.core.LuceneIndexer;
import net.jforum.search.core.LuceneSearch;
import net.jforum.search.dto.LuceneReindexArgs;
import net.jforum.search.util.DateTimeUtil;
import net.jforum.util.preferences.ConfigKeys;
import net.jforum.util.preferences.SystemGlobals;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.search.IndexSearcher;

/**
 * @author Rafael Steil
 * @version $Id: LuceneReindexer.java,v 1.6 2007/10/17 04:36:13 rafaelsteil Exp $
 */
public class PostLuceneReindexImpl implements IDocCreateable<Post>
{
    private static final Logger logger = Logger.getLogger(PostLuceneReindexImpl.class);

	private LuceneSettings settings;
	private LuceneReindexArgs args;
	private boolean recreate;
	
	public PostLuceneReindexImpl(LuceneSettings settings, LuceneReindexArgs args, boolean recreate)
	{
		this.args = args;
		this.recreate = recreate;
		this.settings = settings;
	}
	
	public void startProcess()
	{
	    logger.info("reindex start.");
		this.reindex();
		logger.info("reindex end.");
	}
	
	public void startBackgroundProcess()
	{
		Runnable indexingJob = new Runnable() {		
			public void run() {
				reindex();
			}
		};
		
		SystemGlobals.setValue(ConfigKeys.LUCENE_CURRENTLY_INDEXING, "1");
		
		Thread thread = new Thread(indexingJob);
		thread.start();
	}

	private void reindex()
	{
		try {
			if (recreate) {
				this.settings.createIndexDirectory(Config.LUCENE_INDEX_WRITE_PATH);
			}
		}
		catch (IOException e) {
			throw new ForumException(e);
		}
		
		LuceneDAO dao = DataAccessDriver.getInstance().newLuceneDAO();
		
		IndexSearcher searcher = null;
		LuceneSearch luceneSearch = ConfigLoader.postLuceneFacade.getLuceneManager().getLuceneSearch();
		LuceneIndexer luceneIndexer = ConfigLoader.postLuceneFacade.getLuceneManager().getLuceneIndexer();
		
		int fetchCount = Config.LUCENE_INDEXER_DB_FETCH_COUNT;
		
		try {
			if (!recreate) {
				searcher = new IndexSearcher(this.settings.getDirectory());
			}
			
			boolean hasMorePosts = true;
			long processStart = System.currentTimeMillis();
			
			int firstPostId = args.filterByMessage()
				? args.getFirstPostId()
				: dao.firstPostIdByDate(args.getFromDate());
				
			if (args.filterByMessage()) {
				int dbFirstPostId = dao.firstPostId();
				
				if (firstPostId < dbFirstPostId) {
					firstPostId = dbFirstPostId;
				}
			}
				
			int lastPostId = args.filterByMessage()
				? args.getLastPostId()
				: dao.lastPostIdByDate(args.getToDate());
				
			int counter = 1;
			int indexTotal = 0;
			long indexRangeStart = System.currentTimeMillis();
			
			while (hasMorePosts) {
				boolean contextFinished = false;
				
				int toPostId = firstPostId + fetchCount < lastPostId
					? firstPostId + fetchCount
					: lastPostId;
				
				try {
					JForumExecutionContext ex = JForumExecutionContext.get();
					JForumExecutionContext.set(ex);
					
					List<Post> l = dao.getPostsToIndex(firstPostId, toPostId);
					
					if (counter >= 5000) {
						long end = System.currentTimeMillis();
						System.out.println("Indexed ~5000 documents in " 
							+ (end - indexRangeStart) + " ms (" + indexTotal + " so far)");
						indexRangeStart = end;
						counter = 0;
					}
					
					JForumExecutionContext.finish();
					contextFinished = true;
					
					for (Iterator<Post> iter = l.iterator(); iter.hasNext(); ) {
						if ("0".equals(SystemGlobals.getValue(ConfigKeys.LUCENE_CURRENTLY_INDEXING))) {
							hasMorePosts = false;
							break;
						}
						
						Post post = iter.next();
						
						if (!recreate && args.avoidDuplicatedRecords()) {
							if (luceneSearch.findDocumentByPostId(post.getId()) != null) {
								continue;
							}
						}
						
						luceneIndexer.batchCreate(this.createDocument(post));
						
						counter++;
						indexTotal++;
					}
					
					firstPostId += fetchCount;
					hasMorePosts = hasMorePosts && l.size() > 0;
				}
				finally {
					if (!contextFinished) {
						JForumExecutionContext.finish();
					}
				}
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("**** Total: " + (end - processStart) + " ms");
		}
		catch (IOException e) {
			throw new ForumException(e);
		}
		finally {
			SystemGlobals.setValue(ConfigKeys.LUCENE_CURRENTLY_INDEXING, "0");

			luceneIndexer.flushRAMDirectory();
			
			if (searcher != null) {
				try { searcher.close(); }
				catch (Exception e) {}
			}
		}
	}

    /**
     * 
     * @see net.jforum.search.IDocCreateable#createDocument(java.lang.Object)
     * @param t
     * @return
     */
    @Override
    public Document createDocument(Post post)
    {
        Document d = new Document();

        d.add(new Field(SearchFields.Keyword.POST_ID, String.valueOf(post.getId()), Store.YES,
            Index.UN_TOKENIZED));
        d.add(new Field(SearchFields.Keyword.FORUM_ID, String.valueOf(post.getForumId()), Store.YES,
            Index.UN_TOKENIZED));
        d.add(new Field(SearchFields.Keyword.TOPIC_ID, String.valueOf(post.getTopicId()), Store.YES,
            Index.UN_TOKENIZED));
        d.add(new Field(SearchFields.Keyword.USER_ID, String.valueOf(post.getUserId()), Store.YES,
            Index.UN_TOKENIZED));
        d.add(new Field(SearchFields.Keyword.DATE, DateTimeUtil.formatDateTime(post.getTime()),
            Store.YES, Index.UN_TOKENIZED));

        // 这里我们将标题和正文合在一起构建Field，不过当真正获取的时候，会分别从数据库获取
        d.add(new Field(SearchFields.Indexed.CONTENTS, post.getSubject() + " " + post.getText(),
            Store.NO, Index.TOKENIZED));

        return d;
    }
}
