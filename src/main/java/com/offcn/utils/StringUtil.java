package com.offcn.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static String getPrefixUrl(String url) {

		return url.substring(0, url.lastIndexOf("/"));
	}

	public static String getSuffixUrl(String url) {

		return url.substring(url.lastIndexOf("."));
	}

	public static Integer getCycleCount(String url) {

		return Integer.parseInt(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".")));
	}

	public static String getSelectCondition(String foodName) {

		return "%" + foodName + "%";
	}

	public static Integer getYear(String publishDate) {

		return Integer.parseInt(publishDate.substring(0, publishDate.indexOf("-")));
	}

	public static Integer getMonth(String publishDate) {

		return Integer.parseInt(publishDate.substring(publishDate.indexOf("-") + 1, publishDate.lastIndexOf("-")));
	}

	public static String[] getDateArr(String date) {

		String[] dateArr = new String[4];

		dateArr[0] = getTongBiDate1(getAllDate(date));
		dateArr[1] = getTongBiDate2(getAllDate(date));
		dateArr[2] = getHuanBiDate1(getAllDate(date));
		dateArr[3] = getHuanBiDate2(getAllDate(date));

		return dateArr;
	}

	public static String getTongBiDate1(String date){//2017-12-01
		
		Integer month = getMonth(date);
		
		if( month == 12 ){
			
			return date + "," + String.valueOf(getYear(date)+1) + "-01-01";
		
		}else{
			
			String newMonth = String.valueOf(month).length() > 1 ? String.valueOf(month+1) : String.valueOf("0"+(month+1));
			
			return date + "," + String.valueOf(getYear(date)) + "-" + newMonth + "-01" ;
		}
	}

	public static String getTongBiDate2(String date) {
		
		Integer year = getYear(date);
		
		String newDate = String.valueOf(year - 1) +  getMonthAndDay(date);
		
		newDate += ","+ String.valueOf(year-1);

		return newDate;
	}

	public static String getHuanBiDate1(String date) {

		return null;
	}

	public static String getHuanBiDate2(String date) {

		return null;
	}

	public static String getAllDate(String date) {

		return date + "-01";
	}
	
	public static String getMonthAndDay(String date){
		
		return date.substring(date.indexOf("-"));
	}
	
	
	public static String getStrByHtml(String path){
		
		StringBuffer sb = new StringBuffer();
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			
			String line = null;
			
			
			while( (line = br.readLine()) != null ){
				
				sb.append(line);
				sb.append("\n");
			}
			
			br.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		}
		
		return sb.toString();
	}
	
	
	public static String getCurrentDateWithStr(){
		
		return new SimpleDateFormat("yyyy-MM--dd").format(new Date());
	}

}
