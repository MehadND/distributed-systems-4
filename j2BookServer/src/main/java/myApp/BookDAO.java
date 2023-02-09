package myApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BookDAO {

	instance;
	
	private Map<Integer, Book> booksMap = new HashMap<Integer, Book>();
	
	private BookDAO() {
		Book book1 = new Book();
		book1.setId(1);
		book1.setTitle("Book 1 Title");
		book1.setAuthor("Author 1");
		book1.setYear(2002);
		
		booksMap.put(1, book1);

		Book book2 = new Book();
		book2.setId(1);
		book2.setTitle("Book 2 Title");
		book2.setAuthor("Author 2");
		book2.setYear(1988);
		
		booksMap.put(2, book2);
	}
	
	public List<Book> getBooks()
	{
		List<Book> books = new ArrayList<Book>();
		books.addAll(booksMap.values());
		return books;
	}
	
	public Book getBook(int id) {
		return booksMap.get(id);
	}
}
