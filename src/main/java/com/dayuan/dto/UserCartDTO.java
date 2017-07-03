package com.dayuan.dto;

import java.util.List;

public class UserCartDTO {
	private Long id;
	private Long uid;
	private List<CartDTO> cartDTO;
	
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

	public List<CartDTO> getCartDTO() {
		return cartDTO;
	}

	public void setCartDTO(List<CartDTO> cartDTO) {
		this.cartDTO = cartDTO;
	}
}
