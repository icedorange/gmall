package com.dayuan.vo;

public class CartGoodsVo {
	private Long id;
	private String product;
	private Integer promotionPrice;
	private Integer originalPrice;
	private String picture;
	private Integer number;
	private Integer stockNum;
	private Integer sum;

	public CartGoodsVo(Long id, String product, Integer promotionPrice, Integer originalPrice, String picture,
			Integer number, Integer stockNum, Integer sum) {
		super();
		this.id = id;
		this.product = product;
		this.promotionPrice = promotionPrice;
		this.originalPrice = originalPrice;
		this.picture = picture;
		this.number = number;
		this.stockNum = stockNum;
		this.sum = sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Integer promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
