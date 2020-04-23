package com.czxy.utils;

public class RandomCode {


	public  static  String getCode(){
		int code = (int)((Math.random()*9+1)*100000);
		return code+"";
	}

	public static void main(String[] args) {
		System.out.println(getCode());
	}
}
