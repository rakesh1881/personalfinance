package com.personalfinance.common;

public class Test {
	public static void main(String[] args) {
		long a =1l+4l*(1804289383l*2l);
		long b = (long) Math.sqrt(a)-1;
		if(b/2<0)
		System.out.println(b/2*-1);
		else
		System.out.println(b/2);
		
	}
	
}
