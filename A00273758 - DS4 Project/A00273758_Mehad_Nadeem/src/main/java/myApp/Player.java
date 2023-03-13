package myApp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")

@XmlType(propOrder = {"player_id", "firstName", "lastName", "age", "gender", "club", "appearances", "goals", "assists"} )
public class Player 
{
	private int player_id;
	private String firstName, lastName;
	private char gender;
	private String club;
	private int appearances, goals, assists;
	
	
	public int getPlayer_id() {
		return player_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public char getGender() {
		return gender;
	}
	public String getClub() {
		return club;
	}
	public int getAppearances() {
		return appearances;
	}
	public int getGoals() {
		return goals;
	}
	public int getAssists() {
		return assists;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public void setAppearances(int appearances) {
		this.appearances = appearances;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	
	
}
