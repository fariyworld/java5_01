package com.offcn.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.offcn.domain.FaceAccessToken;
import com.offcn.domain.FaceIdentifyBean;
import com.offcn.domain.FaceRegisterBean;

public interface FaceService {

	/**
	 * 
	 * @TODO: 获取 AccessToken key
	 * 调用方法传递参数即可
	 * @param client_id
	 * @param client_secret
	 * @return FaceAccessToken
	 */
	@GET
	@Path("/getAccessToken")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	FaceAccessToken getAccessToken(String client_id, String client_secret);
	
	
	/**
	 * 
	 * @TODO: 人脸注册
	 * @param faceRegisterBean
	 * @return FaceRegisterBean
	 */
	@POST
	@Path("/addFace")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	FaceRegisterBean addFace(FaceRegisterBean faceRegisterBean);
	
	/**
	 * 
	 * @TODO: 人脸识别
	 * @param faceIdentifyBean
	 * @return FaceIdentifyBean
	 */
	FaceIdentifyBean identifyFace(FaceIdentifyBean faceIdentifyBean);
}
