package secondGroup.webmanger.shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;
import secondGroup.webmanger.shoppingcart.imp.ShoppingCartService;

/**
 * 购物车中商品数量加一
 * 
 * @author qiyongjie
 */
@WebServlet("/QuantityAddOneServlet")
public class QuantityAddOneServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ShoppingCartService shoppingCartService = new ShoppingCartService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String goodsId = request.getParameter("goodsId");
		String userId = request.getParameter("userId");
		int quantity =  1;
		List<ShoppingCart> cartList = null;
		Object o = request.getSession().getAttribute("mycart");
		if(o!=null) {
			cartList = (List<ShoppingCart>)o;
			int i = 0;
			for (ShoppingCart shoppingCart : cartList) {
				if(shoppingCart.getGoods_id().equals(goodsId)&&shoppingCart.getUser_id().equals(userId)) {
					shoppingCartService.addQuantity(shoppingCart, quantity);
					cartList.set(i, shoppingCart);
					break;
				}
				i++;
			}
			request.getSession().setAttribute("mycart", cartList);
			response.getWriter().write(new Gson().toJson(cartList));
		}
	}

}
