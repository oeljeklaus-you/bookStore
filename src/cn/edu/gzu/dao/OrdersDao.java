package cn.edu.gzu.dao;

import cn.edu.gzu.domain.Orders;

public interface OrdersDao {

	void saveOrders(Orders order);

	Orders findOrdersById(String id);

}
