package com.huyu.util;

public class page {
	private int currentPage;
	private int prevPage;
	private int nextPage;
	private int pageCount;
	private int pageSize;
	private int startRecord;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public page(String curpage,int count,int pageSize) {
		curpage=curpage==null||"".equals(curpage)?"1":curpage;
		this.currentPage=Integer.parseInt(curpage);
		this.pageSize=pageSize;
		this.pageCount=count%pageSize==0?count/pageSize:count/pageSize+1;
		this.prevPage=currentPage==1?1:currentPage-1;
		this.nextPage=currentPage==pageCount?pageCount:currentPage+1;
		this.startRecord=(this.currentPage-1)*pageSize;
	}
	
}
