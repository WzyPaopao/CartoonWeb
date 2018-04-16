package Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Common.SqlHelper;
import Model.book;
import Model.directory;

public class viewService {
	public static Boolean addLike( String bookName ){
		Boolean result = false;
		String sqlStr = "update book set like = like + 1 where bookName = '" + bookName + "'";
		
		result = SqlHelper.executeUpdate(sqlStr);
		
		if( result ){
			System.out.println("----------add like success");
		}//Of if
		else{
			System.out.println("----------add like failed");
		}//Of else
		
		return result;
	}//Of addLike
	
	/**
	 * ²éÑ¯Âþ»­Ä¿Â¼
	 * @param bookName
	 * @return
	 */
	public static List<directory> queryDirectory( String bookName ){
		List<directory> listDic = new ArrayList();
		String sqlStr = "select * from directory where bookid = (select bookid from book where bookName = '" + bookName +"')";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				directory newDic = new directory();
				
				newDic.setChapterid( rs.getInt("chapterid") );
				newDic.setBookid( rs.getInt("bookid") );
				newDic.setImage( rs.getString("image") );
				newDic.setChaptername( rs.getString("chapterName") );
				
				listDic.add(newDic);
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}
		
		return listDic;
	}//Of queryDirectory
	
	public static book getBookInfo( String bookName ){
		book thebook = new book();
		String sqlStr = "select * from book where bookName like '%" + bookName + "%'";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		List<directory> listDic = queryDirectory(bookName);
		
		listDic = queryDirectory(bookName);
		
		try{
			while( rs.next() ){
				thebook.setBookname( rs.getString("bookName") );
				thebook.setAuthor( rs.getString("author") );
				thebook.setCategory( rs.getString("category") );
				thebook.setDic( listDic );
				thebook.setIntroduction( rs.getString("introduction") );
				thebook.setLike( rs.getInt("likeNum") );
				thebook.setFace( rs.getString("Face") );
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return thebook;
	}//Of getBookInfo
	
	public static String getBookImage( String bookName, String chapterid ){
		String imageUrl = new String("");
		String sqlStr = "select image from directory where chapterid = " + chapterid + " and bookid = (select bookid from book where bookName = '" + bookName + "')";
		
		ResultSet rs = SqlHelper.executeQuery( sqlStr );
		
		try{
			while( rs.next() ){
				imageUrl = rs.getString("image");
			}//Of while
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return imageUrl;
	}//Of getBookImage
}
