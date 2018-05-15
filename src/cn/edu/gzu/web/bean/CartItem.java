package cn.edu.gzu.web.bean;

import cn.edu.gzu.domain.Book;

public class CartItem {
	   private Book book;//购物项里面的商品，如果从全局考虑的话，应该为泛型
	   private int num;//购物的数量
	   private float price;//购物小计
	
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
