Feature: Login feature

  @failLogin
  Scenario: Invalid User
    Given The User is On the Login Screen
    When User log in using <username> and <password>
    Then Login should fail with displaying error message of invalid combination
    Examples:
    | username   |  password  |
    | testuser_1 | P@ssw0rd_3 |
    | testuser_2 | P@ssw0rd_1 |
    | testuser_3 | P@ssw0rd_2 |
    | testuser_4 | P@ssw0rd_4 |
    
  @failLogin
  Scenario: UserName is Missing
    Given The User is On the Login Screen
    When User log in using "" and ""
    Then Login should fail with displaying error message of username required
    
  @failLogin
  Scenario: Password is Missing
    Given The User is On the Login Screen
    When User log in using "example@domain.com" and ""
    Then Login should fail with displaying error message of password required
    
  @successLogin
  Scenario: User Log in Successfully
  	Given The User is On the Login Screen
  	When User log in using "standard_user" and "secret_sauce"
  	Then User should login successfully
    