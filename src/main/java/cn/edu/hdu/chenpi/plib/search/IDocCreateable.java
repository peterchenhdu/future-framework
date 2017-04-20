/*
 * File Name: ILuceneFacade.java
 * Description: 
 * Author: PiChen
 * Create Date: 2017年4月19日

 */
package net.jforum.search;

import org.apache.lucene.document.Document;

/**
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月19日 下午9:55:45
 * @see       
 * @since forum4j V1.0.0
 */

public interface IDocCreateable<T> 
{

    public  Document createDocument(T t);
}
