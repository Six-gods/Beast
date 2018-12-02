package secondGroup.webmanger.user.regist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注册-servlet层
 * 
 * @author 张昌盛
 */
@WebServlet("/RegistServlet.do")
public class RegistServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctxpath=request.getContextPath();
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String passwordRepeat=request.getParameter("passwordRepeat");
		RegistDao registdao=new RegistDao();
		registdao.regist(email, phone, password, passwordRepeat);
		response.sendRedirect("/secondGroups/jsp/person-address.jsp");
	}

}
