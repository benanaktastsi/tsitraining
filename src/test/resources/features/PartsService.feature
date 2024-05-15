Feature: PartService business logic cases

  Scenario: Create Part
    Given an existing Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
      And a PartDTO with (PartsService.feature)
        | id | description | price |
        | 4 | new item 4 | 400 |
    When create Part (PartsService.feature)
    Then expect returned PartDTO with (PartsService.feature)
    | id | description | price |
    | 4 | new item 4 | 400     |
      And expect a Part Repository with (PartsService.feature)
        | id | description | price |
        | 1 | item 1 | 100 |
        | 2 | item 2 | 200 |
        | 3 | item 3 | 300 |
        | 4 | new item 4 | 400 |


  Scenario: Get Part by ID
    Given an existing Part Repository with
    | id | description | price |
    | 1 | item 1 | 100 |
    | 2 | item 2 | 200 |
    | 3 | item 3 | 300 |
      And an ID input
      | id |
      | 1 |
    When get Part by ID
    Then expect returned PartDTO with
    | id | description | price |
    | 1  | item 1      | 100   |


  Scenario: Get all Parts
    Given an existing Part Repository with
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
    When get all Parts
    Then expect returned PartDTO list with
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |


  Scenario: Update Part
    Given an existing Part Repository with
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
    And an ID input
      | id |
      | 1 |
    And a PartDTO with
      | id | description | price |
      | 1 | updated item 1 | 1000 |
    When update Part
    Then expect returned PartDTO with
      | id | description | price |
      | 1 | new item 1 | 100     |
      And expect a Part Repository with
        | id | description | price |
        | 1 | item 1 | 100 |
        | 2 | item 2 | 200 |
        | 3 | item 3 | 300 |
        | 4 | new item 4 | 400 |


  Scenario: Delete Part
    Given an existing Part Repository with
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
      | 4 | new item 4 | 400 |
    And an ID input
      | id |
      | 4 |
    When delete Part
    Then expect a Part Repository with
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |

