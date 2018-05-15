package cn.edu.gzu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class JdbcUtils {
	private static DataSource ds;
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();  //map
	/**
	 * 加载配置文件
	 */
	static{
		try{
			Properties prop = new Properties();
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			prop.load(in);
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * @Description:获取数据源
	 * @Author: 游宇杰（作者）
	 * @Version: V1.00 （版本号）
	 * @param null
	 * @return void
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	/**
	 * @Description:获得链接
	 * @Author: 游宇杰（作者）
	 * @Version: V1.00 （版本号）
	 * @param null
	 * @return void
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		try{
			//得到当前线程上绑定的连接
			Connection conn = tl.get();
			if(conn==null){  //代表线程上没有绑定连接
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /**
	 * @Description:开启事务
	 * @Author: 游宇杰（作者）
	 * @Version: V1.00 （版本号）
	 * @param null
	 * @return void
	 */
	public static void startTransaction(){
		try{
			//得到当前线程上绑定连接开启事务
			Connection conn = tl.get();
			if(conn==null){  //代表线程上没有绑定连接
				conn = ds.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /**
	 * @Description:提交事务
	 * @Author: 游宇杰（作者）
	 * @Version: V1.00 （版本号）
	 * @param null
	 * @return void
	 */
	public static void commitTransaction(){
		try{
			Connection conn = tl.get();
			if(conn!=null){
				conn.commit();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	 /**
	 * @Description:关闭连接
	 * @Author: 游宇杰（作者）
	 * @Version: V1.00 （版本号）
	 * @param null
	 * @return void
	 */
	public static void closeConnection(){
		try{
			Connection conn = tl.get();
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tl.remove();   //千万注意，解除当前线程上绑定的链接（从threadlocal容器中移除对应当前线程的链接）
		}
	}
}
