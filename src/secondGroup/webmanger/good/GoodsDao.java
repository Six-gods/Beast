package secondGroup.webmanger.good;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import secondGroup.common.util.DbUtil;

public class GoodsDao {

private QueryRunner qr = new QueryRunner(DbUtil.getDataSource());
	
	public GoodsBean getGoodsByBarCode(String goodsNo) throws SQLException {
		String sql = "select goods_no goodsNo, goods_name goodsName, goods_price goodsPrice,goods_type type from goods where bar_code ='" + goodsNo + "'" ;
		return qr.query(sql, new BeanHandler<GoodsBean>(GoodsBean.class));
	}
}
