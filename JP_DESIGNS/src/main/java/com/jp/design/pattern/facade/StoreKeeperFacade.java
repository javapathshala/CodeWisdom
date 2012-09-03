/**
 * 
 */
package com.jp.design.pattern.facade;

/**
 * @author dimit.chadha
 * 
 */
public class StoreKeeperFacade {
	public Goods getGoods(String goodsType) {
		if (goodsType.equals("Packaging")) {
			PackingMaterialStore store = new PackingMaterialStore();
			PackingMaterialGoods packingMaterialGoods = (PackingMaterialGoods) store
					.getGoods();
			return packingMaterialGoods;
		} else if (goodsType.equals("Finished")) {
			FinishedGoodsStore store = new FinishedGoodsStore();
			FinishedGoods finishedGoods = (FinishedGoods) store.getGoods();
			return finishedGoods;
		} else {
			RawMaterialStore store = new RawMaterialStore();
			RawMaterialGoods rawMaterialGoods = (RawMaterialGoods) store
					.getGoods();
			return rawMaterialGoods;
		}

	}
}
