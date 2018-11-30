package com.zzxt.user.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.zzxt.common.util.DbUtil;


public class LoginDao {
	
	public boolean login(String email,String phone,String password){
		Connection connection=DbUtil.getDBConn();
		Statement statement=null;
		ResultSet rs=null;
		String sql;
		if(email==null && " ".equals(email)) {
			 sql="select * from user_List where phone='"+phone+"'";
		}
		if(phone==null && " ".equals(email)){
			sql="select * from user_List where email='"+email+"'";
		}
		System.out.println("==============>"+sql);
		try {
			statement=connection.createStatement();
			if(statement.executeQuery(sql) != null) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(connection, statement, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
