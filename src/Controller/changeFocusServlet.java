package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.userService;

/**
 * Servlet implementation class changeFocusServlet
 */
@WebServlet("/changeFocusServlet")
public class changeFocusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeFocusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isFocus = request.getParameter("value");
		isFocus = new String( isFocus.getBytes("ISO8859-1"), "UTF-8" );
		System.out.println("------------focus is " + isFocus);
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String bookName = session.getAttribute("bookName").toString();
		Boolean focus = (Boolean)session.getAttribute("isFocus");
		
		Boolean f = userService.changeFocus(userName, bookName, isFocus);
		
		if( f ){
			System.out.println("---------关注状态改变成功");
			if( focus ){ focus = false; }
			else{ focus = true; }
			
			session.setAttribute("isFocus", focus);
		}
		
		response.sendRedirect("viewBookInfoPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
