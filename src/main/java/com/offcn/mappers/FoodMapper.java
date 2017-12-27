package com.offcn.mappers;

import java.util.List;

import com.offcn.domain.Food;
import com.offcn.domain.QueryVO;

public interface FoodMapper {
	
	Integer batchInsert(List<Food> foodList);

	List<Food> getListByFoodName(String foodName);
	
	List<Food> getListByFoodNameAndDate(QueryVO queryVO);
	
	String getLastTime();
}
