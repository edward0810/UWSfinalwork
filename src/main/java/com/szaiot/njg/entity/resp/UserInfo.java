package com.szaiot.njg.entity.resp;

import java.util.List;

import com.szaiot.njg.utils.NjgUtil;

/**
 * 用户对象 user_info
 */
public class UserInfo {
	public static class UserInfoListResp {
		private int count;
		private List<UserInfo> dataList;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public List<UserInfo> getDataList() {
			return dataList;
		}
		public void setDataList(List<UserInfo> dataList) {
			this.dataList = dataList;
		}
	}
	private Integer userId;

	private String userName;

	private String password;
	
	private String newPassword;

	private Integer createUser;

	private String createTime;

	private String updateTime;

	private Integer isDelete;
	
	private Integer[] userIds;
	
	private String sessionID;
	
	private Integer isAdmin;
	
	private String authStr;
	
	private Integer[] authStrArr;

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Integer[] userIds) {
		this.userIds = userIds;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getAuthStr() {
		return authStr;
	}

	public void setAuthStr(String authStr) {
		this.authStrArr=stringToInt(authStr);
		this.authStr = authStr;
	}

	public Integer[] getAuthStrArr() {
		return authStrArr;
	}

	public void setAuthStrArr(Integer[] authStrArray) {
		this.authStr=intToString(authStrArray);
		this.authStrArr = authStrArray;
	}
	
	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
