/**
 * 
 */
package com.jp.design.pattern.facade;

/**
 * @author dimit.chadha
 * 
 */
public class FinishedGoodsStore implements Store {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tech.application.pattern.facade.Store#getGoods()
	 */
	
	public Goods getGoods() {
		FinishedGoods finishedGoods = new FinishedGoods();
		return finishedGoods;

	}

}
