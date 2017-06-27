package com.dayuan.vo;

/**
 * Ajax传输对象
 * 
 * @author elk
 *
 */
public class ResultVo {
	/**
	 * 状态码
	 */
	private Integer code;
	private Object data;
	private String msg;

	public ResultVo() {

	}

	public ResultVo(Integer code, Object data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
