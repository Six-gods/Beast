package secondGroup.webmanger.shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import secondGroup.webmanger.shoppingcart.bean.ResponseMsg;
import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;
import secondGroup.webmanger.shoppingcart.imp.ShoppingCartService;

/**
 * 模糊搜素购物车中的商品
 * 
 * @author qiyongjie
 */
@WebServlet("/SearchGoodsServlet")
public class SearchGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShoppingCartService shoppingCartService = new ShoppingCartService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String message = request.getParameter("message");
		ResponseMsg resMsg = new ResponseMsg();
		List<ShoppingCart> cartList = null;
		cartList = shoppingCartService.searchShoppingCart(message);
		if(cartList==null){
			resMsg.setCode("0");
			resMsg.setMsg("购物车中没有此商品");
			response.getWriter().write(new Gson().toJson(resMsg));
		}else {
			resMsg.setCode("1");
			request.setAttribute("cartlist", cartList);
			response.getWriter().write(new Gson().toJson(cartList));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
