package cn.edu.gzu.dao.impl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

import cn.edu.gzu.dao.CategoryDao;
import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.utils.JdbcUtils;


public class CategoryDaoImpl implements CategoryDao {
	QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
	@Override
	public void add(Category category) {
		String sql="insert into Category(id,cname,cdesc) values(?,?,?)";
		Object[] params={category.getId(),category.getCname(),category.getCdesc()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ÃÌº” ß∞‹");
		}
		
	}

	@Override
	public void delete(String id) {
		
		String sql="delete from category where id=?";
		try {
			runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("…æ≥˝ ß∞‹");
		}
	}

	@Override
	public void update(Category category) {
		String sql="update category set cname=? ,cdesc=? where id=?";
	    Object[] params={category.getCname(),category.getCdesc(),category.getId()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("∏¸–¬ ß∞‹");
		}
		
	}

	@Override
	public Category find(String id) {
		String sql="select * from Category where id=?";
		Category category=null;
		try {
		 category=(Category) runner.query(sql,id,new BeanHandler(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}
		return category;
	}

	@Override
	public List<Category> findCategoryList() {
		String sql="select * from Category ";
		List<Category> categories=null;
		try {
			categories=(List<Category>) runner.query(sql, new BeanListHandler(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}
		return categories;
	}

	@Override
	public Admin findAdmin(String name, String pwd) {
		Connection conn=null;
		try {
			conn = JdbcUtils.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs =null;
			String sql="select *from admin where name="+name+" and pwd="+pwd;
			System.out.println(sql);
			System.out.println(name);
			System.out.println(pwd);
			rs  = st.executeQuery(sql);
			Admin admin=null;
			if(rs.next()){
				admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setPwd(rs.getString("pwd"));
			}
		    return admin;
		} catch (SQLException e) {
			throw new RuntimeException("≤È—Ø ß∞‹");
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

}
