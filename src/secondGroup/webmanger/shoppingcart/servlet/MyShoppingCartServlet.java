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
import secondGroup.webmanger.user.User;

/**
 *  查询我的购物车
 *  
 * @author qiyongjie
 */
@WebServlet("/MyShoppingCartServlet")
public class MyShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private ShoppingCartService shoppingCartService = new ShoppingCartService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		Object o = request.getSession().getAttribute("user");
		User user = new User();
		List<ShoppingCart> cartList = null;
		ResponseMsg resMsg = new ResponseMsg();
		if(o!=null) {
			user = (User)o;
			String userId = user.getUserId();
			cartList = shoppingCartService.getCartList(userId);
			if(cartList==null) {
				resMsg.setCode("0");
				resMsg.setMsg("您的购物车为空");
				response.getWriter().write(new Gson().toJson(resMsg));
			}else {
				resMsg.setCode("1");
				request.getSession().setAttribute("mycart", cartList);
				response.getWriter().write(new Gson().toJson(cartList));
			}
		}else {
			response.sendRedirect("Servlet");
		}
	}

}
