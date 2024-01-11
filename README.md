Test Plan:
Objective: To verify the functionality and reliability of the Users service API.
Scope: The test scope includes testing all CRUD operations (Add user, Get user (Returns the user with id),Get users (Returns the users list) Edit user, Delete user) for user management.
Test Scenarios:
Add a new user with valid data.
Retrieve a user by ID.
Retrieve the list of all users.
Update an existing user's details.
Delete a user by ID.
Verify error handling for invalid requests.
Test Data: Define test data for positive and negative test scenarios, including valid and invalid user data.
Testing Environment: Specify the testing environment, including the URL of the Users service and any necessary configurations.
Dependencies/Constraints: Identify any dependencies or constraints that may affect testing, such as external services or resources.

3. 
Edit users - wrong Status
java.lang.AssertionError: Expected status code 200 expected [200] but found [404]
Expected :200
Actual   :404

Delete user - wrong Status
Expected status code 200 expected [200] but found [500]
Expected :200
Actual   :500
