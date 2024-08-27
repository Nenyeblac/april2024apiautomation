#@TestToRun

Feature: Social Networking
  As a user
  I want to be able to use the service - JSONPlaceholder - Free FAKE Rest API
  so that I can create posts or comments or Users

  Scenario Outline: Get a specific post
    Given service JSONPlaceholder is up and running
    When I send GET request to get a specific post with "<id>"
    Then the specific post details "<id>" "<title>" and "<body>" are returned with status code of 200
    Examples:
      | id | title        | body                                                                                                                                                                                                              |
      | 2  | qui est esse | est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla |


#  @TestToRun
  Scenario Outline: Create a new post
    Given service JSONPlaceholder is up and running
    When I send a POST request with the following detail "<userId>", "<title>" and "<body>"
    Then I should get a response with status code of 201 and the following "<userId>", "<title>" and "<body>"
    Examples:
      | userId | title             | body                             |
      | 2      | My recent holiday | I enjoyed my holiday. It was fun |


#  @TestToRun
  Scenario Outline: Update a post
    Given service JSONPlaceholder is up and running
    When I update an existing post with "<id>" using the following detail "<userId>", "<title>" and "<body>"
    Then I should get a response with status code of 200 and the following "<userId>", "<title>" and "<body>" for put
    Examples:
      | id | userId | title             | body                             |
      | 1  | 2      | My recent holiday | I enjoyed my holiday. It was fun |


  @TestToRun
  Scenario Outline: Delete a specific post
    Given service JSONPlaceholder is up and running
    When I send DELETE request for a specific post with "<id>"
    Then the status code of 200 is returned
    Examples:
      | id |
      | 2  |
