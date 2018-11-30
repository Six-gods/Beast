package secondGroup.webmanger.shoppingcart.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import secondGroup.webmanger.shoppingcart.bean.ResponseMsg;
import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;
import secondGroup.webmanger.shoppingcart.imp.ShoppingCartService;
import secondGroup.webmanger.user.User;


/**
 * 向购物车中添加商品
 * 
 * @author qiyongjie
 */
@WebServlet("/AddShoppingCartServlet")
public class AddShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCartService shoppingCartService = new ShoppingCartService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	ResponseMsg resMsg = new ResponseMsg();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		Object o = request.getSession().getAttribute("user");
		String quantityValue = request.getParameter("quantity");
		int quantity = 0;
		if(quantityValue!=null&&!"".equals(quantityValue)) {
			quantity = Integer.parseInt(quantityValue);
		}
		response.setContentType("text/html; charset=UTF-8");
		User user = null;
		String goodsId = request.getParameter("goodsId");
		List<ShoppingCart> cartList = null;
		ResponseMsg resMsg = new ResponseMsg();
		if(o!=null) {
			user = (User)o;
			String userId = user.getUserId();
			o=request.getAttribute("mycart");
			if(o!=null) {
				cartList = (List<ShoppingCart>)o;
			}else {
				cartList = new ArrayList<ShoppingCart>();
				cartList = shoppingCartService.getCartList(userId);
			}
			if(quantity>0) {
				boolean flag = false;
				int i = 0;
				for (ShoppingCart shoppingCart : cartList) {
					if(shoppingCart.getGoods_id().equals(goodsId)&&shoppingCart.getUser_id().equals(userId)) {
						shoppingCart = shoppingCartService.addQuantity(shoppingCart, quantity);
						cartList.set(i, shoppingCart);
						flag = true;
						break;
					}
					i++;
				}
				if(!flag) {
					cartList.add(shoppingCartService.addShoppingCart(userId, goodsId,quantity));
				}
				request.getSession().setAttribute("mycart", cartList);
				response.sendRedirect("myShoppingCart.jsp");
			}else {
				resMsg.setCode("0");
				resMsg.setMsg("商品数量不能为零");
				response.sendRedirect("#");
			}
		}else {
			response.sendRedirect("Servlet");
		}
		
	}

}
