package com.offcn.test;

import org.junit.Test;

import com.offcn.domain.FaceAccessToken;
import com.offcn.domain.FaceRegisterBean;
import com.offcn.enums.FaceConstant;
import com.offcn.service.FaceService;
import com.offcn.service.impl.FaceServiceImpl;
import com.offcn.utils.FileUtils;
import com.offcn.utils.HttpClientUtil;

public class TestHttpClient {

	@Test
	public void test1() {

		String url = "https://aip.baidubce.com/oauth/2.0/token?"
				+ "grant_type=client_credentials&"
				+ "client_id=zw99HIZ00fCIq2Avvac69f2G&"
				+ "client_secret=U5AeVa0xuFc32hwur8bX5QH8V8Xron7d";

		FaceAccessToken accessToken = HttpClientUtil.doGet(FaceAccessToken.class, url);
		
		System.out.println(accessToken);
	}
	
	@Test
	public void test2() {
		
		FaceRegisterBean faceRegisterBean = new FaceRegisterBean();
		
		faceRegisterBean.setAccess_token(FaceConstant.access_token.getVal());
		
		faceRegisterBean.setUid("java0815001");
		faceRegisterBean.setUser_info("offcn0815 001");
		faceRegisterBean.setGroup_id("java0815");
		faceRegisterBean.setImages(FileUtils.getPicStrByImages("D:/BaiduYunDownload/001.jpg"));
		
		FaceService faceService = new FaceServiceImpl();
		
		FaceRegisterBean addFace = faceService.addFace(faceRegisterBean);
		
		System.out.println(addFace);
		
	}
}
