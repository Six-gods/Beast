package secondGroup.webmanger.shoppingcart.imp;

import java.util.List;

import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;

/**
 * 购物车
 * 
 * @author qiyongjie
 */
public class ShoppingCartService {

	private ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
	
	public List<ShoppingCart> getCartList(String userId){
		return shoppingCartDao.getCartList(userId);
	}
	
	public ShoppingCart addShoppingCart(String userId,String goodsId,int quantity) {
		return shoppingCartDao.addShoppingCart(userId, goodsId,quantity);
	}
	
	public ShoppingCart addQuantity(ShoppingCart shoppingCart,int quantity) {
		return shoppingCartDao.addQuantity(shoppingCart, quantity);
	}
	
	public ShoppingCart selestShoppingCart(String goodsId,String userId) {
		return shoppingCartDao.selestShoppingCart(goodsId, userId);
	}
	
	public boolean deleteShoppingCart(String userId,String goodsId) {
		return shoppingCartDao.deleteShoppingCart(userId, goodsId);
	}
	
	public List<ShoppingCart> searchShoppingCart(String message){
		return shoppingCartDao.searchShoppingCart(message);
	}
}
