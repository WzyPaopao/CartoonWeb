package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.ComparatorBook;
import Common.counter;
import Model.book;
import Service.bookService;

/**
 * Servlet implementation class topBookServlet
 */
@WebServlet("/topBookServlet")
public class topBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public topBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<book> bookList = new ArrayList();
		bookList = bookService.getTopBook();
		HttpSession session = request.getSession();
		
		if( counter.getCount() == 0 ){
			Boolean loginStatus = false;
			counter.setCount( 1 );
			session.setAttribute("loginStatus", loginStatus);
		}
		
		ComparatorBook comparator = new ComparatorBook();
		Collections.sort(bookList, comparator);
		
		/*for( int i = 0; i < bookList.size(); i ++ ){
			System.out.println("----------bookName is " + bookList.get(i).getBookname());
			System.out.println("----------author is " + bookList.get(i).getAuthor());
			System.out.println("----------likeNum is " + bookList.get(i).getLike());
			System.out.println("----------image is " + bookList.get(i).getFace());
		}*/
		
		session.setAttribute("bookList", bookList);
		
		response.sendRedirect("IndexPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
