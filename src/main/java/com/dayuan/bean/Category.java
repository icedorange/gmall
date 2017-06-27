package com.dayuan.bean;

import java.util.List;

public class Category {
	private Integer id;
	private String name;
	private Integer pid;
	private List<Category> categoryVo;

	public List<Category> getCategoryVo() {
		return categoryVo;
	}

	public void setCategoryVo(List<Category> category3) {
		this.categoryVo = category3;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
