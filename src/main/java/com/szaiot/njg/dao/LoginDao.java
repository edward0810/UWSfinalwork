package com.szaiot.njg.dao;

import org.apache.ibatis.annotations.Select;

import com.szaiot.njg.entity.resp.UserInfo;

public interface LoginDao {

	@Select("select userId,userName from user_info where userId = #{0} limit 1")
	UserInfo getUserInfoByID(int userID);

	UserInfo getUserInfo(UserInfo loginInfo);

	void resetPwd(UserInfo curUser);

	UserInfo getUserByID(UserInfo curUser);

	void addUserInfo(UserInfo loginInfo);

}
 