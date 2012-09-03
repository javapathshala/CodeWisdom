package com.jp.design.pattern.facade;

public class RawMaterialStore {

	public RawMaterialGoods getGoods() {
		
		return new RawMaterialGoods();
	}

}
