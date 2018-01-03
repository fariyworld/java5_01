package com.offcn.enums;

public enum FaceConstant {
	
	
	Content_Type("application/x-www-form-urlencoded"),

	/*
	 *请求方式：post
	 *人脸注册
	 */
	registerUrl("https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add"),
	
	
	/*
	 * 获取Access Token：请求URL数据格式
	 * 
	 * 向授权服务地址https://aip.baidubce.com/oauth/2.0/token发送请求（推荐使用POST），
	 * 
	 * 并在URL中带上以下参数：
	 * 	grant_type： 必须参数，固定为client_credentials；
	 *  client_id： 必须参数，应用的API Key；
	 *  client_secret： 必须参数，应用的Secret Key
	 */
	accessTokenUrl("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&"),
	client_id("zw99HIZ00fCIq2Avvac69f2G"),
	client_secret("U5AeVa0xuFc32hwur8bX5QH8V8Xron7d"),
	//access_token("24.3d549b067053dff6c088add8a32428a0.2592000.1517066386.282335-10599554"),
	//access_token("24.752534b8cc1f82d5efef8d1934b09f79.2592000.1517235143.282335-10599554"),
	//access_token("24.6e7f92daf20fba33dd4973a87fa8916e.2592000.1517235379.282335-10599554"),
	//access_token("24.d31bf66f76aa5f369ec6340255807f45.2592000.1517235808.282335-10599554"),
	//access_token("24.d9f014a27a548d79da453535a1285f50.2592000.1517383375.282335-10599554"),
	//access_token("24.c1ebc0aba0948d358bac790874eaa196.2592000.1517401861.282335-10599554"),
	//access_token("24.eea1851c480038a1be40681e0a9f8ab6.2592000.1517487254.282335-10599554"),
	access_token("24.9fe85dd0e97eb99b0492ccb90725002f.2592000.1517487385.282335-10599554"),
	
	/**
	 * 人脸识别
	 */
	identifyUrl("https://aip.baidubce.com/rest/2.0/face/v2/identify"),
	
	
	/**
	 * 人脸更新
	 */
	updateUrl("https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/update");
	
	private String value;

	FaceConstant(String value) {
		
		this.value = value;
	}

	public String getVal() {
		return value;
	}
	
}
