### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years

### Readme by candidates from here

#### what I have done:

- controllers:
  - use @RequiredArgsConstructor to replace @Autowired to not use field injection
    - Using @Autowired directly in classes creates a tighter coupling and decreases testability
  - set response status by using @ResponseStatus
  - add javadoc
  - add dto for the request body and response rather then using Employee object directly
  - move System.out calls to service layer and replace with log.info to improve the logging
  - add ApiExceptionHandler to handle custom exceptions
- services: 
  -  use @RequiredArgsConstructor to replace @Autowired to not use field injection
    - Using @Autowired directly in classes creates a tighter coupling and decreases testability
  - add javadoc
  - add log to keep the information
  - add @Override annotation in EmployeeServiceImpl.java
- domain:
  - renamed entities folder to domain/model
  - add a new "employeeId" unique field in Employee.java
    - Since "id" is the auto generated primary key, the id value that user sends through request
    cannot be stored in this field. This field is created to store the id value the user sends.
  - add constructor in Employee.java
  - add several Exception classes to handle the error
- repositories
  - add custom methods to get / delete / find existence by "employeeId" but not primary key "id".
- test
  - modify the dependencies in pom.xml to use junit5
  - add success unit test cases for EmployeeController.java and EmployeeService.java

#### what I would do if I had more time:
- fix swagger documentation based on my changes
- add authentication
- add caching logic for database calls
- add unit tests for the rest
- add e2e tests


#### How to use the APIs?
The API specification has changed, and there are some difference with the swagger documentation provided.
The following shows how to access each endpoint, and also shows the results in different situations.

- get /api/v1/employees
  - When there is no employee in the database, it will return empty response.
    - command: 
      - curl http://localhost:8080/api/v1/employees
    - result: 
      - []
  - Post employees' data to the database first, and then it will return all employees' data.
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "name": "test1", "salary": 1000, "department": "dep1"}' http://localhost:8080/api/v1/employees
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 2, "name": "test2", "salary": 2000, "department": "dep2"}' http://localhost:8080/api/v1/employees
      - curl http://localhost:8080/api/v1/employees
    - result:
      - [{"id":1,"name":"test1","salary":1000,"department":"dep1"},{"id":2,"name":"test2","salary":2000,"department":"dep2"}]
- get /api/v1/employees/:employee_id
  - When there is no employee in the database, it will return 404 error.
    - command: 
      - curl http://localhost:8080/api/v1/employees/3
    - result:
      - {"message":"Employee is not found. EmployeeId : 3"}
  - When there is an employee in the database, it will return the employee's data.
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 3, "name": "test3", "salary": 3000, "department": "dep3"}' http://localhost:8080/api/v1/employees
      - curl http://localhost:8080/api/v1/employees/3
    - result:
      - {"id":3,"name":"test3","salary":3000,"department":"dep3"}
- post /api/v1/employees
  - If request body is set correctly, the data will be successfully posted.
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "name": "test4", "salary": 4000, "department": "dep4"}' http://localhost:8080/api/v1/employees
      - curl http://localhost:8080/api/v1/employees/4
    - result:
      - {"id":4,"name":"test4","salary":4000,"department":"dep4"}
  - If request body is not set correctly, the data cannot be posted.
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 5, "name": "test5", "salary": 5000, "departmen": "dep5"}' http://localhost:8080/api/v1/employees
    - result:
      - {"message":"Validation failed for argument [0] in public void jp.co.axa.apidemo.controllers.EmployeeController.saveEmployee(jp.co.axa.apidemo.controllers.dto.request.EmployeeRequest): [Field error in object 'employeeRequest' on field 'department': rejected value [null]; codes [NotNull.employeeRequest.department,NotNull.department,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [employeeRequest.department,department]; arguments []; default message [department]]; default message [department is not properly set.]] "}
- delete /api/v1/employees/:employee_id
  - If employee id exists, the data will be deleted correctly.
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 5, "name": "test5", "salary": 5000, "department": "dep5"}' http://localhost:8080/api/v1/employees
      - curl http://localhost:8080/api/v1/employees/5
      - curl -X DELETE http://localhost:8080/api/v1/employees/5
      - curl http://localhost:8080/api/v1/employees/5
    - result:
      - The second command returns: {"id":5,"name":"test5","salary":5000,"department":"dep5"}
      - The fourth command return: {"message":"Employee is not found. EmployeeId : 5"}
  - If employee id doesn't exist, it will return 404 error:
    - command:
      - curl -X DELETE http://localhost:8080/api/v1/employees/6
    - result:
      - {"message":"Employee is not found. EmployeeId : 6"}
- put /api/v1/employees/:employee_id
  - If employee id exists, the data will be deleted correctly
    - command:
      - curl -X POST -H "Content-Type: application/json" -d '{"id": 6, "name": "test6", "salary": 6000, "department": "dep6"}' http://localhost:8080/api/v1/employees
      - curl http://localhost:8080/api/v1/employees/6
      - curl -X PUT -H "Content-Type: application/json" -d '{"id": 6, "name": "test6change", "salary": 6000, "department": "dep6"}' http://localhost:8080/api/v1/employees/6
      - curl http://localhost:8080/api/v1/employees/6
    - result:
      - The second command returns: {"id":6,"name":"test6","salary":6000,"department":"dep6"}
      - The fourth command return: {"id":6,"name":"test6change","salary":6000,"department":"dep6"}
  - If employee id doesn't exist, it will return 404 error
    - command:
      -  curl -X PUT -H "Content-Type: application/json" -d '{"id": 7, "name": "test7change", "salary": 7000, "department": "dep7"}' http://localhost:8080/api/v1/employees/7
    - result:
      - {"message":"Employee is not found. EmployeeId: 7"}

#### My experience in Java

- I have 3 years experience in Java and Spring Boot
- I am mainly working with Java and Spring Boot in my current company
- I have developed Java/Spring Boot application from scratch and successfully released for commercial use