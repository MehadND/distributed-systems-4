package myApp;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/book")
public class BookResource {

	@GET
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public List<Book> getBooks(){
		return BookDAO.instance.getBooks();
	}
	
	@GET
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	@Path("{bookId}")
	public Book getBook(@PathParam("bookId") String id) {
		return BookDAO.instance.getBook(Integer.parseInt(id));
	}
}
