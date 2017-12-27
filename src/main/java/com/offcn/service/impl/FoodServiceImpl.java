package com.offcn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.domain.Food;
import com.offcn.domain.QueryVO;
import com.offcn.enums.Constant;
import com.offcn.mappers.FoodMapper;
import com.offcn.service.FoodService;
import com.offcn.utils.CreatePDFUtil;
import com.offcn.utils.HttpClientUtil;
import com.offcn.utils.JsoupUtil;
import com.offcn.utils.StringUtil;

@Service("foodService")
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodMapper foodMapper;

	@Override
	public boolean grabFoodData(QueryVO queryVO) {

		String content = null;

		String url = queryVO.getGrabUrl();

		String type = queryVO.getType();

		System.out.println("类别：" + type);

		long startTime = System.currentTimeMillis();

		content = HttpClientUtil.grabData(url);// 抓取回来的网页内容

		QueryVO queryVO2 = new QueryVO();

		queryVO2 = JsoupUtil.parseContent(content, queryVO);// 解析document

		String prefixUrl = StringUtil.getPrefixUrl(url);
		String suffixUrl = StringUtil.getSuffixUrl(url);

		// System.out.println("前缀：" + prefixUrl);
		// System.out.println("后缀：" + suffixUrl);

		// 确定循环次数
		Integer cycleCount = StringUtil.getCycleCount(queryVO2.getGrabUrl());// 循环结束条件+1
		System.out.println("循环次数：" + (cycleCount - 1));

		for (int i = 2; i <= cycleCount; i++) {

			String nextUrl = prefixUrl + "/" + i + suffixUrl;

			content = HttpClientUtil.grabData(nextUrl);// 抓取回来的网页内容

			queryVO2 = JsoupUtil.parseContent(content, queryVO2);// 解析document
		}

		long endTime = System.currentTimeMillis();

		System.out.println("抓取时间：" + ((endTime - startTime) / 1000) + "秒");

		List<Food> foodList = queryVO2.getFoodList();

		// 入库 每批插入100条记录
		startTime = System.currentTimeMillis();

		Integer count = insertBatches(foodList);

		System.out.println("受影响的记录数：" + count);

		endTime = System.currentTimeMillis();

		System.out.println("入库耗时：" + ((endTime - startTime) / 1000) + "秒");

		if (count > 0)
			return true;

		else
			return false;
	}

	@Override
	public Integer insertBatches(List<Food> foodList) {

		Integer count = 0;

		int totalSize = foodList.size();

		System.out.println("总记录数：" + totalSize);

		int pageSize = Constant.pageSize.getValue();

		int totalPage = (totalSize % pageSize) == 0 ? (totalSize / pageSize) : (totalSize / pageSize) + 1;// 总页数

		for (int i = 1; i < totalPage + 1; i++) {

			int satrtNum = (i - 1) * pageSize;
			int endNum = (i * pageSize > totalSize) ? totalSize : i * pageSize;

			List<Food> list = new ArrayList<Food>();

			for (int j = satrtNum; j < endNum; j++) {

				list.add(foodList.get(j));
			}
			
			System.out.println("批量插入的记录数："+list.size());
			
			count += foodMapper.batchInsert(list);
		}

		return count;
	}

	@Override
	public List<Food> getListByFoodName(String foodName) {

		foodName = StringUtil.getSelectCondition(foodName);

		System.out.println("查询条件：" + foodName);

		return foodMapper.getListByFoodName(foodName);
	}

	/**
	 * 年度报表 2016、2017
	 */
	@Override
	public Map<Integer, Map<Integer, Double>> getYearChart(QueryVO queryVO) {

		queryVO.setFoodName(StringUtil.getSelectCondition(queryVO.getFoodName()));

		Map<Integer, Map<Integer, Double>> map = new HashMap<Integer, Map<Integer, Double>>();

		Map<Integer, Double> map1 = new HashMap<Integer, Double>();
		Map<Integer, Double> map2 = new HashMap<Integer, Double>();

		for (int i = 0; i < 2; i++) {

			if (i == 0) {// 2016

				for (int j = 1; j < 12; j++) {

					queryVO.setStartTime("2016-0" + j + "-01");
					queryVO.setEndTime("2016-0" + (j + 1) + "-01");

					List<Food> list = foodMapper.getListByFoodNameAndDate(queryVO);

					int count = list.size();

					System.out.println("2016年 " + j + " 月的记录数：" + count);
					// 处理月平均价
					Double totalMonthAvgPrice = 0D;

					if (count > 0) {

						for (Food food : list) {

							totalMonthAvgPrice += Double.parseDouble(food.getAvgPrice());
						}

						System.out.println("2016年 " + j + "月的平均价格：" + (totalMonthAvgPrice / count));
						map1.put(j, totalMonthAvgPrice / count);
					} else {

						map1.put(j, 0D);
					}

				}

				queryVO.setStartTime("2016-12-01");
				queryVO.setEndTime("2017-01-01");

				List<Food> list = foodMapper.getListByFoodNameAndDate(queryVO);

				int count = list.size();
				System.out.println("2016年 12 月的记录数：" + count);

				// 处理月平均价
				Double totalMonthAvgPrice = 0D;

				if (count > 0) {

					for (Food food : list) {

						totalMonthAvgPrice += Double.parseDouble(food.getAvgPrice());
					}

					System.out.println("2016年12月的平均价格：" + (totalMonthAvgPrice / count));
					map1.put(12, totalMonthAvgPrice / count);
				} else {

					map1.put(12, 0D);
				}

				map.put(2016, map1);

			} else {// 2017

				for (int j = 1; j < 12; j++) {

					queryVO.setStartTime("2017-0" + j + "-01");
					queryVO.setEndTime("2017-0" + (j + 1) + "-01");

					List<Food> list = foodMapper.getListByFoodNameAndDate(queryVO);

					int count = list.size();

					System.out.println("2017年 " + j + " 月的记录数：" + count);
					// 处理月平均价
					Double totalMonthAvgPrice = 0D;

					if (count > 0) {

						for (Food food : list) {

							totalMonthAvgPrice += Double.parseDouble(food.getAvgPrice());
						}

						System.out.println("2017年 " + j + "月的平均价格：" + (totalMonthAvgPrice / count));
						map2.put(j, totalMonthAvgPrice / count);
					} else {

						map2.put(j, 0D);
					}
				}

				queryVO.setStartTime("2017-12-01");
				queryVO.setEndTime("2018-01-01");

				List<Food> list = foodMapper.getListByFoodNameAndDate(queryVO);

				int count = list.size();
				System.out.println("2017年 12 月的记录数：" + count);

				// 处理月平均价
				Double totalMonthAvgPrice = 0D;

				if (count > 0) {

					for (Food food : list) {

						totalMonthAvgPrice += Double.parseDouble(food.getAvgPrice());
					}

					System.out.println("2017年 12 月的平均价格：" + (totalMonthAvgPrice / count));
					map2.put(12, totalMonthAvgPrice / count);
				} else {

					map2.put(12, 0D);
				}
			}

			map.put(2017, map2);
		}
		return map;
	}

	/**
	 * 月度报表 2016、2017某两个月同比 2017的连续两个月环比
	 */
	@Override
	public Map<Integer, Map<Integer, Double>> getMonthChart(QueryVO queryVO) {

		/**
		 * 处理日期 同比：0,1 环比：2,3
		 */
		//String[] dateArr = StringUtil.getDateArr(queryVO.getDate());

		return null;
	}

	@Override
	public boolean saveChartToPDF(String baseData) {

		return CreatePDFUtil.saveChartToPDF(baseData);
	}

	@Override
	public String getLastTime() {
		
		return foodMapper.getLastTime();
	}

}
