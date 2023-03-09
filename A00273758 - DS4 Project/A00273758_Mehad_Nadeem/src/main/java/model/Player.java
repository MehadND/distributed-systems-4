package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "player")

@XmlType(propOrder = {"id", "name", "age", "gender", "nationality", "club", "appearances", "goals", "assists"} )
public class Player {

	private int id, age, appearances, goals, assists;
	private String name, nationality, club;
	private char gender;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAppearances()
	{
		return appearances;
	}
	public void setAppearances(int appearances)
	{
		this.appearances = appearances;
	}
	public int getGoals()
	{
		return goals;
	}
	public void setGoals(int goals)
	{
		this.goals = goals;
	}
	public int getAssists()
	{
		return assists;
	}
	public void setAssists(int assists)
	{
		this.assists = assists;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNationality()
	{
		return nationality;
	}
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	public String getClub()
	{
		return club;
	}
	public void setClub(String club)
	{
		this.club = club;
	}
	public char getGender()
	{
		return gender;
	}
	public void setGender(char gender)
	{
		this.gender = gender;
	}
	
	
	
}
