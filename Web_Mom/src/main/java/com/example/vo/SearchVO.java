package com.example.vo;

public class SearchVO{
	private String searchKeyword;
   	private int searchCondition;
   	private int pageIndex;
   	private int countPerPage = 10;
   	private int recordCountPerPage = 30;
   	private int firstIndex;
   	private int lastIndex;
   	private String sortColumn;
   	private String sortOrder;
   	private String name;
   	@Override
   	public String toString() {
   		return "SearchVO [searchKeyword=" + searchKeyword + ", searchCondition=" + searchCondition + ", pageIndex="
   				+ pageIndex + ", countPerPage=" + countPerPage + ", recordCountPerPage=" + recordCountPerPage
   				+ ", firstIndex=" + firstIndex + ", lastIndex=" + lastIndex + ", sortColumn=" + sortColumn
   				+ ", sortOrder=" + sortOrder + ", name=" + name + "]";
   	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(int searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}