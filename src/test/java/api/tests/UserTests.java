package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

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

	@Test(priority = 1)
	public void testPostUser() {
		Response response=	UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
	@Test(priority = 2)
	public void testGetUserByName() {
		Response response=	UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
	@Test(priority = 3)
	public void testPutUserByName() {
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		Response response=	UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Response responseAfterUpdate=	UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response=	UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
}
