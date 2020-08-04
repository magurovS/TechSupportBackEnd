package ru.magurovs.TechSupport;

import java.util.Date;

public class Order {	
	
	private int id;
	private String name;
	private String lastName; 
	private String middleName;
	private String comment;
	private String number;
	
	private Date timeProblem;
	
	private TypesProblems typeProblem;
	
	public Order() {	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public Date getTimeProblem() {
		return timeProblem;
	}


	public void setTimeProblem(Date timeProblem) {
		this.timeProblem = timeProblem;
	}


	public TypesProblems getTypeProblem() {
		return typeProblem;
	}


	public void setTypeProblem(TypesProblems typeProblem) {
		this.typeProblem = typeProblem;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public Order(String name, String lastName, 
				 String middleName, String comment, 
				 Date timeProblem, TypesProblems typeProblem){
	
		this.name = name;
		this.lastName = lastName;
		this.middleName = middleName;
		this.comment = comment;
		this.timeProblem = timeProblem;
		this.typeProblem = typeProblem;	

	}
	
	



	
	
	
}
