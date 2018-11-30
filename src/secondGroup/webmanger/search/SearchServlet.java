package secondGroup.webmanger.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import secondGroup.webmanger.good.Goods;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Goods> goodsList=new ArrayList<Goods>();
		String goodsName=request.getParameter("goodsName");
		SearchDao sd=new SearchDao();
		goodsList=sd.search(goodsName);
		if(goodsList!=null) {
			response.sendRedirect("/secondGroups/jsp/introduction.jsp");
		}else {
			response.sendRedirect("/secondGroups/jsp/home.jsp");
		}
	}

}
