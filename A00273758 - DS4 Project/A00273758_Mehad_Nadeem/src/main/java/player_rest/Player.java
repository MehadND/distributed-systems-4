package player_rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")

@XmlType(propOrder = {"player_id", "name", "age", "gender", "nationality", "club", "appearances", "goals", "assists"} )
public class Player {

	private int player_id;
	private String name;
	private int age;
	private char gender;
	private String nationality;
	private String club;
	private int appearances;
	private int goals;
	private int assists;
	
	
	public int getPlayer_id() {
		return player_id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public char getGender() {
		return gender;
	}
	public String getNationality() {
		return nationality;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
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
