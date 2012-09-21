package com.ikmnet;

import java.util.Arrays;
import java.util.Collections;


public class MyTestHarness implements Comparable<MyTestHarness> {
	public String myname;
	public MyTestHarness(String myname){
		this.myname=myname;
	}
	public static void main(String args[]){

		String s = String.valueOf(1);
		MyTestHarness[] myArray = new MyTestHarness[4];
		
		myArray[0]= new MyTestHarness("D");
		myArray[1]= new MyTestHarness("B");
		myArray[2]= new MyTestHarness("A");
		myArray[3]= new MyTestHarness("C");
		
		Arrays.sort(myArray);
		
		for(int i=0;i<4;i++){
			System.out.println(myArray[i].myname);
		}
		
		
	}
	@Override
	public int compareTo(MyTestHarness o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
