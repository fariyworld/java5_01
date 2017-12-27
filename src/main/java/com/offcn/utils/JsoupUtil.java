package com.offcn.utils;

import java.lang.reflect.Method;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.offcn.domain.Food;
import com.offcn.domain.QueryVO;
import com.offcn.enums.FoodProperty;

public class JsoupUtil {
	
	private static String type = "";

	/**
	 * 
	 * @TODO:  处理content 并把下一页的链接返回给调用者
	 * @param content
	 * @return QueryVO
	 */
	public static QueryVO parseContent(String content,QueryVO queryVO){
		
		type = queryVO.getType();
		
		Document document = Jsoup.parse(content);
		
		int flag = queryVO.getFlag();
		
		if( flag == 1){
			
			Element link = document.select("a[title=尾页]").first();
			queryVO.setGrabUrl(link.attr("href"));
			
			flag ++;
			
			queryVO.setFlag(flag);
		}
		
		List<Food> foodList = queryVO.getFoodList();//之前处理的List集合，把新的处理结果也放进去
		
		Elements trEle = document.select(".tr_1").first().siblingElements();//除了第一个其余的所有的tr
		
		for (Element tr : trEle) {//有用的tr
			
			int lastChildNodeIndex = tr.childNodes().size() - 1;
			
			Food food = new Food();
			
			for (int i = 0; i < lastChildNodeIndex ; i++) {
				
				Object tdVal = tr.child(i).text();//Food的对应属性值 string
				
				//
				BeanHandler(food, tdVal, i);
			}
			//处理类别
			food.setType(type);
			
			foodList.add(food);
		}
		
		queryVO.setFoodList(foodList);
		
		return queryVO;
	}
	
	
	public static QueryVO parseContentAndUpdate(String content,QueryVO queryVO,String lastTime){
		
		type = queryVO.getType();
		
		Document document = Jsoup.parse(content);
		
		int flag = queryVO.getFlag();
		
		if( flag == 1){
			
			Element link = document.select("a[title=尾页]").first();
			queryVO.setGrabUrl(link.attr("href"));
			
			flag ++;
			
			queryVO.setFlag(flag);
		}
		
		List<Food> foodList = queryVO.getFoodList();//之前处理的List集合，把新的处理结果也放进去
		
		Elements trEle = document.select(".tr_1").first().siblingElements();//除了第一个其余的所有的tr
		
		for (Element tr : trEle) {//有用的tr
			
			int lastChildNodeIndex = tr.childNodes().size() - 1;
			
			Food food = new Food();
			
			for (int i = 0; i < lastChildNodeIndex ; i++) {
				
				if( tr.child(i).text().equals(lastTime)){//不需要更新，优化：更新时间和数据库数据的时间用before
					
					System.out.println("数据已更新完成");
					
					queryVO.setUpdateFlag(false);
					
					return queryVO;
					
				}else{
					
					Object tdVal = tr.child(i).text();//Food的对应属性值 string
					
					BeanHandler(food, tdVal, i);
				}
			}
			//处理类别
			food.setType(type);
			
			foodList.add(food);
		}
		
		queryVO.setFoodList(foodList);
		
		return queryVO;
	}
	
	
	
	/**
	 * 
	 * @TODO: 把每个td的值赋给JavaBean
	 * @param t
	 * @param cellValue
	 * @param num
	 * @param fieldNameMap
	 */
	public static <T> void BeanHandler(T t, Object cellValue, int num) {

		String fielfName = getFoodProperty(num);

		try {

			Method getMethod = t.getClass().getMethod("get" + initcap(fielfName));

			Method setMethod = t.getClass().getMethod("set" + initcap(fielfName), getMethod.getReturnType());

			setMethod.invoke(t, cellValue);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static String getFoodProperty(Integer index){
		
		String fielfName = null;
		
		for (FoodProperty foodProperty : FoodProperty.values()) {
			
			if(foodProperty.ordinal() == index){
				
				fielfName =  foodProperty.name();
			}
		}
		
		return fielfName;
	}
	
	/**
	 * 
	 * @描述： 首字母大写
	 * 
	 * @param str
	 * @return String
	 */
	public static String initcap(String str) {

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
}
