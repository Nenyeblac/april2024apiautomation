package utilities;

import com.jayway.jsonpath.DocumentContext;

public class RequestBodyService {

    public void setRequestBodyForPost(DocumentContext requestBody, String uId, String title, String body) {
        requestBody.set("userId", uId);
        requestBody.set("title", title);
        requestBody.set("body", body);
    }


    public void setRequestBodyForUser(DocumentContext requestBody, String name, String uName) {
        requestBody.set("userId", name);
        requestBody.set("title", uName);
    }
}
