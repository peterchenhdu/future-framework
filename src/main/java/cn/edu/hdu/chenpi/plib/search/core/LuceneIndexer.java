
package net.jforum.search.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.jforum.search.LuceneSettings;
import net.jforum.search.SearchException;
import net.jforum.search.constant.Config;
import net.jforum.search.event.DocAddedEvent;
import net.jforum.search.event.DocDeleteEvent;
import net.jforum.search.event.DocEvent;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * 
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月19日 下午9:48:58
 * @see
 * @since forum4j V1.0.0
 */
public class LuceneIndexer
{
    private static final Logger logger = Logger.getLogger(LuceneIndexer.class);
    /**
     * 互斥变量
     */
    private static final Object MUTEX = new Object();

    /**
     * analyzer & Directory
     */
    private LuceneSettings settings;
    /**
     * 内存Directory
     */
    private Directory ramDirectory;
    private IndexWriter indexWriter;
    /**
     * 在flush到硬盘前内存中doc的最大数量
     */
    private int ramNumDocs;

    private static DocEvent docAddEvent = new DocAddedEvent();
    private static DocEvent docDeleteEvent = new DocDeleteEvent();
    // 订阅者
    private List<IDocumentListener<DocEvent>> docListenerList = new ArrayList<IDocumentListener<DocEvent>>();

    public LuceneIndexer(LuceneSettings settings)
    {
        this.settings = settings;
        this.createIndexWriter();
    }

    /**
     * 创建IndexWriter
     */
    private void createIndexWriter()
    {
        try
        {
            if (this.indexWriter != null)
            {
                this.indexWriter.close();
            }

            this.ramDirectory = new RAMDirectory();
            this.indexWriter = new IndexWriter(this.ramDirectory, this.settings.getAnalyzer(),
                true);
            this.ramNumDocs = Config.LUCENE_INDEXER_RAM_NUMDOCS;
        }
        catch (IOException e)
        {
            throw new SearchException(e);
        }
    }

    /**
     * 批量建立索引，doc数目大于ramNumDocs才写入文件
     * 
     * @param post
     */
    public void batchCreate(Document document)
    {
        synchronized (MUTEX)
        {
            try
            {
                this.indexWriter.addDocument(document);
                if (this.indexWriter.docCount() >= this.ramNumDocs)
                {
                    this.flushRAMDirectory();
                }
            }
            catch (IOException e)
            {
                throw new SearchException(e);
            }
        }
    }

    /**
     * 将RAM中的doc flush到文件中
     */
    public void flushRAMDirectory()
    {
        synchronized (MUTEX)
        {
            IndexWriter writer = null;

            try
            {
                writer = new IndexWriter(this.settings.getDirectory(), this.settings.getAnalyzer());
                writer.addIndexes(new Directory[]
                { this.ramDirectory });
                writer.optimize();

                this.createIndexWriter();
            }
            catch (IOException e)
            {
                throw new SearchException(e);
            }
            finally
            {
                if (writer != null)
                {
                    try
                    {
                        writer.flush();
                        writer.close();

                        this.notify(docAddEvent);
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
    }

    /**
     * 单个建立
     * 
     * @param post
     */
    public void create(Document document)
    {
        synchronized (MUTEX)
        {
            IndexWriter writer = null;

            try
            {
                writer = new IndexWriter(this.settings.getDirectory(), this.settings.getAnalyzer());

                writer.addDocument(document);

                this.optimize(writer);

                if (logger.isDebugEnabled())
                {
                    logger.debug("Indexed " + document);
                }
            }
            catch (Exception e)
            {
                logger.error(e.toString(), e);
            }
            finally
            {
                if (writer != null)
                {
                    try
                    {
                        writer.flush();
                        writer.close();

                        this.notify(docAddEvent);
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
    }

    public void update(Document document, String key, String value)
    {
        if (this.performDelete(key, value))
        {
            this.create(document);
        }
    }

    private void optimize(IndexWriter writer) throws Exception
    {
        if (writer.docCount() % 100 == 0)
        {
            if (logger.isInfoEnabled())
            {
                logger.info(
                    "Optimizing indexes. Current number of documents is " + writer.docCount());
            }

            writer.optimize();

            if (logger.isDebugEnabled())
            {
                logger.debug("Indexes optimized");
            }
        }
    }

    /**
     * 添加订阅者
     * 
     * @param docListener
     */
    public void addNewDocListener(IDocumentListener<DocEvent> docListener)
    {
        this.docListenerList.add(docListener);
    }

    // 观察者模式，通知订阅者更新
    private void notify(DocEvent docAddedEvent)
    {
        for (Iterator<IDocumentListener<DocEvent>> iter = this.docListenerList.iterator(); iter
            .hasNext();)
        {
            iter.next().onDocumentEvent(docAddedEvent);
        }
    }

    public void delete(String key, String value)
    {
        this.performDelete(key, value);
    }

    private boolean performDelete(String key, String value)
    {
        synchronized (MUTEX)
        {
            IndexReader reader = null;
            boolean status = false;

            try
            {
                reader = IndexReader.open(this.settings.getDirectory());
                reader.deleteDocuments(new Term(key, value));
                status = true;
            }
            catch (IOException e)
            {
                logger.error(e.toString(), e);
            }
            finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                        this.notify(docDeleteEvent);
                    }
                    catch (Exception e)
                    {
                    }
                }
            }

            return status;
        }
    }
}
