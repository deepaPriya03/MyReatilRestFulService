# MyReatilRestFulService
#Problem Statement
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions: 
>> Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

>> Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
	Example: http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

>> Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.

>> BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.
#TechStack
MySQl
Java
Spring boot

#Steps to run the spring boot app
1.  Make Sure you have java and any Ide would do good(eclipse,itellij)
2.  Install My Sql 
3.  Install git bash and clone project https://github.com/deepaPriya03/MyReatilRestFulService.git
4.  Import the project as existing maven project in your IDE 
5.  Do clean build install the project this would download you dependecies 
6.  Mention you db username and password in src/main/resources/application.porperties under spring.db.password=%yourpass%
spring.db.username=%yourusername%
7.  Create schema in you db as targetexercise and table as products with column id, name, value and currency_code
8.  Insert data or run script as in MyRetailRestFulServiceData.sql 
9.  Run your Spring boot app
10. Access the Below application URLthrough Postman 
    http://localhost:8100/retail/getProductDetails/{id} 
    http://localhost:8100/retail/retrieveProductName?id={id}
    http://localhost:8100/retail/updateProductPrice/{id}?value={value} 
    http://localhost:8100/retail/readPricingInformation?id={id} 
