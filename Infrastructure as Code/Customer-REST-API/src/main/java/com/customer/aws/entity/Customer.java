package com.customer.aws.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Customer")
public class Customer implements Serializable {
    @DynamoDBHashKey  (attributeName = "customerId")
    @DynamoDBAutoGeneratedKey
    private String customerId;
    @DynamoDBAttribute
    private String FirstName;
    @DynamoDBAttribute
    private String LastName;
    @DynamoDBAttribute
    private Address address;
	public String getCustomerId() {
		// TODO Auto-generated method stub
		return customerId;
	}
}
