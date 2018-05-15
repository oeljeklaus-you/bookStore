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
	 * ���������ļ�
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
	 * @Description:��ȡ����Դ
	 * @Author: ����ܣ����ߣ�
	 * @Version: V1.00 ���汾�ţ�
	 * @param null
	 * @return void
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	/**
	 * @Description:�������
	 * @Author: ����ܣ����ߣ�
	 * @Version: V1.00 ���汾�ţ�
	 * @param null
	 * @return void
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		try{
			//�õ���ǰ�߳��ϰ󶨵�����
			Connection conn = tl.get();
			if(conn==null){  //�����߳���û�а�����
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /**
	 * @Description:��������
	 * @Author: ����ܣ����ߣ�
	 * @Version: V1.00 ���汾�ţ�
	 * @param null
	 * @return void
	 */
	public static void startTransaction(){
		try{
			//�õ���ǰ�߳��ϰ����ӿ�������
			Connection conn = tl.get();
			if(conn==null){  //�����߳���û�а�����
				conn = ds.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /**
	 * @Description:�ύ����
	 * @Author: ����ܣ����ߣ�
	 * @Version: V1.00 ���汾�ţ�
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
	 * @Description:�ر�����
	 * @Author: ����ܣ����ߣ�
	 * @Version: V1.00 ���汾�ţ�
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
			tl.remove();   //ǧ��ע�⣬�����ǰ�߳��ϰ󶨵����ӣ���threadlocal�������Ƴ���Ӧ��ǰ�̵߳����ӣ�
		}
	}
}
