package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setEmail(faker.phoneNumber().cellPhone());

	}

	@Test(priority = 0)
	public void testPostUser() {
		Response response=	UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
	@Test(priority = 1)
	public void testGetUserByName() {
		Response response=	UserEndPoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
	@Test(priority = 2)
	public void testPutUserByName() {
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		Response response=	UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Response responseAfterUpdate=	UserEndPoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	@Test(priority = 3)
	public void testDeleteUserByName() {
		Response response=	UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
}
