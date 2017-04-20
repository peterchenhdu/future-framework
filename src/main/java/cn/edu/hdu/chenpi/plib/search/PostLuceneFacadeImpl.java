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
 * Created on Mar 11, 2005 12:01:47 PM
 * The JForum Project
 * http://www.jforum.net
 */
package net.jforum.search;

import net.jforum.entities.Post;
import net.jforum.search.constant.SearchFields;
import net.jforum.search.dto.Indexable;
import net.jforum.search.dto.SearchArgs;
import net.jforum.search.dto.SearchResult;
import net.jforum.search.util.DateTimeUtil;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

/**
 * 外观类
 * 
 * @author Rafael Steil
 * @version $Id: SearchFacade.java,v 1.8 2007/09/09 22:53:35 rafaelsteil Exp $
 */
public class PostLuceneFacadeImpl implements IDocCreateable<Post>
{
    private  ILuceneManager luceneManager;
    private static Logger logger = Logger.getLogger(PostLuceneFacadeImpl.class);

    public  void init()
    {

        //这里 可以选择使用哪个ISearchManager作为实现者(e.g 从配置文件读取...)
        luceneManager = new LuceneManager();

        luceneManager.init();
        logger.info("init search Manager success.");
    }

    public  void create(Post post)
    {
        

        luceneManager.create(this.createDocument(post));

    }

    public  void update(Post post)
    {

        luceneManager.update(this.createDocument(post),
            SearchFields.Keyword.POST_ID, String.valueOf(post.getId()));

    }

    public  SearchResult<Post> search(SearchArgs args)
    {
        return luceneManager.search(args);
    }

    public  void delete(Post p)
    {

        this.luceneManager.delete(SearchFields.Keyword.POST_ID,String.valueOf(p.getId()));

    }

    public  ILuceneManager getLuceneManager()
    {
        return this.luceneManager;
    }
    
    public  Document createDocument(Post post)
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
