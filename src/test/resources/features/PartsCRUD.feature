# [ SETUP ]
# In order for these tests to work, the following changes to the code are needed:
# - Part (entity) ID's @GeneratedValue annotation is removed

Feature: CRUD operations on Part Repository

  Background:
    Given an empty Part Repository

  Scenario Outline: CREATE a Part
    Given an existing Part Repository with
      | id_input   | description_input   | price_input   |
      | <id_input> | <description_input> | <price_input> |
    And a Part DTO with
      | id_input | description_input | price_input |
      | 1        | new item 1        | 100         |
    When save Part to Part Repository
    Then expect Part Repository with
      | id_output   | description_output   | price_output   |
      | <id_output> | <description_output> | <price_output> |

    Examples:
      # 1. VALID - Empty repo
      # 2. VALID - Existing repo
      # 3. INVALID - Existing Part Description
      # 4. INVALID - Existing Part ID + Description
      # 5. VALID - Existing Part ID, however, ID is auto-generated
      | id_input | description_input | price_input | id_output | description_output | price_output |
      |          |                   |             | 1         | new item 1         | 100          |
      | 2        | item 2            | 200         | 1,2       | new item 1,item 2  | 100,200      |
      | 2        | new item 1        | 200         | 2         | new item 1         | 200          |
      | 1        | new item 1        | 100         | 1         | new item 1         | 100          |
      | 1        | item 1            | 200         | 1,2       | new item 1,item 1  | 100,200      |


  Scenario Outline: READ from Part Repository
    Given an existing Part Repository with
      | id_input   | description_input   | price_input   |
      | <id_input> | <description_input> | <price_input> |
    And a Part DTO with
      | id_input | description_input | price_input |
      | 4        | new item 4        | 400         |
    When find Part from Part Repository by description
    Then expect Part returned with
      | id_output   | description_output   | price_output   |
      | <id_output> | <description_output> | <price_output> |

    Examples:
      # 1. VALID - Existing Part
      # 2. INVALID - Empty repo
      # 3. INVALID  - Missing Part with description
      | id_input | description_input | price_input | id_output | description_output | price_output |
      | 4        | new item 4        | 400         | 4         | new item 4         | 400          |
      |          |                   |             |           |                    |              |
      | 1        | item 1            | 100         |           |                    |              |


  Scenario Outline: UPDATE a Part
    Given an existing Part Repository with
      | id_input   | description_input   | price_input   |
      | <id_input> | <description_input> | <price_input> |
    And a Part DTO with
      | id_input | description_input | price_input |
      | 4        | new item 4        | 400         |
    And an updated Part DTO with
      | id_input | description_input | price_input |
      | 4        | updated item 4    | 4000        |
    When update Part from updated Part DTO
    Then expect Part Repository with
      | id_output   | description_output   | price_output   |
      | <id_output> | <description_output> | <price_output> |

    Examples:
      # 1. VALID - Existing Part
      # 2. INVALID - Empty Part Repository
      # 3. INVALID - Missing Part with description
      | id_input | description_input | price_input | id_output | description_output | price_output |
      | 4        | new item 4        | 400         | 4         | updated item 4     | 4000         |
      |          |                   |             |           |                    |              |
      | 1        | item 1            | 100         | 1         | item 1             | 100          |


  Scenario Outline: DELETE a Part
    Given an existing Part Repository with
      | id_input   | description_input   | price_input   |
      | <id_input> | <description_input> | <price_input> |
    And a Part DTO with
      | id_input | description_input | price_input |
      | 4        | new item 4  | 400   |
    When delete Part from Part Repository
    Then expect Part Repository with
      | id_output   | description_output   | price_output   |
      | <id_output> | <description_output> | <price_output> |

    Examples:
      # 1. VALID - Existing Part
      # 2. INVALID - Empty Part Repository
      # 3. INVALID - Missing Part
      | id_input | description_input | price_input | id_output | description_output | price_output |
      | 4        | new item 4        | 400         |           |                    |              |
      |          |                   |             |           |                    |              |
      | 1        | item 1            | 100         | 1         | item 1             | 100          |