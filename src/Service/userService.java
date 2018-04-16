package Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import Common.SqlHelper;
import Model.users;

public class userService {
	public static Boolean loginCheck( String userName, String password ){
		Boolean checkRight = false;
		String rightPassword = new String();
		String sqlStr = "select password from users where userName = '" + userName + "'";
		
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				rightPassword = rs.getString("password");
			}//Of whiles
			
			if( password.equals(rightPassword) ){
				checkRight = true;
			}//Of if
		}//Of try
		catch( Exception e ){
			System.out.println(e);
		}//Of catch
		
		return checkRight;
	}//Of loginCheck
	
	public static Boolean addUser( String userName, String password, String userSex ){
		Boolean result = false;
		String sqlStr = "insert into users values( '" + userName + "', '" + password + "', '" + userSex + "', 0 )";
		
		result = SqlHelper.executeUpdate(sqlStr);
		
		return result;
	}//Of addUser
	
	public static users getUserInfo( String userName ){
		users theuser = new users();
		String sqlStr = "select * from users where userName = '" + userName + "'";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				theuser.setUsername( rs.getString("userName") );
				theuser.setUserid( rs.getInt("userID") );
				theuser.setUsersex( rs.getString("usersex") );
				theuser.setSelfIntroduction( rs.getString("selfIntroduction") );
			}//Of while
		}//Of try
		catch( Exception e ){
			
		}//Of catch
		
		
		return theuser;
	}
	
	public static Boolean isFocus( String userName, String bookName ){
		Boolean result = false;
		String sqlStr = "select * from focus where userName = '" + userName + "' and bookid = (select bookid from book where bookname = '" + bookName + "')";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		
		try{
			while( rs.next() ){
				result = true;
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		
		return result;
	}//Of isFocus
	
	public static Boolean changeFocus( String userName, String bookName, String status ){
		Boolean result = false;
		Boolean a = false;
		Boolean b = false;
		String sqlStr = new String("");
		
		if( status.equals("已关注") ){
			sqlStr = "delete focus where userName = '" + userName + "' and bookid = (select bookid from book where bookName = '" + bookName + "')";
			a = SqlHelper.executeUpdate(sqlStr);
			sqlStr = "update book set likeNum = likeNum - 1 where bookName = '" + bookName + "'";
			b = SqlHelper.executeUpdate(sqlStr);
		}
		else if( status.equals("关注") ){
			sqlStr = "insert into focus values( '" + userName + "', (select bookid from book where bookName = '" + bookName + "') )";
			a = SqlHelper.executeUpdate(sqlStr);
			sqlStr = "update book set likeNum = likeNum + 1 where bookName = '" + bookName + "'";
			b = SqlHelper.executeUpdate(sqlStr);
		}
		
		if( a && b ){
			result = true;
		}
		
		return result;
	}//Of changeFocus
	
	/**
	 * 修改个人资料
	 * @param userName
	 * @param name
	 * @param sex
	 * @param intro
	 * @return
	 */
	public static Boolean changeUserInfo( String userName, String name, String sex, String intro ){
		Boolean result = false;
		System.out.println("-----------here is changeUserInfo method");
		String sqlStr = "update users set userName = '" + name + "' , usersex = '" + sex + "' , selfIntroduction = '" + intro + "' where userName = '" + userName + "'";
		
		result = SqlHelper.executeUpdate(sqlStr);
		
		return result;
	}//Of changeUserInfo
}
