package cn.edu.gzu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.edu.gzu.dao.OrdersDao;
import cn.edu.gzu.domain.OrderItem;
import cn.edu.gzu.domain.Orders;
import cn.edu.gzu.utils.JdbcUtils;

public class OrdersDaoImpl implements OrdersDao {
	QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
	@Override
	public void saveOrders(Orders order) {
		if(order==null)
		{
			throw new IllegalArgumentException("订单不能为空");
		}
		List<OrderItem> items=order.getOrderItem();
		try{
			if(items!=null&&items.size()>0)
			{
				for(OrderItem item:items)
				{
					String sql="insert into OrderItem(id,num,totalMoney,bid,oid) values(?,?,?,?,?)";
					runner.update(sql, new Object[]{item.getId(),item.getNum(),item.getTotalMoney(),item.getBook().getId(),order.getId()});
				}
			}
			String sql="insert into orders(id,totalMoney,totalNum,status) values(?,?,?,?)";
			runner.update(sql, new Object[]{order.getId(),order.getTotalMoney(),order.getTotalNum(),order.getStatus()});
		}
		catch(Exception e)
		{
			throw new RuntimeException("保存失败");
		}
	}

	@Override
	public Orders findOrdersById(String id) {
	   String sql="select *from orders where id=?";
	   try {
		Orders order=(Orders) runner.query(sql, id, new BeanHandler(Orders.class));
		return order;
	} catch (SQLException e) {
		throw new RuntimeException("查询失败");
	}
	}

}
