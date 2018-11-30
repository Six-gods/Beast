package secondGroup.webmanger.intro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class IntroductionServlet
 */
@WebServlet("/IntroductionServlet.do")
public class IntroductionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsCode=request.getParameter("goodsCode");
		IntriductionDao idd=new IntriductionDao();
		int goodsCount=idd.getGoodsCount(goodsCode);
		response.getWriter().write(new Gson().toJson(goodsCount));
	}

}
