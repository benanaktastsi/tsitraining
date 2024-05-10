Feature: CRUD operations on Part Repository

  Scenario: CREATE a Part
    Given an existing Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      And a Part DTO with
        | description | price |
        | new item 4  | 400   |
    When create Part from Part DTO
    Then expect Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      | new item 4  | 400   |


  Scenario: READ from Part Repository
    Given an existing Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      | new item 4  | 400   |
    And a Part DTO with
      | description | price |
      | new item 4  | 400   |
    When find Part from Part Repository
    Then expect Part returned with
      | description | price |
      | new item 4  | 400   |


  Scenario: UPDATE a Part
    Given an existing Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      | new item 4  | 400   |
    And a Part DTO with
      | description | price |
      | new item 4  | 400   |
    And an updated Part DTO with
      | description | price |
      |  updated item 4 | 4000   |
    When update Part from updated Part DTO
    Then expect Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      | updated item 4  | 4000   |


  Scenario: DELETE a Part
    Given an existing Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
      | new item 4  | 400   |
    And a Part DTO with
      | description | price |
      | new item 4  | 400   |
    When delete Part from Part Repository
    Then expect Part Repository with
      | description | price |
      | item 1      | 100   |
      | item 2      | 200   |
      | item 3      | 300   |
