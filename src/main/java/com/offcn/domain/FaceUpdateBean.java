package com.offcn.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @TODO: 人脸更新
 * @Administrator: 黄土高坡的宝宝
 * @Date: 2017年12月30日下午5:00:41
 * @Version: V1.0.0
 */
@XmlRootElement
public class FaceUpdateBean {

	/** body参数 */
	private String uid;
	private String images;

	/** url参数 */
	private String access_token;

	/** 更新返回json值： */
	private String log_id;

	/** 更新失败返回值+log_id： */
	private String error_code;
	private String error_msg;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	@Override
	public String toString() {
		return "FaceUpdateBean [log_id=" + log_id + ", error_code=" + error_code + ", error_msg=" + error_msg + "]";
	}

}
