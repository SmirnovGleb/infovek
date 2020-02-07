package net.infovek.lambda.infoveklamda.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@DynamoDBTable(tableName = "myDynamoDB")
public class Person {

	private int id;
	private String name;
	private String surname;
	private int age;

	public Person() {
		name = "unknown person";
	}

	public Person(int id, String name, String surname, int age) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	@DynamoDBHashKey(attributeName = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DynamoDBAttribute(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@DynamoDBAttribute(attributeName = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + "]";
	}
}