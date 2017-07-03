package com.dayuan.vo;

import java.util.List;

public class ShopCartVo {
	private Long id;
	private Long uid;
	private List<CartGoodsVo> cartGoodsVo;

	public ShopCartVo() {

	}

	public ShopCartVo(Long id, Long uid, List<CartGoodsVo> cartGoodsVo) {
		this.id = id;
		this.uid = uid;
		this.setCartGoodsVo(cartGoodsVo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public List<CartGoodsVo> getCartGoodsVo() {
		return cartGoodsVo;
	}

	public void setCartGoodsVo(List<CartGoodsVo> cartGoodsVo) {
		this.cartGoodsVo = cartGoodsVo;
	}

}
