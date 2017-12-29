package com.offcn.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.offcn.domain.FaceAccessToken;
import com.offcn.domain.FaceRegisterBean;

public interface FaceService {

	/**
	 * 
	 * @TODO: 获取 AccessToken key
	 * @param client_id
	 * @param client_secret
	 * @return FaceAccessToken
	 */
	@GET
	@Path("/getAccessToken")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	FaceAccessToken getAccessToken(String client_id, String client_secret);
	
	
	FaceRegisterBean addFace(FaceRegisterBean faceRegisterBean);
}
