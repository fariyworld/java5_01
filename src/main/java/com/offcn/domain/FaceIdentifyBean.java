package com.offcn.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @TODO: 人脸识别JavaBean 说明：人脸识别返回值不直接判断是否是同一人，只返回用户信息及相似度分值。
 *        说明：推荐可判断为同一人的相似度分值为80，您也可以根据业务需求选择更合适的阈值。
 * @Administrator: 黄土高坡的宝宝
 * @Date: 2017年12月30日下午2:14:57
 * @Version: V1.0.0
 */
@XmlRootElement
public class FaceIdentifyBean {

	// Required
	private String group_id;
	private String images;
	/** url参数 */
	private String access_token;

	// Optional
	private String ext_fields;
	private Integer user_top_num;

	// Return
	private String log_id;
	private Integer result_num;
	private List<?> result;
	private List<?> ext_info;
	private String faceliveness;

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

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExt_fields() {
		return ext_fields;
	}

	public void setExt_fields(String ext_fields) {
		this.ext_fields = ext_fields;
	}

	public Integer getUser_top_num() {
		return user_top_num;
	}

	public void setUser_top_num(Integer user_top_num) {
		this.user_top_num = user_top_num;
	}

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public Integer getResult_num() {
		return result_num;
	}

	public void setResult_num(Integer result_num) {
		this.result_num = result_num;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public List<?> getExt_info() {
		return ext_info;
	}

	public void setExt_info(List<?> ext_info) {
		this.ext_info = ext_info;
	}

	public String getFaceliveness() {
		return faceliveness;
	}

	public void setFaceliveness(String faceliveness) {
		this.faceliveness = faceliveness;
	}

	@Override
	public String toString() {
		return "FaceIdentifyBean [log_id=" + log_id + ", result_num=" + result_num + ", result=" + result
				+ ", ext_info=" + ext_info + ", faceliveness=" + faceliveness + "]";
	}

}
