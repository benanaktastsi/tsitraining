Feature: CRUD operations on Part Repository

  Scenario: CREATE a Part
    Given a Part DTO with
        | id | description | price |
        | 1  | new item 1  | 100   |
    When save Part to Part Repository
    Then expect Part Repository with
      | id | description | price |
      | 1  | new item 1  | 100   |


  Scenario: READ from Part Repository
    Given an existing Part Repository with
      | id | description | price |
      | 1  | item 1      | 100   |
      | 2  |item 2      | 200   |
      | 3  |item 3      | 300   |
      | 4  |new item 4  | 400   |
    And a Part DTO with
      | id | description | price |
      | 4  | new item 4  | 400   |
    When find Part from Part Repository by description
    Then expect Part returned with
      | id | description | price |
      | 4  | new item 4  | 400   |


  Scenario: UPDATE a Part
    Given an existing Part Repository with
      | id | description | price |
      | 1  | item 1      | 100   |
      | 2  | item 2      | 200   |
      | 3  | item 3      | 300   |
      | 4  | new item 4  | 400   |
    And a Part DTO with
      | id | description | price |
      | 4  | new item 4  | 400   |
    And an updated Part DTO with
      | id | description | price |
      | 4  | updated item 4  | 4000   |
    When update Part from updated Part DTO
    Then expect Part Repository with
      | id | description | price |
      | 1  | item 1      | 100   |
      | 2  | item 2      | 200   |
      | 3  | item 3      | 300   |
      | 4  | updated item 4  | 4000   |


  Scenario: DELETE a Part
    Given an existing Part Repository with
      | id | description | price |
      | 1  | item 1      | 100   |
      | 2  | item 2      | 200   |
      | 3  | item 3      | 300   |
      | 4  | new item 4  | 400   |
    And a Part DTO with
      | id | description | price |
      | 4  | new item 4  | 400   |
    When delete Part from Part Repository
    Then expect Part Repository with
      | id | description | price |
      | 1  | item 1      | 100   |
      | 2  | item 2      | 200   |
      | 3  | item 3      | 300   |
