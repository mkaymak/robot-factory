# Robot Factory Code Challenge

This is a web project for create an order of user requested robot components

### What's Inside?
* Spring Boot,
* RESTful services,
* Lombok,
* Swagger,
* JUnit,
* AssertJ,
* Mockito (to mock dependencies)
* MockMvc (to test controllers)

### Problem Statement
When ordering a robot, a customer must configure the following parts:
- Face (Humanoid, LCD or Steampunk)
- Material (Bioplastic or Metallic)
- Arms (Hands or Grippers)
- Mobility (Wheels, Legs or Tracks)

An order will be valid if it contains one, and only one, part of face, material, arms and mobility.

If an order is valid and there are enough parts to assemble the robot:
- The priced order should be calculated
- The stock must be adjusted to reflect the fact that parts are being used in robot manufacturing.

### How to run?
* Because of Spring boot embedded tomcat, the project could be started easily. <br />
  * Project uses default 8080 port

### REST Services
* REST services documentation can be found at: http://localhost:8080/swagger-ui/index.html#/ 
* `POST /api/robot-factory/orders`
    * Body of the request:  `{ "components": ["I","A","D","F"] }`
    * Result is an order id and the total price of the components
      and if the components are not compatible with the given rules, <br /> 
      the order cannot be created and appropriate HTTP status will be sent (like Bad Request or Internal Server Error)
      * A successful result: `201 {"order_id": "some-id", "total": 160.11 }`

### Design Decisions
* Chain of Responsibility design pattern was applied for determining whether the requested component codes are applicable.
  <br /> If one of the rules cannot be applied (e.g number of components is not 4), it throws the related exception and the chain could not continue.
* Global exception handling by the help of AOP for reusability.
* For extending testing in a variety of scenarios, parameterized tests were used.
* For each rule, a custom exception was created.
* HashMaps were used instead of a database (persistence was not included in the scope of the challenge ) <br /> 
  for storing the orders and robot component information.
* EnumSet was used for determining whether there is exactly one component for each type.
