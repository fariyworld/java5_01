package com.offcn.test;

import org.junit.Test;

import com.offcn.utils.StringUtil;

public class TestStringUtil {

	@Test
	public void test1() {

		/*
		 * System.out.println(StringUtil.getYear("2017-12-11"));
		 * System.out.println(StringUtil.getMonth("2017-12-11"));
		 */
		System.out.println(StringUtil.getTongBiDate1("2017-12-01"));
	}
}
