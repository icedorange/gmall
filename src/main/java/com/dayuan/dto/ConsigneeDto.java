package com.dayuan.dto;

import java.util.List;

import com.dayuan.bean.Consignee;

public class ConsigneeDto {
	private Long id;
	private Long uid;
	private String name;
	private String mobile;
	private String privince;
	private String city;
	private String area;
	private String address;
	private Integer isDefault;
	private List<Consignee> ConsigneeDtoList;

	public List<Consignee> getConsigneeDtoList() {
		return ConsigneeDtoList;
	}

	public void setConsigneeDtoList(List<Consignee> consigneeDtoList) {
		ConsigneeDtoList = consigneeDtoList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
