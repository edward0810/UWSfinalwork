package com.szaiot.njg.dao;

import java.util.List;

import com.szaiot.njg.entity.req.SearchContent;
import com.szaiot.njg.entity.resp.CommentInfo;
import com.szaiot.njg.entity.resp.HistoryInfo;
import com.szaiot.njg.entity.resp.SavedInfo;

public interface CarDao {

	int selectHistoryInfoListTotal(SearchContent searchContent);

	List<HistoryInfo> selectHistoryInfoList(SearchContent searchContent);

	HistoryInfo selectHistoryInfoByUserId(SearchContent searchContent);

	void addHistoryInfo(HistoryInfo historyInfo);

	void addCommentInfo(CommentInfo commentInfo);

	int selectSavedInfoListTotal(SearchContent searchContent);

	List<SavedInfo> selectSavedInfoList(SearchContent searchContent);

	void addSavedInfo(SavedInfo savedInfo);

}
 