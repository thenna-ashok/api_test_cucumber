Feature: Todo's API test exercise verification using BDD Framework

  # Retrieve all todos
  Scenario: Retrieve list of all todos
    Given I need to access the todos rest api
    When I retrieve the list of all todos
    Then I see the todo with title "delectus aut autem"

  # Retrieve a specific todos
  Scenario: Retrieve a todo for a specific id
    Given I need to access the todos rest api
    When I retrieve a todo for an id 25
    Then I see a todo with an id "25"

  # Post a new todos
  Scenario: Post a new todo
    Given I need to access the todos rest api
    When I post a todo with an id 203 and title "foo" and body "bar"
    Then I should see a todo created successfully

  # Update a todos
  Scenario: Update a todo
    Given I need to access the todos rest api
    When I update a todo with an id 25 and title "foo" and body "bar"
    Then I should see a todo updated successfully

  # Delete a todos
  Scenario: Delete a todo
    Given I need to access the todos rest api
    When I delete a todo with an id 25
    Then I should see a todo deleted successfully