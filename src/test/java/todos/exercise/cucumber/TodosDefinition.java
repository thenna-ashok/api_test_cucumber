package todos.exercise.cucumber;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import static com.jayway.restassured.RestAssured.given;

public class TodosDefinition {
    public TodosDefinition() {
        RestAssured.baseURI = TodosConfiguration.TODOS_API_URI;
    }
    private Response response;

    public void requestToRetrieveAllTodos() {
        response =
                given().
                        contentType("application/json").
                        when().
                        get(TodosConfiguration.TODOS_URI_PATH).
                        then().
                        statusCode(200).
                        extract().response();
    }

    public void assertResponseJsonString(String stringToAssert) {
        String title = response.jsonPath().getString("title[0]");
        Assert.assertEquals(stringToAssert, title);
    }

    public void requestToGetATodo(int todoID) {
        response =
                given().
                        contentType("application/json").
                        when().
                        get(TodosConfiguration.TODOS_URI_PATH + todoID).
                        then().
                        statusCode(200).
                        extract().response();
    }

    public void assertResponseJsonByID(String todoID) {
        String todoIDFromResponse = response.jsonPath().getString("id");
        Assert.assertEquals(todoID, todoIDFromResponse);
    }

    public void requestToPostATodo(final int todoID, final String title, final String body) {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", todoID);
        requestParams.put("title", title);
        requestParams.put("body", body);
        request.body(requestParams.toJSONString());
        response = request.post(TodosConfiguration.TODOS_URI_PATH);
    }

    public void requestToUpdateATodo(final int todoID, final String todoTitle, final String todoBody) {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", todoID);
        requestParams.put("title", todoTitle);
        requestParams.put("body", todoBody);
        request.body(requestParams.toJSONString());
        response = request.put(TodosConfiguration.TODOS_URI_PATH + todoID);
    }

    public void assertResponseCode200(){
        Assert.assertEquals(200, response.getStatusCode());
    }

    public void assertResponseCode201(){
        Assert.assertEquals(201, response.getStatusCode());
    }

    public void requestToDeleteATodo(final int todoID) {
        EncoderConfig encoderconfig = new EncoderConfig();
        response =
                given().
                        contentType("application/json").
                          config(RestAssured.config()
                .encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        when().
                        delete(TodosConfiguration.TODOS_URI_PATH + todoID).
                        then().
                        extract().response();
    }
}
