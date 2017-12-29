package com.offcn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.domain.QueryVO;

@Controller
@RequestMapping(value="/face")
public class FaceController {

	
	@RequestMapping(value="/facelogin.action")
	@ResponseBody
	public QueryVO facelogin(String imageData){
		
		QueryVO queryVO = new QueryVO();
		
		queryVO.setResult("login success");
		
		return queryVO;
	}
}
