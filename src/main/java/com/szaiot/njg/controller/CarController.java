package com.szaiot.njg.controller;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szaiot.njg.entity.req.SearchContent;
import com.szaiot.njg.entity.resp.CommentInfo;
import com.szaiot.njg.entity.resp.HistoryInfo;
import com.szaiot.njg.entity.resp.HistoryInfo.HistoryInfoList;
import com.szaiot.njg.entity.resp.NewsInfo.NewsInfoList;
import com.szaiot.njg.entity.resp.SavedInfo;
import com.szaiot.njg.entity.resp.SavedInfo.SavedInfoList;
import com.szaiot.njg.service.CarService;
import com.szaiot.njg.utils.BaseResponseObj;

@RestController
@RequestMapping("car")
public class CarController {

	@Autowired
	private CarService carService;

	/** 获取日志记录器 */
	private static Logger logger = LoggerFactory.getLogger(CarController.class);

	@Value(value = "${project}")
	private String project;
	
	
	@RequestMapping("selectNewsInfoList")
	public BaseResponseObj<NewsInfoList> selectNewsInfoList(HttpServletRequest request){
		logger.info("-------selectNewsInfoList---------");
		BaseResponseObj<NewsInfoList> baseObj = new BaseResponseObj<NewsInfoList>();
		try {
			baseObj = carService.selectNewsInfoList(request);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("selectHistoryInfoList")
	public BaseResponseObj<HistoryInfoList> selectHistoryInfoList(HttpServletRequest request, SearchContent searchContent){
		logger.info("-------selectHistoryInfoList---------");
		BaseResponseObj<HistoryInfoList> baseObj = new BaseResponseObj<HistoryInfoList>();
		try {
			baseObj = carService.selectHistoryInfoList(request, searchContent);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("addHistoryInfo")
	public BaseResponseObj<Void> addHistoryInfo(HttpServletRequest request, HistoryInfo historyInfo){
		logger.info("-------addHistoryInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = carService.addHistoryInfo(request, historyInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("addCommentInfo")
	public BaseResponseObj<Void> addCommentInfo(HttpServletRequest request, CommentInfo commentInfo){
		logger.info("-------addCommentInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = carService.addCommentInfo(request, commentInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("selectSavedInfoList")
	public BaseResponseObj<SavedInfoList> selectSavedInfoList(HttpServletRequest request, SearchContent searchContent){
		logger.info("-------selectSavedInfoList---------");
		BaseResponseObj<SavedInfoList> baseObj = new BaseResponseObj<SavedInfoList>();
		try {
			baseObj = carService.selectSavedInfoList(request, searchContent);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
	
	@RequestMapping("addSavedInfo")
	public BaseResponseObj<Void> addSavedInfo(HttpServletRequest request, SavedInfo savedInfo){
		logger.info("-------addSavedInfo---------");
		BaseResponseObj<Void> baseObj = new BaseResponseObj<Void>();
		try {
			baseObj = carService.addSavedInfo(request, savedInfo);
		} catch (Exception e) {
			baseObj.setMessage("Error!");
			e.printStackTrace();
		}
		return baseObj;
	}
}
