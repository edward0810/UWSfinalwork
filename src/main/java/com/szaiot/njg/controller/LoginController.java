package com.szaiot.njg.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szaiot.njg.entity.resp.UserInfo;
import com.szaiot.njg.service.LoginService;
import com.szaiot.njg.utils.BaseResponseObj;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/** 获取日志记录器 */
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Value(value = "${project}")
	private String project;

	@RequestMapping("toLogin")
	public BaseResponseObj<UserInfo> toLogin(HttpServletRequest request, UserInfo loginInfo) {
		logger.info("-------toLogin---------");
		BaseResponseObj<UserInfo> baseObj = new BaseResponseObj<UserInfo>();
		try {
			baseObj = loginService.toLogin(request, loginInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("resetPwd")
	public BaseResponseObj<String> resetPwd(HttpServletRequest request,UserInfo loginInfo){
		logger.info("-------resetPwd---------");
		BaseResponseObj<String> baseObj = new BaseResponseObj<String>();
		try {
			baseObj = loginService.resetPwd(request, loginInfo, project);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("regUser")
	public BaseResponseObj<String> regUser(HttpServletRequest request,UserInfo loginInfo){
		logger.info("-------regUser---------");
		BaseResponseObj<String> baseObj = new BaseResponseObj<String>();
		try {
			baseObj = loginService.regUser(request, loginInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error");
			e.printStackTrace();
		}
		return baseObj;
	}
}
