package com.szaiot.njg.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szaiot.njg.entity.req.SearchContent;
import com.szaiot.njg.entity.resp.BrandInfo;
import com.szaiot.njg.entity.resp.BrandInfo.BrandInfoList;
import com.szaiot.njg.entity.resp.CarInfo;
import com.szaiot.njg.entity.resp.CarInfo.CarInfoList;
import com.szaiot.njg.entity.resp.UserInfo;
import com.szaiot.njg.entity.resp.UserInfo.UserInfoListResp;
import com.szaiot.njg.service.ConfigureService;
import com.szaiot.njg.utils.BaseResponseObj;

@RestController
@RequestMapping("configure")
public class ConfigureController {

	@Autowired
	private ConfigureService configureService;

	/** 获取日志记录器 */
	private static Logger logger = LoggerFactory.getLogger(ConfigureController.class);

	@Value(value = "${project}")
	private String project;

	@RequestMapping("selectUserInfoList")
	public BaseResponseObj<UserInfoListResp> selectUserInfoList(HttpServletRequest request, SearchContent searchContent){
		logger.info("-------selectUserInfoList---------");
		BaseResponseObj<UserInfoListResp> baseObj = new BaseResponseObj<UserInfoListResp>();
		try {
			baseObj = configureService.selectUserInfoList(request, searchContent);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("insertUserInfo")
	public BaseResponseObj<Void> insertUserInfo(HttpServletRequest request, UserInfo userInfo){
		logger.info("-------insertUserInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.insertUserInfo(request, userInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("updateUserInfo")
	public BaseResponseObj<Void> updateUserInfo(HttpServletRequest request, UserInfo userInfo){
		logger.info("-------updateUserInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.updateUserInfo(request, userInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("deleteUserInfoByUserIds")
	public BaseResponseObj<Void> deleteUserInfoByUserIds(HttpServletRequest request, UserInfo userInfo){
		logger.info("-------deleteUserInfoByUserIds---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.deleteUserInfoByUserIds(request, userInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("updatePassword")
	public BaseResponseObj<Void> updatePassword(HttpServletRequest request, UserInfo userInfo){
		logger.info("-------updatePassword---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.updatePassword(request, userInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("selectBrandList")
	public BaseResponseObj<BrandInfoList> selectBrandList(HttpServletRequest request, SearchContent searchContent){
		logger.info("-------selectBrandList---------");
		BaseResponseObj<BrandInfoList> baseObj = new BaseResponseObj<BrandInfoList>();
		try {
			baseObj = configureService.selectBrandList(request, searchContent);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("insertBrandInfo")
	public BaseResponseObj<Void> insertBrandInfo(HttpServletRequest request, BrandInfo brandInfo){
		logger.info("-------insertBrandInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.insertBrandInfo(request, brandInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("updateBrandInfo")
	public BaseResponseObj<Void> updateBrandInfo(HttpServletRequest request, BrandInfo brandInfo){
		logger.info("-------updateBrandInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.updateBrandInfo(request, brandInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("deleteBrandInfo")
	public BaseResponseObj<Void> deleteBrandInfo(HttpServletRequest request, BrandInfo brandInfo){
		logger.info("-------deleteBrandInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.deleteBrandInfo(request, brandInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("selectCarList")
	public BaseResponseObj<CarInfoList> selectCarList(HttpServletRequest request, SearchContent searchContent){
		logger.info("-------selectCarList---------");
		BaseResponseObj<CarInfoList> baseObj = new BaseResponseObj<CarInfoList>();
		try {
			baseObj = configureService.selectCarList(request, searchContent);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("insertCarInfo")
	public BaseResponseObj<Void> insertCarInfo(HttpServletRequest request, CarInfo carInfo){
		logger.info("-------insertCarInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.insertCarInfo(request, carInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("updateCarInfo")
	public BaseResponseObj<Void> updateCarInfo(HttpServletRequest request, CarInfo carInfo){
		logger.info("-------updateCarInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.updateCarInfo(request, carInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("deleteCarInfo")
	public BaseResponseObj<Void> deleteCarInfo(HttpServletRequest request, CarInfo carInfo){
		logger.info("-------deleteCarInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = configureService.deleteCarInfo(request, carInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
}
