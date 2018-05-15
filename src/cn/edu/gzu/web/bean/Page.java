package cn.edu.gzu.web.bean;

import java.util.List;

public class Page {
    private List records;//ҳ����ļ�¼
    private int pageSize=4;//ҳ���С
    private int totalRecords;//�ܵļ�¼����
    private int index;//ÿҳ��ʼ������
    private int pageNum;//�û�Ҫ�鿴��ҳ��
    private int totalPage;//��ҳ��
    
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
