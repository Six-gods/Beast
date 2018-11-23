package secondGroup.webmanger.good;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import secondGroup.webmanger.order.OrderItem;

/**
 *根据输入的商品编号查找商品
 *
 * @author 高小炎
 */
@SuppressWarnings("serial")
@WebServlet("/showGoods.do")
public class GoodsShowServlet extends HttpServlet{

	private OrderService os = new OrderService();
	 
	@Override
	protected void doGet(HttpServletRequest requestuest, HttpServletResponse responseonse) throws ServletException, IOException {
		doPost(requestuest, responseonse);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsNo = request.getParameter("goodsNo");
		String goodsCount = request.getParameter("goodsCount");
		Integer gc = 0;
		if(goodsCount != null && !"".equals(goodsCount)) {
			gc = Integer.parseInt(goodsCount);
		}else {
			gc = 1;
		}
		List<OrderItem> orList = null;
		Object o = request.getSession().getAttribute("orderItemList");
		if(o != null) {
			orList = (List<OrderItem>) o;
		}else {
			orList = new LinkedList<OrderItem>();
		}
		boolean isNotExist = true;
		for(OrderItem oi : orList) {
			if(oi.getGoods_no().equals(goodsNo)) {
				oi.setGoods_number(oi.getGoods_number() + gc);
				isNotExist = false;
				break;
			}
		}
		if(isNotExist) {
			try {
				OrderItem oi = os.getOrderItemByBarcode(goodsNo);
				oi.setGoods_number(gc);
				orList.add(oi);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("orderItemList", orList);
		response.sendRedirect("one/home/search.html");
	}
}
