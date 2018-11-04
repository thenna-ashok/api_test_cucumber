package todos.exercise.cucumber;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

public class TodosDefinition {
    public TodosDefinition() {
        RestAssured.baseURI = TodosConfiguration.TODOS_API_URI;
    }

    public void requestToRetrieveAllTodos() {
        Response response =
                given().
                        contentType("application/json").
                        when().
                        get("/todos/").
                        then().
                        statusCode(200).
                        extract().response();
        System.out.println(response.getStatusCode());
    }
}
