package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.book;
import Model.users;
import Service.bookService;
import Service.userService;

/**
 * Servlet implementation class theCenterServlet
 */
@WebServlet("/theCenterServlet")
public class theCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public theCenterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		users theuser = new users();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		List<book> bookList = new ArrayList();
		
		bookList = bookService.queryBookByAuthor(userName);

		theuser = userService.getUserInfo(userName);
		
		session.setAttribute("theuser", theuser);
		session.setAttribute("bookList", bookList);
		
		response.sendRedirect("userCenterPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
