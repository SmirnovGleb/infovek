package net.infovek.lambda.infoveklamda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import net.infovek.lambda.infoveklamda.model.Person;
import net.infovek.lambda.infoveklamda.model.PersonRequest;


public class LambdaFunctionHandler implements RequestHandler<PersonRequest, Person> {

	@Override
	public Person handleRequest(PersonRequest personRequest, Context context) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Person person = mapper.load(Person.class, personRequest.getId());
		if(person == null) {
            context.getLogger().log("No Person found with ID: " + personRequest.getId() + "\n");
            person = new Person();
		}
		return person;
	}


	
}