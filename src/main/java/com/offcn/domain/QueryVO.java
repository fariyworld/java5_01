package com.offcn.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryVO {

	private String result;// ajax返回值
	private String grabUrl;// 抓取地址
	private String type;
	private String foodName;
	private String startTime;
	private String endTime;
	private Integer flag = 1;
	private String date;
	private List<Food> foodList = new ArrayList<Food>();// 页面处理结果放到List

	private boolean updateFlag = true;

	private Map<Integer, Map<Integer, Double>> yearChartMap = new HashMap<Integer, Map<Integer, Double>>();

	public QueryVO() {
		super();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getGrabUrl() {
		return grabUrl;
	}

	public void setGrabUrl(String grabUrl) {
		this.grabUrl = grabUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public Map<Integer, Map<Integer, Double>> getYearChartMap() {
		return yearChartMap;
	}

	public void setYearChartMap(Map<Integer, Map<Integer, Double>> yearChartMap) {
		this.yearChartMap = yearChartMap;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

}
