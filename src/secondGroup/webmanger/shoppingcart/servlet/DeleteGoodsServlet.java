package secondGroup.webmanger.shoppingcart.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
 *删除购物车中的商品 
 * 
 * @author qiyongjie
 */
@WebServlet("/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	ShoppingCartService shoppingCartService = new ShoppingCartService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		String goodsId = request.getParameter("goodsId");
		Object o = request.getSession().getAttribute("mycart");
		if(o!=null) {
			List<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
			cartList = (List<ShoppingCart>)o;
			int i = 0 ;
			for (ShoppingCart shoppingCart : cartList) {
				if(goodsId.equals(shoppingCart.getGoods_id())){
					break;
				}
				i++;
			}
			shoppingCartService.deleteShoppingCart(userId, goodsId);
			cartList.remove(i);
			request.getSession().setAttribute("mycart", cartList);
			response.getWriter().write(new Gson().toJson(cartList));
		}else {
			response.sendRedirect("login.jsp");
		}
	}
}
