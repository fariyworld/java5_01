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
	access_token("access_token=24.3d549b067053dff6c088add8a32428a0.2592000.1517066386.282335-10599554"),
	
	
	identifyUrl("https://aip.baidubce.com/rest/2.0/face/v2/identify");
	
	private String value;

	FaceConstant(String value) {
		
		this.value = value;
	}

	public String getVal() {
		return value;
	}
	
}
