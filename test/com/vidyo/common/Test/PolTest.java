package com.vidyo.common.Test;

import java.util.Date;
import java.util.Random;




class A{
	
	public void method1(){
		System.out.println("A1");
	}
	
	public void method2(){
		System.out.println("A2");
	}
	
}

class B extends A{
	
	public void method1(){
		System.out.println("B1");
	}
	
	public void method3(){
		System.out.println("B3");
	}
	
}


public class PolTest {
	
	public static void main(String args[]){
		
		Test1 test1 = new Test1();
		System.out.println(test1.getMyname().toLowerCase());
		
	}

}
