package cn.edu.gzu.web.bean;

import cn.edu.gzu.domain.Book;

public class CartItem {
	   private Book book;//�������������Ʒ�������ȫ�ֿ��ǵĻ���Ӧ��Ϊ����
	   private int num;//���������
	   private float price;//����С��
	
	public CartItem(Book book) {
		this.book = book;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return book.getPrice()*num;
	}
  
}
