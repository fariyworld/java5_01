package com.offcn.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.domain.QueryVO;
import com.offcn.domain.Student;
import com.offcn.service.FoodService;
import com.offcn.service.PhoneService;

@Controller
@RequestMapping(value = "/Food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private PhoneService phoneService;

	@RequestMapping(value = "/grabData.action")
	@ResponseBody
	public QueryVO grabData(QueryVO queryVO) {

		String url = queryVO.getGrabUrl();

		System.out.println("前台抓取地址：" + url);

		if (foodService.grabFoodData(queryVO))
			queryVO.setResult("grab data success");

		else
			queryVO.setResult("grab data failed");

		return queryVO;
	}

	@RequestMapping(value = "/getFoodListByType.action")
	@ResponseBody
	public QueryVO getFoodListByType(QueryVO queryVO) {

		System.out.println("报表类型：" + queryVO.getType());
		System.out.println("查询品名：" + queryVO.getFoodName());

		if (queryVO.getType().equals("year")) {// 年度报表

			queryVO.setYearChartMap(foodService.getYearChart(queryVO));

		} else {// 月度报表
			System.out.println("查询日期：" + queryVO.getDate());
			foodService.getMonthChart(queryVO);
		}

		queryVO.setResult("get chart data success");

		return queryVO;
	}

	@RequestMapping(value = "/exportChartToPDF.action")
	@ResponseBody
	public QueryVO exportChartToPDF(String imgdata) {

		QueryVO queryVO = new QueryVO();

		if (foodService.saveChartToPDF(imgdata))
			queryVO.setResult("save pdf success");

		else
			queryVO.setResult("save pdf failed");

		return queryVO;
	}

	@RequestMapping(value = "/testIp.action")
	@ResponseBody
	public QueryVO testIp(HttpServletRequest request) {

		QueryVO queryVO = new QueryVO();

		queryVO.setResult(request.getRemoteAddr());

		return queryVO;
	}

	@RequestMapping(value = "/queryNumber.action")
	@ResponseBody
	public QueryVO queryNumber(String phoneNumber) {

		System.out.println("查询的手机号：" + phoneNumber);

		QueryVO queryVO = new QueryVO();

		String result = null;

		Student student = phoneService.queryPhoneLocation(phoneNumber);

		if (student != null)
			result = student.getMobileArea() + student.getMobileType();

		else
			result = "号码在号段库暂时不存在";

		queryVO.setResult(result);

		return queryVO;
	}

}
