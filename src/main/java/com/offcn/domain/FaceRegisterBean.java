package com.offcn.domain;

/**
 * 
 * @TODO: 人脸注册JavaBean
 * @Administrator: 黄土高坡的宝宝
 * @Date: 2017年12月28日下午8:38:44
 * @Version: V1.0.0 // 注册成功 { "log_id": 73473737, } // 注册发生错误 { "error_code":
 *           216616, "log_id": 674786177, "error_msg": "image exist" }
 * 
 */
public class FaceRegisterBean {

	/** 注册成功返回json值： */
	private String log_id;

	/** 注册失败返回json值： */
	private String error_code;
	private String error_msg;

	/** url参数 */
	private String access_token;

	/** 注册请求参数：Body中放置请求参数 */
	private String uid;
	private String user_info;
	private String group_id;
	private String images; // 图像base64编码串
	private String action_type;// 可选 默认：append ，replace:更新

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

	// ------------------------------------------------

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUser_info() {
		return user_info;
	}

	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return "FaceRegisterBean [log_id=" + log_id + ", error_code=" + error_code + ", error_msg=" + error_msg + "]";
	}

}
