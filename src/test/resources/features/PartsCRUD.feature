Feature: CRUD operations on Part Repository

  Scenario Outline: CREATE a Part
    Given an existing Part Repository with
        | id_input   | description_input   | price_input   |
        | <id_input> | <description_input> | <price_input> |
      And a Part DTO with
          | id_input | description_input | price_input |
          | 1        | new item 1        | 100         |
    When save Part to Part Repository
    Then expect Part Repository with
      | id_output           | description_output           | price_output            |
      | <id_output>  | <description_output>  | <price_output>   |

    Examples:
    | id_input | description_input | price_input | id_output | description_output | price_output |
    # VALID INPUTS
    |          |                   |             | 1         | new item 1         | 100          |
    | 2        | item 2            | 200         | 1,2       | new item 1,item 2  | 100,200      |
    # INVALID INPUTS - Existing description
    | 2        | new item 1        | 200         | 2         | new item 1         | 200          |
    # INVALID INPUTS - Existing entry
    | 1        | new item 1        | 100         | 1         | new item 1         | 100          |
     # INVALID INPUTS - Existing ID
    | 1        | item 1            | 200         | 1         | item 1             | 200          |

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
