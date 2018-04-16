package Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Directory;

import Common.SqlHelper;
import Model.book;
import Model.directory;

public class bookService {
	public static book queryBookDetail( String bookName ){
		book thebook = new book();
		String sqlStr = new String();
		
		sqlStr = "select * from book where name like '%" + bookName + "%'";
		ResultSet rs = SqlHelper.executeQuery( sqlStr );
		
		try{
			while( rs.next() ){
				thebook.setBookid( rs.getInt("bookID") );
				thebook.setBookname( rs.getString("bookName") );
				thebook.setAuthor( rs.getString("author") );
				thebook.setLike( rs.getInt("likeNum") );
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println( e );
		}//Of catch
		
		return thebook;
	}//Of queryBookDetail
	
	public static List<book> getTopBook(){
		List<book> bookList = new ArrayList();
		String sqlStr = "select top 10 * from book order by likeNum";
		
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				book abook = new book();
				
				abook.setBookname( rs.getString("bookName") );
				abook.setBookid( rs.getInt("bookID") );
				abook.setAuthor( rs.getString("author") );
				abook.setCategory( rs.getString("category") );
				abook.setLike( rs.getInt("likeNum") );
				abook.setFace( rs.getString("face") );
				
				bookList.add(abook);
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return bookList;
	}//Of getTopBook
	
	/**
	 * 上传新书
	 * @param author
	 * @param bookName
	 * @param introduction
	 * @param category
	 * @return
	 */
	public static Boolean uploadNewBook( book abook ){
		System.out.println("here is uploadNewBook methodS");
		Boolean result = false;
		String sqlStr = "insert into book values( '" + 
				abook.getBookname() + "', '" + abook.getAuthor() + "', '" + abook.getIntroduction() + "', '" + abook.getCategory() + "', " + Integer.toString(abook.getLike()) + ", '" + abook.getComment() + "', '" + abook.getFace() + "' )";
		
		result = SqlHelper.executeUpdate(sqlStr);
		
		if( result ){
			System.out.println("----------upload success");
		}//Of if
		else{
			System.out.println("----------upload failed");
		}//Of else
		
		return result;
	}//Of uploadNewBook
	
	/**
	 * xiugai
	 * @param abook
	 * @return
	 */
	public static Boolean uploadTheBook( book abook ){
		System.out.println("here is uploadNewBook methodS");
		Boolean result = false;
		String sqlStr = "update book set bookName = '" + 
				abook.getBookname() + "', author = '" + abook.getAuthor() + "', Introduction = '" + abook.getIntroduction() + "', category = '" + abook.getCategory() + "', likeNum = " + Integer.toString(abook.getLike()) + ", comment = '" + abook.getComment() + "' where bookName = 'null'";
		
		result = SqlHelper.executeUpdate(sqlStr);
		
		if( result ){
			System.out.println("----------upload success");
		}//Of if
		else{
			System.out.println("----------upload failed");
		}//Of else
		
		return result;
	}//Of uploadNewBook
	
	
	/**
	 * 新增章节
	 * @param bookName
	 * @param image
	 * @param chapterName
	 * @param upDate
	 * @return
	 */
	public static Boolean updateBook( String bookName, String image, String upDate ){
		System.out.println("_______________here is updateBook method");
		
		Boolean result = false;
		int bookid = -1;
		String sqlStr = "select bookid from book where bookName = '" + bookName + "'";
		
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		try{
			while( rs.next() ){
				bookid = rs.getInt("bookID");
			}
		}
		catch( Exception e ){
			System.out.println(e);
		}
		
		sqlStr = "insert into directory(bookId, image, chapterName, time) values( " + String.valueOf(bookid) + ", '" + image + "', 'a', '" + upDate + "' )";
		result = SqlHelper.executeUpdate(sqlStr);
		
		System.out.println("-------------------------------insert !!!!!!!!!!!!!!!!!!!!");
		
		return result;
	}//Of updateBook
	
	public static List<book> showBook( String category ){
		List<book> bookList = new ArrayList();
		String sqlStr = "select * from book where category = '" + category + "'";
		
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		try{
			while( rs.next() ){
				book newbook = new book();
				
				newbook.setAuthor( rs.getString("author") );
				newbook.setBookname( rs.getString("bookName") );
				newbook.setIntroduction( rs.getString("introduction") );
				newbook.setLike( rs.getInt("likeNum") );
				newbook.setFace( rs.getString("Face") );
				
				bookList.add(newbook);
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return bookList;
	}//Of showBook
	
	/**
	 * 个人中心查书
	 * @param userName
	 * @return
	 */
	public static List<book> queryBookByAuthor( String userName ){
		List<book> bookList = new ArrayList();
		String sqlStr = "select * from book where author = '" + userName + "'";
		
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		try{
			while( rs.next() ){
				book newbook = new book();
				
				newbook.setAuthor( rs.getString("author") );
				newbook.setBookname( rs.getString("bookName") );
				newbook.setIntroduction( rs.getString("introduction") );
				newbook.setLike( rs.getInt("likeNum") );
				
				List<directory> listDic = viewService.queryDirectory( rs.getString("bookName") );
				newbook.setDic(listDic);
				
				bookList.add(newbook);
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return bookList;
	}//Of showBook
	
	public static List<book> queryMyBook( String userName ){
		List<book> bookList = new ArrayList();
		String sqlStr = "select * from book where author = '" + userName + "'";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				book newbook = new book();
				
				newbook.setBookname( rs.getString("bookname") );
				newbook.setLike( rs.getInt("likeNum") );
				
				bookList.add(newbook);
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return bookList;
	}//Of queryMyBook
	
	/**
	 * 改变章节名
	 */
	public static Boolean changeChapterName( String name ){
		Boolean result = false;
		String sqlStr = "update Directory set chapterName = '" + name + "' where chapterName = 'a'";
		result = SqlHelper.executeUpdate(sqlStr);
		
		return result;
	}
}
