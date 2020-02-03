package net.infovek.lambda.infoveklamda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import net.infovek.lambda.infoveklamda.model.Person;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class LambdaFunctionHandler implements RequestStreamHandler {

	private JSONParser parser = new JSONParser();

	private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	
	
	private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "myDynamoDB";
    private Regions REGION = Regions.US_EAST_2;
	
	public LambdaFunctionHandler() {
	}

	// Test purpose only.
	LambdaFunctionHandler(AmazonS3 s3) {
		this.s3 = s3;
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		JSONObject responseJson = new JSONObject();

		try {
			JSONObject event = (JSONObject) parser.parse(reader);

			if (event.get("body") != null) {

				Person person = new Person((String) event.get("body"));

			}

			JSONObject responseBody = new JSONObject();
			responseBody.put("message", "New item created");

			JSONObject headerJson = new JSONObject();
			headerJson.put("x-custom-header", "my custom header value");

			responseJson.put("statusCode", 200);
			responseJson.put("headers", headerJson);
			responseJson.put("body", responseBody.toString());

		} catch (ParseException pex) {
			responseJson.put("statusCode", 400);
			responseJson.put("exception", pex);
		}
		
		retrievePerson(1);

		OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
		writer.write(responseJson.toString());
		writer.close();

	}
	
	
	
	private void retrievePerson(int id) {
		
		 HashMap<String,AttributeValue> key_to_get =
		            new HashMap<String,AttributeValue>();

		        key_to_get.put("arn:aws:dynamodb:us-east-2:220338227514:table/myDynamoDB", new AttributeValue("id"));

		        GetItemRequest request = null;
		        
		            request = new GetItemRequest()
		                .withKey(key_to_get)
		                .withTableName("myDynamoDB");		        

		        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

		        try {
		            Map<String,AttributeValue> returned_item =
		               ddb.getItem(request).getItem();
		            if (returned_item != null) {
		                Set<String> keys = returned_item.keySet();
		                for (String key : keys) {
		                    System.out.format("%s: %s\n",
		                            key, returned_item.get(key).toString());
		                }
		            } else {
		                System.out.format("No item found with the key %s!\n", "");
		            }
		        } catch (AmazonServiceException e) {
		            System.err.println(e.getErrorMessage());
		        }
				
	}

 
}