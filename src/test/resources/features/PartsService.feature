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
    Given an existing Part Repository with (PartsService.feature)
    | id | description | price |
    | 1 | item 1 | 100 |
    | 2 | item 2 | 200 |
    | 3 | item 3 | 300 |
      And an ID input 1 (PartsService.feature)
      # | id |
      # | 1 |
    When get Part by ID (PartsService.feature)
    Then expect returned PartDTO with (PartsService.feature)
    | id | description | price |
    | 1  | item 1      | 100   |


  Scenario: Get Part by description
    Given an existing Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
    And a description input "item 1" (PartsService.feature)
      # | description |
      # | item 1 |
    When get Part by description (PartsService.feature)
    Then expect returned PartDTO with (PartsService.feature)
      | id | description | price |
      | 1  | item 1      | 100   |


  Scenario: Get all Parts (PartsService.feature)
    Given an existing Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
    When get all Parts (PartsService.feature)
    Then expect returned PartDTO list with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |


  Scenario: Update Part
    Given an existing Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
    And an ID input 1 (PartsService.feature)
      # | id |
      # | 1 |
    And a PartDTO with (PartsService.feature)
      | id | description | price |
      | 1 | updated item 1 | 1000 |
    When update Part (PartsService.feature)
    Then expect returned PartDTO with (PartsService.feature)
      | id | description | price |
      | 1 | updated item 1 | 1000 |
      And expect a Part Repository with (PartsService.feature)
        | id | description | price |
        | 1 | updated item 1 | 1000 |
        | 2 | item 2 | 200 |
        | 3 | item 3 | 300 |


  Scenario: Delete Part
    Given an existing Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |
      | 4 | new item 4 | 400 |
    And an ID input 4 (PartsService.feature)
      # | id |
      #| 4 |
    When delete Part (PartsService.feature)
    Then expect a Part Repository with (PartsService.feature)
      | id | description | price |
      | 1 | item 1 | 100 |
      | 2 | item 2 | 200 |
      | 3 | item 3 | 300 |

