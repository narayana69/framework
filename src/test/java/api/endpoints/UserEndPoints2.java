package api.endpoints;
//create for perform create,read,update,delete request the user APIs
//UserEndPoints.java
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints2 {
	
	
	
	static ResourceBundle	geturl(){
		ResourceBundle routes=	ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response	createUser(User payload){
		String post_url=geturl().getString("post_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return response;
	}
	public static Response	getUser(String userName){
		String get_url=geturl().getString("get_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(get_url);
		return response;
	}public static Response	updateUser(String userName,User payload){
		String update_url=geturl().getString("update_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				.when()
				.put(update_url);
		return response;
	}
	public static Response	deleteUser(String userName){
		String delete_url=geturl().getString("delete_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(delete_url);
		return response;}
}
