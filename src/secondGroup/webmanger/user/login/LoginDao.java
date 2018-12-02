package secondGroup.webmanger.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import secondGroup.common.util.DbUtil;





public class LoginDao {
	
	public boolean login(String email,String phone,String password){
		Connection connection=DbUtil.getDBConn();
		Statement statement=null;
		ResultSet rs=null;
		String sql="select * from user_List where 2=2 ";
		try {
			statement=connection.createStatement();
			if(email==null || " ".equals(email)) {
				sql+="and phone='"+phone+"'";
			}else {
				sql+="and email='"+email+"'";
			}
			
			if(password==null || " ".equals(password)) {
				return false;
			}else {
				sql+="and password='"+password+"'";
			}
			System.out.println(sql);
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println("登陆成功");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					DbUtil.close(connection, statement,rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	
	}

}
