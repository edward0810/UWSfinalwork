package com.szaiot.njg.entity.resp;

import java.util.List;

public class NewsInfo {
	
	private String url;         // 新闻超链接
	private String image;       // 图片
	
	
	public static class NewsInfoList {
		public List<NewsInfo> getDataList() {
			return dataList;
		}

		public void setDataList(List<NewsInfo> dataList) {
			this.dataList = dataList;
		}

		private List<NewsInfo> dataList;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
