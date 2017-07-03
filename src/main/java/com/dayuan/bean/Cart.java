package com.dayuan.bean;

public class Cart {
	private Long id;
	private Long gid;
	private Long ucid;
	private Integer number;
	private Integer isSelected;
	
	public Integer getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Integer isSelected) {
		this.isSelected = isSelected;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Long getUcid() {
		return ucid;
	}

	public void setUcid(Long ucid) {
		this.ucid = ucid;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
