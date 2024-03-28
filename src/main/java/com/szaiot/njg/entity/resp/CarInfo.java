package com.szaiot.njg.entity.resp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CarInfo {
	
	private int carId;           // 编号
	private String carName;      // 名称
	private String level;        // 级别。 例如：中大型车、中型SUV
	private String environmentProtection;// 环保标准 例如：欧I、欧II
	private int maxPower;        // 最大功率。单位：kW
	private int maxTorque;       // 最大扭矩。单位：N·m
	private String engine;       // 发动机
	private String gearbox;      // 变速箱
	private String size;         // 长x宽x高。单位：mm
	private String bodyStructure;// 车身结构
	private int maxSpeed;        // 最高车速。单位：km/h
	private String wltc;         // 综合油耗。单位：L/100km
	private String warranty;     // 整车质保
	private String image;        // 照片路径
	private MultipartFile file;
	private int price;           // 价格。单位：元
	private List<String> comment;// 评论
	private int brandId;         // 品牌编号
	
	
	public static class CarInfoList {
		private int count;
		private List<CarInfo> dataList;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public List<CarInfo> getDataList() {
			return dataList;
		}
		public void setDataList(List<CarInfo> dataList) {
			this.dataList = dataList;
		}
	}
	
	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEnvironmentProtection() {
		return environmentProtection;
	}
	public void setEnvironmentProtection(String environmentProtection) {
		this.environmentProtection = environmentProtection;
	}
	public int getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}
	public int getMaxTorque() {
		return maxTorque;
	}
	public void setMaxTorque(int maxTorque) {
		this.maxTorque = maxTorque;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBodyStructure() {
		return bodyStructure;
	}
	public void setBodyStructure(String bodyStructure) {
		this.bodyStructure = bodyStructure;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getWltc() {
		return wltc;
	}
	public void setWltc(String wltc) {
		this.wltc = wltc;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<String> getComment() {
		return comment;
	}
	public void setComment(List<String> comment) {
		this.comment = comment;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
}
