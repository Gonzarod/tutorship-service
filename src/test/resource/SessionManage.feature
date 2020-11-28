Feature:  User can view open sessions
  Scenario: User Student successfully view open sessions
    Given Student with a username "jesus.student"
    When go to session option and call to api is made
    Then response status is 200
    And all session data is retrieved

  Scenario: User Teacher successfully view open sessions
    Given Teacher with a username "albert.teacher"
    When go to session option and call to api is made
    Then response status is 200
    And all session data is retrieved

  Scenario: Admin successfully view open sessions
    Given Admin with username "jose.admin"
    When go to session option and call to api is made
    Then response status is 200
    And all session data is retrieved