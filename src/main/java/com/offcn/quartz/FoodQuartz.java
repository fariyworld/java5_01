package com.offcn.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.offcn.domain.Food;
import com.offcn.domain.QueryVO;
import com.offcn.enums.FoodType;
import com.offcn.service.FoodService;
import com.offcn.utils.HttpClientUtil;
import com.offcn.utils.JsoupUtil;
import com.offcn.utils.StringUtil;

public class FoodQuartz {
	
	@Autowired
	private FoodService foodService;
	
	private String lastTime;

	/**
	 * 
	 * @TODO: 更新数据库food表，并发送邮件
	 */
	public void updateDBAndSendMail() {

		//去数据库查看最新的数据日期
		lastTime = foodService.getLastTime();
		System.out.println("数据最近的时间是："+lastTime);
		
		//和当前的系统时间的xxxx--xx--xx比较
		
		if( lastTime.equals(StringUtil.getCurrentDateWithStr()) ){
			
			System.out.println("数据已更新,停止作业");
			
			return;
			
		}else{
			
			System.out.println("执行作业");
			//抓取数据，查看是否更新
		}
		
		String prefixUrl = "http://www.xinfadi.com.cn/marketanalysis/";
				
		String suffixUrl = "/list/1.shtml";
		
		String url = "";
		
		String content = "";
		
		String prefixUrl1 = "";
		String suffixUrl1 = "";
		
		QueryVO queryVO = new QueryVO();
		
		for (int i = 1; i < 6; i++) {
			
			url = prefixUrl + i + suffixUrl;
			
			prefixUrl1 = StringUtil.getPrefixUrl(url);
			
			suffixUrl1 = StringUtil.getSuffixUrl(url);
			
			queryVO.setType(getFoodType(i-1));
			
			System.out.println(queryVO.getType());

			long startTime = System.currentTimeMillis();
			
			content = HttpClientUtil.grabData(url);// 抓取回来的网页内容

			QueryVO queryVO2 = new QueryVO();

			queryVO2 = JsoupUtil.parseContentAndUpdate(content, queryVO, lastTime);// 解析document

			if( queryVO2.isUpdateFlag()){
				
				// 确定循环次数
				Integer cycleCount = StringUtil.getCycleCount(queryVO2.getGrabUrl());// 循环结束条件+1
				System.out.println("循环次数：" + (cycleCount - 1));

				for (int j = 2; j <= cycleCount && queryVO2.isUpdateFlag(); j++) {

					String nextUrl = prefixUrl1 + "/" + j + suffixUrl1;

					content = HttpClientUtil.grabData(nextUrl);// 抓取回来的网页内容

					queryVO2 = JsoupUtil.parseContentAndUpdate(content, queryVO2, lastTime);// 解析document
				}


			}
			long endTime = System.currentTimeMillis();
			
			System.out.println("抓取时间：" + ((endTime - startTime) / 1000) + "秒");
			
			System.out.println("结束抓取");

			List<Food> foodList = queryVO2.getFoodList();

			// 入库 每批插入100条记录
			startTime = System.currentTimeMillis();

			Integer count = foodService.insertBatches(foodList);

			System.out.println("受影响的记录数：" + count);

			endTime = System.currentTimeMillis();

			System.out.println("入库耗时：" + ((endTime - startTime) / 1000) + "秒");
		}
		
	}
	
	
	public static String getFoodType(int num){
		
		String foodTypeStr = null;
		
		for (FoodType foodType :FoodType.values() ){
			
			if( foodType.ordinal() == num ){
				
				foodTypeStr =  foodType.name();
			}
		}
		
		return foodTypeStr;
	}

}
