package step_definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocialNetworkingStepDef extends BaseSteps {

    //declaring this at class level, so it can be accessible to both last two methods
    Response responseForGetAPostCall;
    Response responseForCreateAPostCall;
    Response responseForUpdateAPost;
    Response responseForDeleteAPostCall;

    //For GET http request
    @Given("service JSONPlaceholder is up and running")
    public void service_json_placeholder_free_fake_rest_api_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        Response responseForService = getCall();
        assertThat(responseForService.statusCode(), equalTo(200));
    }

    @When("I send GET request to get a specific post with {string}")
    public void i_send_get_request_to_get_a_specific_post_with(String id) {
        setHeadersWithContentType();
        setEndpointPath(postUrl + id);
        responseForGetAPostCall = getCall();

    }

    @Then("the specific post details {string} {string} and {string} are returned with status code of {int}")
    public void the_specific_post_details_and_are_returned_with_status_code_of(String id, String title, String body, Integer sCode) {
        assertThat(responseForGetAPostCall.statusCode(), equalTo(sCode));
        assertThat(responseForGetAPostCall.body().jsonPath().get("id"), equalTo(Integer.parseInt(id)));
        assertThat(responseForGetAPostCall.body().jsonPath().get("title"), equalTo(title));
        assertThat(responseForGetAPostCall.body().jsonPath().get("body"), equalTo(body));
    }


    //For POST http request
    @When("I send a POST request with the following detail {string}, {string} and {string}")
    public void i_send_a_post_request_with_the_following_detail_and(String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(postUrl);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(postPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody, uId, title, body);
        responseForCreateAPostCall = getPostCall();
    }

    @Then("I should get a response with status code of {int} and the following {string}, {string} and {string}")
    public void i_should_get_a_response_with_status_code_of_and_the_following_and(Integer sCode, String uId, String title, String body) {
        assertThat(responseForCreateAPostCall.statusCode(), equalTo(sCode));
        assertThat(responseForCreateAPostCall.jsonPath().get("userId"), equalTo(uId));
        assertThat(responseForCreateAPostCall.jsonPath().get("title"), equalTo(title));
        assertThat(responseForCreateAPostCall.jsonPath().get("body"), equalTo(body));
    }
//using PUT http to update a post
    @When("I update an existing post with {string} using the following detail {string}, {string} and {string}")
    public void iUpdateAnExistingPostWithUsingTheFollowingDetailAnd(String id, String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(postUrl + id);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(postPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody, uId, title, body);
        responseForUpdateAPost = getPutCall();

    }

    @Then("I should get a response with status code of {int} and the following {string}, {string} and {string} for put")
    public void iShouldGetAResponseWithStatusCodeOfAndTheFollowingAndForPut(int sCode, String uId, String title, String body) {
        assertThat(responseForUpdateAPost.statusCode(), equalTo(sCode));
        assertThat(responseForUpdateAPost.jsonPath().get("userId"), equalTo(uId));
        assertThat(responseForUpdateAPost.jsonPath().get("title"), equalTo(title));
        assertThat(responseForUpdateAPost.jsonPath().get("body"), equalTo(body));
    }

    //Using DELETE http call to delete a post
    @When("I send DELETE request for a specific post with {string}")
    public void iSendDELETERequestForASpecificPostWith(String id) {
        setHeadersWithContentType();
        setEndpointPath(postUrl + id);
        responseForDeleteAPostCall = getCall();
    }

    @Then("the status code of {int} is returned")
    public void theStatusCodeOfIsReturned(int scode) {
        assertThat(responseForDeleteAPostCall.statusCode(), equalTo(200));
    }


    //for user payload, to replace some information in the payload


}
