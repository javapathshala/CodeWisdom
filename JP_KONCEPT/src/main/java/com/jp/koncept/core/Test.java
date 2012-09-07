package com.jp.koncept.core;

public class Test {
	
		   public static void main(String[] args) {
		      Test inst_test = new Test();
		      int i1 = 2000;
		      int i2 = 2000;
		      int i3 = 2;
		      int i4 = 2;
		      Integer Ithree = new Integer(2); // 1
		      Integer Ifour = new Integer(2); // 2
		      System.out.println( Ithree == Ifour );
		      inst_test.method( i3 , i4 );
		      inst_test.method( i1 , i2 );
		   }
		   public void method( Integer i , Integer eye ) {
		      System.out.println(i == eye );
		   }
		
//	// static boolean foo(char c) {
//	// System.out.print(c);
//	// return true;
//	// }
//	static int i[];
//	int[] stillBorn = new int[] { 1, 2, 3, 4 };
//
//	void Test() {
//
//	}
//
//	public static void main(String[] argv) {
//		// int i = 0;
//		// for (foo('A'); foo('B') && (i < 2); foo('C')) {
//		// i++;
//		// foo('D');
//		// }
//		// System.out.println((Math.abs(Integer.MIN_VALUE)));
//		// System.out.println((Math.abs(-2147483647)));
//		// Integer b = null;
//		//
//		// System.out.println(b);
//		// System.out.println(b instanceof Object);
//		// // System.out.println(i[0]);
//		// if (~0 == 1) {
//		// System.out.println("true");
//		// } else {
//		// System.out.println("false");
//		// }
//
//		int i = 2, j = 3, k = 5;
//		String str = " + ", eq = " = ";
//		System.out.println(i + j + str + k + eq + (i + j + k));
//
//		boolean b[] = new boolean[2];
//		System.out.println(b[1]);
//		Byte b1 = new Byte("127");
//		String str1 = b1.toString();
//		System.out.println();
//		if (b1.toString().equals(b1.toString())) {
//			System.out.println("true");
//		} else {
//			System.out.println("fl");
//		}
//		System.out.println(b1.toString());
//		infiniteLoop();
//		Object i1 = (String)new Test().get(10);
//		System.out.println(i1.toString());
//	}
//
//	static void infiniteLoop() {
//		double d = -10 / 0.0;
//		double e = -11 / 0.0;
//		if (d == e) {
//			System.out.println(" d = e ");
//		} else
//			System.out.println(" d != e ");
//
//		for (int i = 0; i < 5; i++) {
//			System.out.println(i);
//		}
//		for (int i = 0; i < 5; ++i) {
//			System.out.println(i);
//		}
//		  int i = 10 ;
//          int j = 10 ;
//          boolean b = false ;
//
//          if( b = i == j ){
//        	  System.out.println("ddddd");
//          }
//          String id=null;
//          Object obj=null;
//          if(obj==id){
//        	  System.out.println("dsdadas");
//          }
//	}
//
//	public String get(int i) {
//		return "dimit";
//	}
}
