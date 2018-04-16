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
import Service.bookService;

/**
 * Servlet implementation class showBookServlet
 */
@WebServlet("/showBookServlet")
public class showBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = new String("");
		category = new String(request.getParameter("category").getBytes("ISO8859-1"),"UTF-8");
		
		System.out.println("-----------category is " + category);
		
		List<book> bookList = new ArrayList();
		bookList = bookService.showBook(category);
		
		for(int i = 0; i < bookList.size(); i ++){
			System.out.println(bookList.get(i).getBookname());
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("bookList", bookList);
		session.setAttribute("category", category);
		
		response.sendRedirect("viewAllBook.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
