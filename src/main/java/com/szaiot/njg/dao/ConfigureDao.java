package com.szaiot.njg.dao;

import java.util.List;

import com.szaiot.njg.entity.req.SearchContent;
import com.szaiot.njg.entity.resp.BrandInfo;
import com.szaiot.njg.entity.resp.CarInfo;
import com.szaiot.njg.entity.resp.SystemInfo;
import com.szaiot.njg.entity.resp.UserInfo;

public interface ConfigureDao {

	int selectUserInfoListTotal(SearchContent searchContent);

	List<UserInfo> selectUserInfoList(SearchContent searchContent);

	UserInfo selectUserInfoByParam(UserInfo userInfo);

	void insertUserInfo(UserInfo userInfo);

	void updateUserInfo(UserInfo userInfo);

	void deleteUserInfoByUserIds(Integer[] userIds);

	int selectBrandListTotal(SearchContent searchContent);

	List<BrandInfo> selectBrandList(SearchContent searchContent);

	SystemInfo getSystemInfo(String system_key);

	BrandInfo getBrandInfoByName(String brandName);

	void insertBrandInfo(BrandInfo brandInfo);

	void updateBrandInfo(BrandInfo brandInfo);

	BrandInfo getBrandInfoByID(int brandId);

	void deleteBrandInfo(int brandId);

	int selectCarListTotal(SearchContent searchContent);

	List<CarInfo> selectCarList(SearchContent searchContent);

	List<String> selectCommentListByCarId(int carId);

	CarInfo getCarInfoByName(String carName);

	void insertCarInfo(CarInfo carInfo);

	void updateCarInfo(CarInfo carInfo);

	CarInfo getCarInfoByID(int carId);

	void deleteCarInfo(int carId);

	void insertBrandCarInfo(CarInfo carInfo);

	void deleteBrandCarInfo(int carId);

}
 