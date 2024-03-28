package com.szaiot.njg.entity.req;

import com.szaiot.njg.utils.NjgUtil;

public class SearchContent {

	private String searchText; //
	private int page; //
	private int rows; //
	private Integer userId;
	private String startTime;
	private String endTime;
	private int type;
	private int brandId;
	private int carId;
	private int priceFilter;
	private String typeFilter;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getPage() {
		return (page - 1) * rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getPriceFilter() {
		return priceFilter;
	}

	public void setPriceFilter(int priceFilter) {
		this.priceFilter = priceFilter;
	}

	public String getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
	}

	public String intToString(Integer[] array) {
		String sidebarAuthStr="";
		if (array!=null && array.length>0) {
			for (int i=0;i<array.length;i++) {
				if (i==array.length-1) {
					sidebarAuthStr=sidebarAuthStr+array[i];
				}else {
					sidebarAuthStr=sidebarAuthStr+array[i]+",";
				}
			}
		}
		return sidebarAuthStr;
	}
	
	public Integer[] stringToInt(String str) {
		Integer[] array=null;
		if (!NjgUtil.isEmpty(str)) {
			String[] sidebarAuthStr=str.split(",");
			array=new Integer[sidebarAuthStr.length];
			for (int i=0;i<sidebarAuthStr.length;i++) {
				array[i]=Integer.parseInt(sidebarAuthStr[i]);
			}
		}
		return array;
	}
}
