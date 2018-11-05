package todos.exercise.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import todos.exercise.cucumber.TodosDefinition;

public class TodosSteps {

    private TodosDefinition service;

    @Given("^I need to access the todos rest api$")
    public void i_need_to_access_the_todos_api() throws Throwable {
        service = new TodosDefinition();
    }

    @When("^I retrieve the list of all todos$")
    public void i_retrieve_the_list_of_all_todos() throws Throwable {
        service.requestToRetrieveAllTodos();
    }

    @Then("^I see the todo with title \"([^\"]*)\"$")
    public void i_see_the_todo_with_title(String stringToAssert) throws Throwable {
        service.assertResponseJsonString(stringToAssert);
    }

    @When("^I retrieve a todo for an id (\\d+)$")
    public void i_retrieve_a_todo_for_id(int todoID) throws Throwable {
        service.requestToGetATodo(todoID);
    }

    @Then("^I see a todo with an id \"([^\"]*)\"$")
    public void i_see_a_todo_with_an_id(String stringToAssert) throws Throwable {
        service.assertResponseJsonByID(stringToAssert);
    }

    @When("^I post a todo with an id (\\d+) and title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void i_post_a_new_todo(final int todoID, final String todoTitle, final String todoBody) throws Throwable {
        service.requestToPostATodo(todoID, todoTitle, todoBody);
    }

    @Then("^I should see a todo created successfully$")
    public void i_should_see_a_todo_created_successfully() throws Throwable {
        service.assertResponseCode201();
    }

    @When("^I update a todo with an id (\\d+) and title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void i_update_a_todo(final int todoID, final String todoTitle, final String todoBody) throws Throwable {
        service.requestToUpdateATodo(todoID, todoTitle, todoBody);
    }

    @Then("^I should see a todo updated successfully$")
    public void i_should_see_a_todo_updated_successfully() throws Throwable {
        service.assertResponseCode200();
    }

    @When("^I delete a todo with an id (\\d+)$")
    public void i_should_delete_a_todo(final int todoID) throws Throwable {
        service.requestToDeleteATodo(todoID);
    }

    @Then("^I should see a todo deleted successfully$")
    public void i_should_see_a_todo_deleted_successfully() throws Throwable {
        service.assertResponseCode200();
    }
}
