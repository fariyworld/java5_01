package com.offcn.domain;

public class Food {

	private Integer id;
	private String foodName;
	private String minPrice;
	private String avgPrice;
	private String maxPrice;
	private String specification;// 规格：普通
	private String unit;// 单位：斤
	private String publishDate;// 发布日期;yyyy-MM-dd
	private String type;// 所属类别

	public Food() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", minPrice=" + minPrice + ", avgPrice=" + avgPrice
				+ ", maxPrice=" + maxPrice + ", specification=" + specification + ", unit=" + unit + ", publishDate="
				+ publishDate + ", type=" + type + "]";
	}

}
