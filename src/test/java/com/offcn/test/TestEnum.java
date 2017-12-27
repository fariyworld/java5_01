package com.offcn.test;

import org.junit.Test;

import com.offcn.enums.Constant;
import com.offcn.enums.FoodProperty;

public class TestEnum {
	
	@Test
	public void test1(){
		
		for (FoodProperty foodProperty : FoodProperty.values()) {
			
			if(foodProperty.ordinal() == 0){
				
				System.out.println(foodProperty.name());
			}
		}
		
	}
	
	@Test
	public void test2(){
		
		System.out.println(Constant.pageSize.getValue());
	}

}
