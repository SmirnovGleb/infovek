package net.infovek.lambda.infoveklamda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import net.infovek.lambda.infoveklamda.model.Person;
import net.infovek.lambda.infoveklamda.model.PersonRequest;
import net.infovek.lambda.infoveklamda.model.PersonResponse;


public class LambdaFunctionHandler implements RequestHandler<PersonRequest, PersonResponse> {
	private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	@Override
	public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
		
		String action = personRequest.getAction();
		int id = personRequest.getId();
		Person person = personRequest.getPerson();
		
		switch(action) {
			case "get":
				getPerson(id, context);
				break;
			case "create":
				getPerson(id, context);
				break;
			case "update":
				getPerson(id, context);
				break;
			case "delete":
				getPerson(id, context);
				break;
		}
		
		return new PersonResponse();
	}
	
	private Person getPerson(int id, Context context){
		Person person = mapper.load(Person.class, id);
		if(person == null) {
            context.getLogger().log("No Person found with ID: " + id + "\n");
            person = new Person();
		}
		
		return person;
	}
	
}