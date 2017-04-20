package net.jforum.search.core;

import net.jforum.search.event.DocEvent;

/**
 * doc事件变化监听接口
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月19日 下午8:50:21
 * @see       
 * @since forum4j V1.0.0
 */
public interface IDocumentListener<E extends DocEvent>
{
    /**
     * DocEvent发生时如何处理(add/del .eg)
     * 
     * @param event
     */
	public void onDocumentEvent(E event);
}
