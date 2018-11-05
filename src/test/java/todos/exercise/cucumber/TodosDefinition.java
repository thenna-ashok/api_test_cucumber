package todos.exercise.cucumber;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

/** This class is used to perform all the CRUD operations for the todos */
public class TodosDefinition {
  private Response response;

  /** Default constructor which loads the base URI */
  public TodosDefinition() {
    RestAssured.baseURI = TodosConfiguration.TODOS_API_URI;
  }

  /** This method retrieves the list of all todos */
  public void requestToRetrieveAllTodos() {
    response =
        given()
            .contentType("application/json")
            .when()
            .get(TodosConfiguration.TODOS_URI_PATH)
            .then()
            .statusCode(200)
            .extract()
            .response();
  }

  /**
   * This method retrieves todos details based on ID
   *
   * @param todoID
   */
  public void requestToGetATodo(int todoID) {
    response =
        given()
            .contentType("application/json")
            .when()
            .get(TodosConfiguration.TODOS_URI_PATH + todoID)
            .then()
            .statusCode(200)
            .extract()
            .response();
  }

  /**
   * This method is used to create a new todos item
   *
   * @param todoID
   * @param title
   * @param body
   */
  public void requestToPostATodo(final int todoID, final String title, final String body) {
    RequestSpecification request = RestAssured.given();
    JSONObject requestParams = new JSONObject();
    requestParams.put("id", todoID);
    requestParams.put("title", title);
    requestParams.put("body", body);
    request.body(requestParams.toJSONString());
    response = request.post(TodosConfiguration.TODOS_URI_PATH);
  }

  /**
   * This method is used to update the todos detail if already exist else creates a new todos
   *
   * @param todoID
   * @param todoTitle
   * @param todoBody
   */
  public void requestToUpdateATodo(
      final int todoID, final String todoTitle, final String todoBody) {
    RequestSpecification request = RestAssured.given();
    JSONObject requestParams = new JSONObject();
    requestParams.put("id", todoID);
    requestParams.put("title", todoTitle);
    requestParams.put("body", todoBody);
    request.body(requestParams.toJSONString());
    response = request.put(TodosConfiguration.TODOS_URI_PATH + todoID);
  }

  /**
   * This method deletes a todos based on ID
   *
   * @param todoID
   */
  public void requestToDeleteATodo(final int todoID) {
    EncoderConfig encoderconfig = new EncoderConfig();
    response =
        given()
            .contentType("application/json")
            .config(
                RestAssured.config()
                    .encoderConfig(
                        encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .when()
            .delete(TodosConfiguration.TODOS_URI_PATH + todoID)
            .then()
            .extract()
            .response();
  }

  /**
   * This method asserts todos title from the response
   *
   * @param stringToAssert
   */
  public void assertResponseJsonString(String stringToAssert) {
    String title = response.jsonPath().getString("title[0]");
    Assert.assertEquals(stringToAssert, title);
  }

  /**
   * This method asserts todos id from the response
   *
   * @param todoID
   */
  public void assertResponseJsonByTodoID(String todoID) {
    String todoIDFromResponse = response.jsonPath().getString("id");
    Assert.assertEquals(todoID, todoIDFromResponse);
  }

  /** This method asserts the 201 response code */
  public void assertResponseCode201() {
    Assert.assertEquals(201, response.getStatusCode());
  }

  /** This method asserts the 200 response code */
  public void assertResponseCode200() {
    Assert.assertEquals(200, response.getStatusCode());
  }
}
