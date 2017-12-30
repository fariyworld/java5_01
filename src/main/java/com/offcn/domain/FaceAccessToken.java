package com.offcn.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="faceAccessToken")
public class FaceAccessToken {

	private String access_token;
	private String expires_in;

	/** 请求错误 */
	private String error;
	private String error_description;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	// --------------------------------------------

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	@Override
	public String toString() {
		return "FaceAccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", error=" + error
				+ ", error_description=" + error_description + "]";
	}

}
