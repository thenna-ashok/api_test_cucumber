package todos.exercise.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import todos.exercise.cucumber.TodosDefinition;

public class TodosSteps {

    TodosDefinition service;

    @Given("^I need to access the todos rest api$")
    public void i_need_to_access_the_todos_api() throws Throwable {
        service = new TodosDefinition();
    }


    @When("^I retrieve the list of all todos$")
    public void i_retrieve_the_list_of_all_todos() throws Throwable {
        service.requestToRetrieveAllTodos();
    }
}
