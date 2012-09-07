package com.jp.koncept.core;

class Just {
	int a = 10 ;

	Just ( ) {
		call ( ) ;
	}

	void call ( ) {
		System . out . print( "a = " + a + " ") ;
	}
};

class Q05 extends Just {
	int b = 16 ;

	Q05 ( ) {
		call ( ) ;
	}

	void call ( ) {
		System.out.print( " b = " + b + " " ) ;
	}
	public static void main ( String args [ ] ) {
		new Q05 ( ) ;
		new Just();
	}
}