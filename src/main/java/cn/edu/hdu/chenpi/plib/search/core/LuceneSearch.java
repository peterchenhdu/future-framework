package net.jforum.search.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.jforum.entities.Post;
import net.jforum.exceptions.ForumException;
import net.jforum.search.ILuceneResultCollector;
import net.jforum.search.LuceneSettings;
import net.jforum.search.SearchException;
import net.jforum.search.constant.SearchFields;
import net.jforum.search.dto.SearchArgs;
import net.jforum.search.dto.SearchResult;
import net.jforum.search.event.DocAddedEvent;
import net.jforum.search.event.DocDeleteEvent;
import net.jforum.search.event.DocEvent;
import net.jforum.search.util.DateTimeUtil;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;

/**
 * 提供搜索功能
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月19日 下午9:05:03
 * @see       
 * @since forum4j V1.0.0
 */
public class LuceneSearch implements IDocumentListener<DocEvent>
{
    private static final Logger logger = Logger.getLogger(LuceneSearch.class);

    private IndexSearcher search;
    private LuceneSettings settings;
    private ILuceneResultCollector contentCollector;

    public LuceneSearch(LuceneSettings settings, ILuceneResultCollector contentCollector)
    {
        this.settings = settings;
        this.contentCollector = contentCollector;

        this.openSearch();
    }

    /**
     * @see net.jforum.dao.SearchDAO#search(net.jforum.search.dto.SearchArgs)
     */
    public SearchResult<Post> search(SearchArgs args)
    {
        return this.performSearch(args, this.contentCollector, null);
    }

    /**
     * 根据Post id查找
     * 
     * @param postId
     * @return
     */
    public Document findDocumentByPostId(int postId)
    {
        Document doc = null;

        try
        {
            Hits hit = this.search.search(
                new TermQuery(new Term(SearchFields.Keyword.POST_ID, String.valueOf(postId))));

            if (hit.length() > 0)
            {
                doc = hit.doc(0);
            }
        }
        catch (IOException e)
        {
            throw new SearchException(e);
        }

        return doc;
    }

    /**
     * 根据SearchArgs查找
     * 
     * @param args
     * @param resultCollector
     * @param filter
     * @return
     */
    private String[] performSearch(SearchArgs args,
        ILuceneResultCollector resultCollector, Filter filter)
    {
        String[] result;

        try
        {
            StringBuffer criteria = new StringBuffer(256);

            this.filterByForum(args, criteria);
            this.filterByKeywords(args, criteria);
            this.filterByDateRange(args, criteria);

            if (criteria.length() == 0)
            {
                logger.debug("query criteria is blank");
                return new String[]{};
            }

            Query query = new QueryParser("", new StandardAnalyzer()).parse(criteria.toString());

            if (logger.isDebugEnabled())
            {
                logger.debug("Generated query: " + query);
            }

            Hits hits = filter == null ? this.search.search(query, this.getSorter(args))
                : this.search.search(query, filter, this.getSorter(args));

            if (hits != null && hits.length() > 0)
            {
                result = new SearchResult<Post>(resultCollector.collect(args, hits, query),
                    hits.length());
            }
            else
            {
                result = new String[]{};
            }
        }
        catch (Exception e)
        {
            throw new SearchException(e);
        }

        return result;
    }

    private Sort getSorter(SearchArgs args)
    {
        Sort sort = Sort.RELEVANCE;

        if ("time".equals(args.getOrderBy()))
        {
            sort = new Sort(SearchFields.Keyword.POST_ID, "DESC".equals(args.getOrderDir()));
        }

        return sort;
    }

    private void filterByDateRange(SearchArgs args, StringBuffer criteria)
    {
        if (args.getFromDate() != null)
        {
            criteria.append('(').append(SearchFields.Keyword.DATE).append(": [")
                .append(DateTimeUtil.formatDateTime(args.getFromDate())).append(" TO ")
                .append(DateTimeUtil.formatDateTime(args.getToDate())).append(']').append(')');
        }
    }

    private void filterByKeywords(SearchArgs args, StringBuffer criteria)
    {
        String[] keywords = this.analyzeKeywords(args.rawKeywords());

        for (int i = 0; i < keywords.length; i++)
        {
            if (args.shouldMatchAllKeywords())
            {
                criteria.append(" +");
            }

            criteria.append('(').append(SearchFields.Indexed.CONTENTS).append(':')
                .append(QueryParser.escape(keywords[i])).append(") ");
        }
    }

    private void filterByForum(SearchArgs args, StringBuffer criteria)
    {
        if (args.getForumId() > 0)
        {
            criteria.append("+(").append(SearchFields.Keyword.FORUM_ID).append(':')
                .append(args.getForumId()).append(") ");
        }
    }

    private String[] analyzeKeywords(String contents)
    {
        try
        {
            TokenStream stream = this.settings.getAnalyzer().tokenStream("contents",
                new StringReader(contents));
            List<String> tokens = new ArrayList<String>();

            while (true)
            {
                Token token = stream.next();

                if (token == null)
                {
                    break;
                }

                tokens.add(token.termText());
            }

            return (String[]) tokens.toArray(new String[0]);
        }
        catch (IOException e)
        {
            throw new SearchException(e);
        }
    }

    private void openSearch()
    {
        try
        {
            this.search = new IndexSearcher(this.settings.getDirectory());
        }
        catch (IOException e)
        {
            throw new SearchException(e.toString(), e);
        }
    }

    private void reOpen() throws IOException{
        this.search.close();
        this.openSearch();
    }
    
    /**
     * 
     * @see net.jforum.search.core.IDocumentListener#onDocumentEvent(net.jforum.search.event.DocEvent)
     * @param event
     */
    @Override
    public void onDocumentEvent(DocEvent event)
    {
        logger.info("docEvent:" + event);
        try
        {
            if(event instanceof DocAddedEvent){
                logger.info("DocAddedEvent");
                this.reOpen();
            }else if(event instanceof DocDeleteEvent){
                logger.info("DocDeleteEvent");
                this.reOpen();
            }else{
                logger.warn("不识别的事件:" + event);
            }

        }
        catch (Exception e)
        {
            throw new SearchException(e);
        }
        

    }
    private String[] collect(SearchArgs args, Hits hits)
    {
        try {
            String[] postIds = new String[Math.min(args.fetchCount(), hits.length())];
            
            for (int docIndex = args.startFrom(), i = 0; 
                docIndex < args.startFrom() + args.fetchCount() && docIndex < hits.length(); 
                docIndex++, i++) {
                Document doc = hits.doc(docIndex);
                postIds[i] = doc.get(SearchFields.Keyword.POST_ID);
            }
            
            return postIds;
        }
        catch (Exception e) {
            throw new ForumException(e.toString(), e);
        }
    }
}
