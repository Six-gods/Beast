package secondGroup.webmanger.intro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import secondGroup.common.util.DbUtil;
import secondGroup.webmanger.good.Goods;

public class IntriductionDao {
	
	public int getGoodsCount(String goodsCode) {
		Connection connection=null;
		Statement statement=null;
		connection=DbUtil.getDBConn();
		String sql="select * from goods where goods_code='"+goodsCode+"'";
		ResultSet rs=null;
		Goods goods=new Goods();
		int goodscount = 0;
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()) {
				goods.setGoodsCount(rs.getInt(3));
				goodscount=goods.getGoodsCount();
			}
			return goodscount;
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
		return 0;
	}

}
