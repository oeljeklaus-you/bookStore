package cn.edu.gzu.web.bean;

import java.util.List;

public class Page {
    private List records;//页面里的记录
    private int pageSize=4;//页面大小
    private int totalRecords;//总的记录数、
    private int index;//每页开始的索引
    private int pageNum;//用户要查看的页数
    private int totalPage;//总页数
    
	public int getTotalPage() {
		return totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getIndex() {
		return (pageNum-1)*pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
    
    
}
