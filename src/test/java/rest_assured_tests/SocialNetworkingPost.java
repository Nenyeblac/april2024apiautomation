package rest_assured_tests;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class SocialNetworkingPost {
    @Test
    public void getAllPost() {
        given().contentType(ContentType.JSON).when().get("https://jsonplaceholder.typicode.com/posts").then().statusCode(200);
        //when you want everything(the request and the response) to be logged on the console
        given().log().all().contentType(ContentType.JSON).when().get("https://jsonplaceholder.typicode.com/posts").
                then().log().all().statusCode(200);
    }

    @Test
    public void getASpecificPost() {
        given().log().all().contentType(ContentType.JSON).when().get("https://jsonplaceholder.typicode.com/posts/2").
                then().log().all().statusCode(200).body("title", equalTo("qui est esse"));
    }

    @Test
    public void createANewPost() {
        HashMap<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("userId", "400404");
        bodyForPost.put("title", "My trip to Japan");
        bodyForPost.put("body", "I enjoyed my stay in Japan. It was very beautiful bla bla bla");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).when().post("https://jsonplaceholder.typicode.com/posts").
                then().log().all().statusCode(201).body("title", equalTo("My trip to Japan"));
    }

    @Test
    public void updateAPostUsingPut(){
        Map<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("userId", "400404");
        bodyForPost.put("title", "My trip to Japan");
        bodyForPost.put("body", "I did not enjoy my stay in Japan. It was very boring bla bla bla");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).when().put("https://jsonplaceholder.typicode.com/posts/1").
                then().log().all().statusCode(200);
    }

    @Test
    public void updateAPostUsingPatch(){
        Map<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("title", "My trip to Johannesburg");
        bodyForPost.put("body", "Oh I really did not enjoy my stay in Johannesburg. It wasn't very boring");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).when().patch("https://jsonplaceholder.typicode.com/posts/100").
                then().log().all().statusCode(200);
    }

    @Test
    public void deleteAPost(){
        given().log().all().contentType(ContentType.JSON).when().delete("https://jsonplaceholder.typicode.com/posts/100").
                then().log().all().statusCode(200);
    }
}
