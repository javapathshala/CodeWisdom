/**
 * 
 */
package com.jp.design.pattern.facade;

/**
 * @author dimit.chadha
 * 
 */
public class RunFacadePattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StoreKeeperFacade keeper = new StoreKeeperFacade();
		RawMaterialGoods rawMaterialGoods = (RawMaterialGoods) keeper
				.getGoods("RawMaterials");
		rawMaterialGoods.getSolution();
		
		
		FinishedGoods finishedGoods = (FinishedGoods) keeper
				.getGoods("Finished");
		finishedGoods.getSolution();

	}
	
	

}
