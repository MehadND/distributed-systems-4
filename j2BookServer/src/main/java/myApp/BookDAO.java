package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public void createBook(Book book)
	{
		booksMap.put(book.getId(), book);
	}
	
	public void deleteBook(int id)
	{
		System.out.println("Get Delete Book --> "+booksMap.get(id));
		booksMap.remove(id);
	}
	
	public String testDB()
	{
		String name = null;
		StringBuilder sb = new StringBuilder();
		
		Connection connection = null;
		
		try {
			//driver
			Class.forName("org.hsqldb.jdbcDriver");
		
			//connector
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");
			
			//statement
			Statement statement = connection.createStatement();
			
			//resultSet
			ResultSet resultSet = statement.executeQuery("select * from user;");
			
			while(resultSet.next())
			{
				sb.append(resultSet.getString("name")).append("\n");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
