package secondGroup.webmanger.user.regist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import secondGroup.common.util.DbUtil;


/**
 * 注册-dao层
 * 
 * @author 张昌盛
 */
public class RegistDao {

	public void regist(String email,String phone,String password,String passwordRepeat) {
		Connection connection=null;
		Statement statement=null;
		String sql="insert into user_List values('"+email+"','"+phone+"','"+password+"','"+passwordRepeat+"')";
		try {
			connection=DbUtil.getDBConn();
			statement=connection.createStatement();
			if(statement.execute(sql)) {
				System.out.println("注册成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(connection, statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
