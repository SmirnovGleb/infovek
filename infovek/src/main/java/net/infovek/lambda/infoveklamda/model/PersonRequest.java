package net.infovek.lambda.infoveklamda.model;

public class PersonRequest {
	private int id;
	private String action;
	private Person person;

	public PersonRequest() {
	}

	public PersonRequest(int id) {
		this.id = id;
	}

	private PersonRequest(String action, Person person) {
		this.action = action;
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
