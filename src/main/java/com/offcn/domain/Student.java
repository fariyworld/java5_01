package com.offcn.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="phone")
public class Student {

	private Double ID;
	private String MobileNumber;
	private String MobileArea;
	private String MobileType;
	private String AreaCode;
	private String PostCode;

	public Student() {
		super();
	}

	public Double getID() {
		return ID;
	}

	public void setID(Double iD) {
		ID = iD;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getMobileArea() {
		return MobileArea;
	}

	public void setMobileArea(String mobileArea) {
		MobileArea = mobileArea;
	}

	public String getMobileType() {
		return MobileType;
	}

	public void setMobileType(String mobileType) {
		MobileType = mobileType;
	}

	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}

	public String getPostCode() {
		return PostCode;
	}

	public void setPostCode(String postCode) {
		PostCode = postCode;
	}


}
