/**
 * 
 */
package com.jp.design.pattern.prototype;

/**
 * @author dimit.chadha
 * 
 */
public class PrototypeRun {
	private PrototypeFactory prot;

	public PrototypeRun(PrototypeFactory prot) {
		this.prot = prot;
	}

	public PrototypeFactory makeCopy() throws CloneNotSupportedException {
		return this.prot.clone();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PrototypeFactory tempExample = null;
			int num = 1000;
			PrototypeFactory prot = new PrototypeImpl(1000);
			PrototypeRun cm = new PrototypeRun(prot);
			for (int i = 0; i < 10; i++) {
				System.out
						.println("###########################################");
				prot.prototypeFactory(i * num);
				prot.printValue();

				tempExample = cm.makeCopy();
				tempExample.prototypeFactory(i * num);
				tempExample.printValue();
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

}
