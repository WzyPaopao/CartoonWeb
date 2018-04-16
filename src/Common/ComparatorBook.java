package Common;

import java.util.Comparator;

import Model.book;

public class ComparatorBook implements Comparator {
	public int compare( Object obj1, Object obj2 ){
		book book1 = (book)obj1;
		book book2 = (book)obj2;
		
		int flag = -2;
		if( book1.getLike() > book2.getLike() ){
			flag = 1;
		}
		else if( book1.getLike() == book2.getLike() ){
			flag = 0;
		}
		else{
			flag = -1;
		}
		
		if( flag == 0 ){
			return book1.getBookname().compareTo(book2.getBookname());
		}
		else{
			return flag;
		}
	}//Of compare
}
