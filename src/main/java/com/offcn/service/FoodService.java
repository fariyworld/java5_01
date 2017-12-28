package com.offcn.service;

import java.util.List;
import java.util.Map;

import com.offcn.domain.Food;
import com.offcn.domain.QueryVO;

public interface FoodService {

	public boolean grabFoodData(QueryVO queryVO);

	public List<Food> getListByFoodName(String foodName);
	
	public Map<Integer, Map<Integer, Double>> getYearChart(QueryVO queryVO);
	
	public Map<Integer, Map<Integer, Double>> getMonthChart(QueryVO queryVO);
	
	public boolean saveChartToPDF(String baseData, QueryVO queryVO);
	
	public String getLastTime();

	public Integer insertBatches(List<Food> foodList);
	
}
