package keven.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	//创建连接池
	public static DataSource getDataSource(){
		return ds;
	}
	//获取连接
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	//释放资源
		public static void close(Connection conn,Statement ps,ResultSet rs){
			try{
				if(conn!=null)
				{
					conn.close();
				}
			}catch(Exception e){
				System.out.println(e);
			}finally{
				conn=null;
			}
			
			try{
				if(ps!=null)
				{
					ps.close();
				}
			}catch(Exception e){
				System.out.println(e);
			}finally{
				ps=null;
			}
			
			try{
				if(rs!=null)
				{
					rs.close();
				}
			}catch(Exception e){
				System.out.println(e);
			}finally{
				rs=null;
			}
		}


}
