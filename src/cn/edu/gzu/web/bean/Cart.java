package cn.edu.gzu.web.bean;

import java.util.HashMap;
import java.util.Map;

import cn.edu.gzu.domain.Book;

public class Cart {
	//keyֵΪ������������Ʒ��id,value�ǹ�����
	private Map<String,CartItem> items=new HashMap<String,CartItem>();
	private int totalNum;//�ܵ�����
	private float totalPrice;//�ܵļ۸�
	public Map<String, CartItem> getItems() {
		return items;
	}
	public int getTotalNum() {
		totalNum=0;
		for(Map.Entry<String,CartItem>item :items.entrySet())
		{
			totalNum=totalNum+item.getValue().getNum();
		}	
		return totalNum;
	}
	public float getTotalPrice() {
		totalPrice=0;
		for(Map.Entry<String, CartItem> item:items.entrySet())
		{
			totalPrice=totalPrice+item.getValue().getPrice();
		}
		return totalPrice;
	}
	//�÷���Ӧ���Ƿ���
	public void addBook(Book book)
	{
		if(items.containsKey(book.getId()))
		{
			items.get(book.getId()).setNum(items.get(book.getId()).getNum()+1);
		}
		else
		{
			CartItem cartItem=new CartItem(book);
			cartItem.setNum(1);
			items.put(book.getId(),cartItem);
		}
	}
	public void deleteBookFromCart(String bid) {
		items.remove(bid);		
	}
	public void UpdateBookFromCart(String bid, String num) {
		CartItem cartItem=items.get(bid);
		int number=Integer.parseInt(num);
		cartItem.setNum(number);
		
	}
}
