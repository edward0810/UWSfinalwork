package com.szaiot.njg.entity.resp;

public class SystemInfo {
	private Integer system_id;           //编号
	private String system_key;      //属性字段
	private String system_value;     //数值
	private String remark;  //备注
	public Integer getSystem_id() {
		return system_id;
	}
	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}
	public String getSystem_key() {
		return system_key;
	}
	public void setSystem_key(String system_key) {
		this.system_key = system_key;
	}
	public String getSystem_value() {
		return system_value;
	}
	public void setSystem_value(String system_value) {
		this.system_value = system_value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
