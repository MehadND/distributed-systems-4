import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import Book.Book;

public class BookClient {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/j2BookServer/rest/book").build();
			
			System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");
			
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);
			
			String text;
			
			HttpEntity entity = httpResponse.getEntity();
			
			text = EntityUtils.toString(entity);
			//System.out.println(text);
			
			ParseBook bookParser = null;
			List<Book> booksList = new ParseBook().startParsing(text);
			
			System.out.println("------------------------------------");
			
			for(Book b : booksList)
			{
				System.out.println("ID: "+b.getId());
				System.out.println("Title: "+b.getTitle());
				System.out.println("Author: "+b.getAuthor());
				System.out.println("Year: "+b.getYear());
				System.out.println("------------------------------------");
			}
			
			//System.out.println(inputStream);
			
		
		
	}
}
