package com.dayuan.vo;

import java.io.Serializable;
import java.util.List;

public class CategoryVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 792000752333205311L;
	private Integer id;
	private String name;
	private Integer pid;
	private List<CategoryVo> categoryVoList;

	public CategoryVo() {

	}

	public CategoryVo(Integer id, String name, Integer pid, List<CategoryVo> categoryVoList) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.setCategoryVoList(categoryVoList);
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

	public List<CategoryVo> getCategoryVoList() {
		return categoryVoList;
	}

	public void setCategoryVoList(List<CategoryVo> categoryVoList) {
		this.categoryVoList = categoryVoList;
	}

}
