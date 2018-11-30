package secondGroup.webmanger.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import secondGroup.common.util.DbUtil;
import secondGroup.webmanger.good.Goods;

public class SearchDao {
	
	public List<Goods> search(String goodsName) {
		List<Goods> goodsList=new ArrayList<Goods>();
		Connection connection=DbUtil.getDBConn();
		Statement statement=null;
		ResultSet rs=null;
		String sql="select * from goods where goods_name like '%"+goodsName+"%'";
		System.out.println("------------->"+sql);
		try {
			 statement=connection.createStatement();
			 rs=statement.executeQuery(sql);
			 while(rs.next()) {
				 Goods goods=new Goods();
				 goods.setGoodsName(rs.getString(1));
				 goods.setGoodsCode(rs.getString(2));
				 goodsList.add(goods);
			 }
			 return goodsList;
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
		return null;
	}

}
