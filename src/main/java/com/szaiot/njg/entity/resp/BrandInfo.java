package com.szaiot.njg.entity.resp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BrandInfo {
	
	private int brandId;        // 编号
	private String brandName;   // 名称
	private String image;       // 照片路径
	private MultipartFile file;
	private int type;           // 类型。 0=汽车 1=摩托车 2=面包车 3=房车
	
	
	public static class BrandInfoList {
		private int count;
		private List<BrandInfo> dataList;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public List<BrandInfo> getDataList() {
			return dataList;
		}
		public void setDataList(List<BrandInfo> dataList) {
			this.dataList = dataList;
		}
	}
	
	
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
