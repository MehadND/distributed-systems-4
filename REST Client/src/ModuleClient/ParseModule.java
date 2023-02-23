package ModuleClient;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import Module.Module;

public class ParseModule {
	boolean inModules, inModule, inId, inHoursPerWeek, inName, inLecturer = false;

	private Module module;
	private List<Module> modules;
	private String tagValue;

	public List<Module> getModules() {
		return modules;
	}

	public List<Module> startParsing(String input) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(input));
			processDocument(parser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modules;
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
			module.setId(Integer.parseInt(s));
		}
		if(inHoursPerWeek)
		{
			String s = parserEvent.getText();
			module.setHoursPerWeek(Integer.parseInt(s));
		}
		if(inName)
		{
			String s = parserEvent.getText();
			module.setName(s);
		}
		if(inLecturer)
		{
			String s = parserEvent.getText();
			module.setLecturer(s);
		}
		
	}

	private void processEndTag(XmlPullParser parserEvent) {
		String endTagName = parserEvent.getName();
		
		if(endTagName.equalsIgnoreCase("modules"))
		{
			inModules = false;
		}
		else if(endTagName.equalsIgnoreCase("module"))
		{
			inModule = false;
			modules.add(module);
		}
		else if(endTagName.equalsIgnoreCase("id"))
		{
			inId = false;
		}
		else if(endTagName.equalsIgnoreCase("hoursPerWeek"))
		{
			inHoursPerWeek = false;
		}
		else if(endTagName.equalsIgnoreCase("name"))
		{
			inName = false;
		}
		else if(endTagName.equalsIgnoreCase("lecturer"))
		{
			inLecturer = false;
		}
	}

	private void processStartTag(XmlPullParser parserEvent) {
		String startTagName = parserEvent.getName();
		
		if(startTagName.equalsIgnoreCase("modules"))
		{
			inModules = true;
			modules = new ArrayList<Module>();
		}
		else if(startTagName.equalsIgnoreCase("module"))
		{
			inModule = true;
			module = new Module();
		}
		else if(startTagName.equalsIgnoreCase("id"))
		{
			inId = true;
		}
		else if(startTagName.equalsIgnoreCase("hoursPerWeek"))
		{
			inHoursPerWeek = true;
		}
		else if(startTagName.equalsIgnoreCase("name"))
		{
			inName = true;
		}
		else if(startTagName.equalsIgnoreCase("lecturer"))
		{
			inLecturer = true;
		}

	}

}
