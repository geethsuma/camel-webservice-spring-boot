# camel-webservice-spring-boot
This example shows how to work with a simple Apache Camel WebService using Spring Boot.


This example can be run using Spring-Boot from the command line with 

    mvn spring-boot:run
    
The wsdl endpoint can be accessed at http://localhost:8080/cxf/camel-example/ProductService?wsdl and can be tested using SoapUI Client.

This example also has a unit test which explains how a camel endpoint can be changed dynamically at test execution time. The unit test covers the end to end testing by replacing cxf endpoint with direct component.

##adviceWith

Sometimes, you need to configure the route flow to accomodate a specific test scenrio. For example, this is good if you want to 

    - replace your CXF endpoint for test
    - test only the to endpoints inside a route and skip other endpoints
    - completely remove some endpoints from test
    - add a new endpoint in the route for testing the output
    - mock the endpoints so that the message does not get sent to external endpoint and is returned back in the JUnit context.
    
 
