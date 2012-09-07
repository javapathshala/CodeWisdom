package com.jp.koncept.core;

public class QQQ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str="Error for batch element #8: DB2 SQL error: SQLCODE: -438, SQLSTATE: 80001, SQLERRMC: NAME ,dsferf    f      ,SQLSTATE: 80001";
		System.out.println(str);
		
		int index = str.indexOf("SQLERRMC");
		System.out.println(index);
		int newindex=index+10;
		System.out.println(newindex);
		String desire=str.substring(newindex);
		
		String finalStr="";
		int in=desire.indexOf(",");
		if(in>0){
			finalStr= desire.substring(0, in).trim();
		}else{
			finalStr=desire.trim();
		}
System.out.println(finalStr);
	}

}
