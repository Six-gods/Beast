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

import secondGroup.webmanger.shoppingcart.bean.ResponseMsg;
import secondGroup.webmanger.shoppingcart.bean.ShoppingCart;
import secondGroup.webmanger.shoppingcart.imp.ShoppingCartService;
import secondGroup.webmanger.user.User;

/**
 * 提交订单
 * 
 * @author qiyongjie
 */
@WebServlet("/SubmitOrderSevlet")
public class SubmitOrderSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCartService shoppingCartService = new ShoppingCartService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String lengthValue = request.getParameter("length");
		int length = 0;
		ResponseMsg resMsg = new ResponseMsg();
		if(lengthValue!=null && !"".equals(lengthValue)) {
			length = Integer.parseInt(lengthValue);
		}
		List<ShoppingCart> orderList = new ArrayList<ShoppingCart>();
		String goodsIdOrderArray[] = request.getParameterValues("goodsIdArray");
		Object o = request.getSession().getAttribute("user");
		if(o!=null) {
			User user = (User)o;
			String userId = user.getUserId();
			if(length!=0&&goodsIdOrderArray!=null) {
				for(int i=0;i<length;i++) {
					String goodsId = goodsIdOrderArray[i];
					orderList.add(shoppingCartService.selestShoppingCart(goodsId, userId));
				}
				resMsg.setCode("1");
				resMsg.setMsg("提交订单");
				request.getSession().setAttribute("orderList", orderList);
			}else {
				resMsg.setCode("0");
				resMsg.setMsg("提交的为空值");
			}
		}else {
			resMsg.setCode("0");
		}
		response.getWriter().write(new Gson().toJson(resMsg));
	}

}
