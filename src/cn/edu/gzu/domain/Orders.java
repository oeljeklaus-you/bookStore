package cn.edu.gzu.domain;

import java.util.LinkedList;
import java.util.List;

public class Orders {
	private String id;//�������
	private float totalMoney;//�ܼƽ��
	private int totalNum;//�ܵ�����
	private int status;
	//privtae Customer customer;
	private List<OrderItem> orderItem=new LinkedList<OrderItem>();//������
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
}
