package net.infovek.lambda.infoveklamda.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Person {

	private int id;
	private String name;
	private String surname;
	private int age;

	public Person(String json) {
		Gson gson = new Gson();
		Person request = gson.fromJson(json, Person.class);
		this.id = request.getId();
		this.name = request.getName();
	}

	public String toString() {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}