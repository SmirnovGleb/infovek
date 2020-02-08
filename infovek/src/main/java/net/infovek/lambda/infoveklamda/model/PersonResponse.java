package net.infovek.lambda.infoveklamda.model;

public class PersonResponse {
	private String status;
	private Person person;

	public PersonResponse() {
	}

	public PersonResponse(String status, Person person) {
		this.status = status;
		this.person = person;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
