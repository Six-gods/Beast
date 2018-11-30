package secondGroup.webmanger.shoppingcart.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import secondGroup.common.util.DbUtil;
import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;

/**
 * 购物车数据库操作
 * 
 * @author qiyongjie
 */
public class ShoppingCartDao {
	
	private QueryRunner qr = new QueryRunner(DbUtil.getDataSource());

	/*
	 * 查询我的购物车
	 */
	public List<ShoppingCart> getCartList(String userId){
		String sql = "select shopping_cart.*,goods.* from shopping_cart,goods where shopping_cart.user_id = '"+userId+"' and shopping_cart.goods_id=goods.goods_id";
		List<ShoppingCart> cartList = null;	
		try {
			cartList = qr.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class));
			if(cartList.size()==0) {
				cartList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}
	
	/*
	 * 向购物车中添加
	 */
	public ShoppingCart addShoppingCart(String userId,String goodsId,int quantity) {
		ShoppingCart shoppingCart = null;
		String sql = "insert into shopping_cart (user_id,goods_id,quantity,addDate) values('"+userId+"','"+goodsId+"','"+quantity+"','2018-1-1')";
		try {
			if(qr.update(sql)>0) {
				sql = "select shopping_cart.*,goods.* from shopping_cart,goods where shopping_cart.user_id = '"+userId+"' and shopping_cart.goods_id=goods.goods_id and goods.goods_id='"+goodsId+"'";
				shoppingCart = qr.query(sql, new BeanHandler<ShoppingCart>(ShoppingCart.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingCart;
	}
	
	/*
	 * 购物车中商品数量增加
	 */
	public ShoppingCart addQuantity(ShoppingCart shoppingCart,int quantity) {
		String userId = shoppingCart.getUser_id();
		String goodsId = shoppingCart.getGoods_id();
		quantity = quantity+shoppingCart.getQuantity();
		String sql = "update shopping_cart set quantity='"+quantity+"'where goods_id='"+goodsId+"' and user_id='"+userId+"'";
		try {
			if(qr.update(sql)>0) {
				shoppingCart.setQuantity(quantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingCart;
	}

	/*
	 * 根据商品编号和用户编号查询购物车信息
	 */
	public ShoppingCart selestShoppingCart(String goodsId,String userId) {
		String sql = "select shopping_cart.*,goods.* from shopping_cart,goods where shopping_cart.user_id = '"+userId+"'and shopping_cart.goods_id=goods.goods_id and shopping_cart.goods_id='"+goodsId+"'";
		ShoppingCart shoppingCart = null;
		try {
			shoppingCart = qr.query(sql, new BeanHandler<ShoppingCart>(ShoppingCart.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingCart;
	}
	
	/*
	 * 删除购车中的某一条数据
	 */
	public boolean deleteShoppingCart(String userId,String goodsId) {
		String sql = "delete from shopping_cart where goods_id = '"+goodsId+"'and user_id = '"+userId+"'";
		try {
			return qr.update(sql)>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 根据商品名称模糊查询
	 */
	public List<ShoppingCart> searchShoppingCart(String message){
		String sql = "select * from shopping_cart,goods where goods.goods_name like '%"+message+"%' and goods.goods_id = shopping_cart.goods_id";
		List<ShoppingCart> cartList = null;
		try {
			cartList = qr.query(sql, new BeanListHandler<ShoppingCart>(ShoppingCart.class));
			if(cartList.size()==0) {
				cartList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}
}
