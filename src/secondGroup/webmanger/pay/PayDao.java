package secondGroup.webmanger.pay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import secondGroup.common.util.DbUtil;
import secondGroup.webmanger.good.Goods;

public class PayDao {
	public void changeGoodsCout() {
		Connection connection=null;
		Statement statement=null;
		connection=DbUtil.getDBConn();
		String sql="update goods set goods_count='' where goods_code=''";
		ResultSet rs=null;
		Goods goods=new Goods();
		int goodscount = 0;
		try {
			statement=connection.createStatement();
			if(statement.execute(sql)) {
				System.out.println("操作成功");
			}else {
				System.out.println("操作失败");
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
	}
}


