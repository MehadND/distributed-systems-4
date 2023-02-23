package BookClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import Book.Book;

public class ParseBook {

	boolean inBooks, inBook, inId, inAuthor, inTitle, inYear = false;

	private Book book;
	private List<Book> books;
	private String tagValue;

	public List<Book> getBooks() {
		return books;
	}

	public List<Book> startParsing(String input) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(input));
			processDocument(parser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException
	{
		int eventType = pullParser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("START_DOCUMENT");
			}
			else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("END_DOCUMENT");
			}
			else if (eventType == XmlPullParser.START_TAG) {
				processStartTag(pullParser);
			}
			else if (eventType == XmlPullParser.END_TAG) {
				processEndTag(pullParser);
			}
			else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		}
	}

	private void processText(XmlPullParser parserEvent) {
		if(inId)
		{
			String s = parserEvent.getText();
			book.setId(Integer.parseInt(s));
		}
		if(inAuthor)
		{
			String s = parserEvent.getText();
			book.setAuthor(s);
		}
		if(inTitle)
		{
			String s = parserEvent.getText();
			book.setTitle(s);
		}
		if(inYear)
		{
			String s = parserEvent.getText();
			book.setYear(Integer.parseInt(s));
		}
		
	}

	private void processEndTag(XmlPullParser parserEvent) {
		String endTagName = parserEvent.getName();
		
		if(endTagName.equalsIgnoreCase("books"))
		{
			inBooks = false;
		}
		else if(endTagName.equalsIgnoreCase("book"))
		{
			inBook = false;
			books.add(book);
		}
		else if(endTagName.equalsIgnoreCase("id"))
		{
			inId = false;
		}
		else if(endTagName.equalsIgnoreCase("author"))
		{
			inAuthor = false;
		}
		else if(endTagName.equalsIgnoreCase("title"))
		{
			inTitle = false;
		}
		else if(endTagName.equalsIgnoreCase("year"))
		{
			inYear = false;
		}
	}

	private void processStartTag(XmlPullParser parserEvent) {
		String startTagName = parserEvent.getName();
		
		if(startTagName.equalsIgnoreCase("books"))
		{
			inBooks = true;
			books = new ArrayList<Book>();
		}
		else if(startTagName.equalsIgnoreCase("book"))
		{
			inBook = true;
			book = new Book();
		}
		else if(startTagName.equalsIgnoreCase("id"))
		{
			inId = true;
		}
		else if(startTagName.equalsIgnoreCase("author"))
		{
			inAuthor = true;
		}
		else if(startTagName.equalsIgnoreCase("title"))
		{
			inTitle = true;
		}
		else if(startTagName.equalsIgnoreCase("year"))
		{
			inYear = true;
		}

	}

}
