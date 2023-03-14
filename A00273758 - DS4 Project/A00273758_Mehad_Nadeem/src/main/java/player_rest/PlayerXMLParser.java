package player_rest;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PlayerXMLParser {

	boolean inPlayers, inPlayer, inPlayerID, inPlayerName, inPlayerAge, inPlayerGender, inPlayerNationality,
			inPlayerClub, inPlayerAppearances, inPlayerGoals, inPlayerAssists = false;

	private Player player;
	private List<Player> players;
	private String tagValue;

	public List<Player> startParsing(String input) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(input));
			processRequest(parser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}

	public void processRequest(XmlPullParser pullParser) throws XmlPullParserException, IOException {
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
		if(inPlayerID)
		{
			String s = parserEvent.getText();
			player.setPlayer_id(Integer.parseInt(s));
		}
		if(inPlayerName)
		{
			String s = parserEvent.getText();
			player.setName(s);
		}
		if(inPlayerAge)
		{
			String s = parserEvent.getText();
			player.setAge(Integer.parseInt(s));
		}
		if(inPlayerGender)
		{
			String s = parserEvent.getText();
			player.setGender(s.charAt(0));
		}
		if(inPlayerNationality)
		{
			String s = parserEvent.getText();
			player.setNationality(s);
		}
		if(inPlayerClub)
		{
			String s = parserEvent.getText();
			player.setClub(s);
		}
		if(inPlayerAppearances)
		{
			String s = parserEvent.getText();
			player.setAppearances(Integer.parseInt(s));
		}
		if(inPlayerGoals)
		{
			String s = parserEvent.getText();
			player.setGoals(Integer.parseInt(s));
		}
		if(inPlayerAssists)
		{
			String s = parserEvent.getText();
			player.setAssists(Integer.parseInt(s));
		}
		
	}

	private void processEndTag(XmlPullParser parserEvent) {
		String endTagName = parserEvent.getName();
		
		if(endTagName.equalsIgnoreCase("players"))
		{
			inPlayers = false;
		}
		else if(endTagName.equalsIgnoreCase("player"))
		{
			inPlayer = false;
			players.add(player);
		}
		else if(endTagName.equalsIgnoreCase("player_id"))
		{
			inPlayerID = false;
		}
		else if(endTagName.equalsIgnoreCase("name"))
		{
			inPlayerName = false;
		}
		else if(endTagName.equalsIgnoreCase("age"))
		{
			inPlayerAge = false;
		}
		else if(endTagName.equalsIgnoreCase("gender"))
		{
			inPlayerGender = false;
		}
		else if(endTagName.equalsIgnoreCase("nationality"))
		{
			inPlayerNationality = false;
		}
		else if(endTagName.equalsIgnoreCase("club"))
		{
			inPlayerClub = false;
		}
		else if(endTagName.equalsIgnoreCase("appearances"))
		{
			inPlayerAppearances = false;
		}
		else if(endTagName.equalsIgnoreCase("goals"))
		{
			inPlayerGoals = false;
		}
		else if(endTagName.equalsIgnoreCase("assists"))
		{
			inPlayerAssists = false;
		}
	}

	private void processStartTag(XmlPullParser parserEvent) {
		String startTagName = parserEvent.getName();

		if(startTagName.equalsIgnoreCase("players"))
		{
			inPlayers = true;
			players = new ArrayList<Player>();
		}
		else if(startTagName.equalsIgnoreCase("player"))
		{
			inPlayer = true;
			player = new Player();
		}
		else if(startTagName.equalsIgnoreCase("player_id"))
		{
			inPlayerID = true;
		}
		else if(startTagName.equalsIgnoreCase("name"))
		{
			inPlayerName = true;
		}
		else if(startTagName.equalsIgnoreCase("age"))
		{
			inPlayerAge = true;
		}
		else if(startTagName.equalsIgnoreCase("gender"))
		{
			inPlayerGender = true;
		}
		else if(startTagName.equalsIgnoreCase("nationality"))
		{
			inPlayerNationality = true;
		}
		else if(startTagName.equalsIgnoreCase("club"))
		{
			inPlayerClub = true;
		}
		else if(startTagName.equalsIgnoreCase("appearances"))
		{
			inPlayerAppearances = true;
		}
		else if(startTagName.equalsIgnoreCase("goals"))
		{
			inPlayerGoals = true;
		}
		else if(startTagName.equalsIgnoreCase("assists"))
		{
			inPlayerAssists = true;
		}
	}
	
	public Player startParsing2(String input) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(input));
			processRequest(parser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}
	
	public void processRequest2(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("START_DOCUMENT");
			}
			else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("END_DOCUMENT");
			}
			else if (eventType == XmlPullParser.START_TAG) {
				processStartTag2(pullParser);
			}
			else if (eventType == XmlPullParser.END_TAG) {
				processEndTag2(pullParser);
			}
			else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		}
	}

	private void processEndTag2(XmlPullParser parserEvent) {
		String endTagName = parserEvent.getName();
		
		if(endTagName.equalsIgnoreCase("players"))
		{
			inPlayers = false;
		}
		else if(endTagName.equalsIgnoreCase("player"))
		{
			inPlayer = false;
			players.add(player);
		}
		else if(endTagName.equalsIgnoreCase("player_id"))
		{
			inPlayerID = false;
		}
		else if(endTagName.equalsIgnoreCase("name"))
		{
			inPlayerName = false;
		}
		else if(endTagName.equalsIgnoreCase("age"))
		{
			inPlayerAge = false;
		}
		else if(endTagName.equalsIgnoreCase("gender"))
		{
			inPlayerGender = false;
		}
		else if(endTagName.equalsIgnoreCase("nationality"))
		{
			inPlayerNationality = false;
		}
		else if(endTagName.equalsIgnoreCase("club"))
		{
			inPlayerClub = false;
		}
		else if(endTagName.equalsIgnoreCase("appearances"))
		{
			inPlayerAppearances = false;
		}
		else if(endTagName.equalsIgnoreCase("goals"))
		{
			inPlayerGoals = false;
		}
		else if(endTagName.equalsIgnoreCase("assists"))
		{
			inPlayerAssists = false;
		}
	}

	private void processStartTag2(XmlPullParser parserEvent) {
		String startTagName = parserEvent.getName();

		if(startTagName.equalsIgnoreCase("players"))
		{
			inPlayers = true;
			players = new ArrayList<Player>();
		}
		else if(startTagName.equalsIgnoreCase("player"))
		{
			inPlayer = true;
			player = new Player();
		}
		else if(startTagName.equalsIgnoreCase("player_id"))
		{
			inPlayerID = true;
		}
		else if(startTagName.equalsIgnoreCase("name"))
		{
			inPlayerName = true;
		}
		else if(startTagName.equalsIgnoreCase("age"))
		{
			inPlayerAge = true;
		}
		else if(startTagName.equalsIgnoreCase("gender"))
		{
			inPlayerGender = true;
		}
		else if(startTagName.equalsIgnoreCase("nationality"))
		{
			inPlayerNationality = true;
		}
		else if(startTagName.equalsIgnoreCase("club"))
		{
			inPlayerClub = true;
		}
		else if(startTagName.equalsIgnoreCase("appearances"))
		{
			inPlayerAppearances = true;
		}
		else if(startTagName.equalsIgnoreCase("goals"))
		{
			inPlayerGoals = true;
		}
		else if(startTagName.equalsIgnoreCase("assists"))
		{
			inPlayerAssists = true;
		}
	}

	
}
