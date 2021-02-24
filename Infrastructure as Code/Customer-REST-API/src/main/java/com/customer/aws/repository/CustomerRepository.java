package com.customer.aws.repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.customer.aws.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {

    @Autowired
    private DynamoDBMapper mapper;


    public Customer addCustomer(Customer customer) {
        mapper.save(customer);
        return customer;
    }

    public Customer findCustomerByCustomerId(String customerId) {
        return mapper.load(Customer.class, customerId);
    }
    
    public PaginatedScanList<Customer> listCustomers() {
    	/* Map<String, AttributeValue> map = new HashMap<>();
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.ap-southeast-2.amazonaws.com", "ap-southeast-2"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA3FLQE6MALEO2PE7V", "j71hHJBIbpkqEMRTAtWFwpAdVfZiAlwBX6x+gvIm"))).build();
    	ScanRequest sc  = new ScanRequest().withTableName("Customer");
    	ScanResult result = client.scan(sc);
    	for(Map<String, AttributeValue> item:result.getItems()) {
    		map.putAll(item);
    	}*/
    	
    	return mapper.scan(Customer.class, new DynamoDBScanExpression());
    }

    public String deleteCustomer(Customer customer) {
        mapper.delete(customer);
        return "customer removed !!";
    }

    public String editCustomer(Customer customer) {
        mapper.save(customer, buildExpression(customer));
        return "Customer record has been updated !";
    }

    private DynamoDBSaveExpression buildExpression(Customer customer) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("customerId", new ExpectedAttributeValue(new AttributeValue().withS(customer.getCustomerId())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }


}
