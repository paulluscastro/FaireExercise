# **Faire Backend Exercise**

Project made to attend Faire's challenge on using Faire's API.
___
### Faire Backend Exercise
 Using the **Faire API**, you will write a program that accepts an API key as a single command line parameter, which should be sent with all requests in the X-FAIRE-ACCESS-TOKEN header, and does the following:
- Consumes all products for a given brand*2, recording the inventory levels for each product option
- Consumes all orders, accepting the order if there is inventory to fulfill the order and it is not already accepted, otherwise it marks the items that don’t have enough inventory as backordered
- Update the inventory levels of product options as each order is moved to processing
- After all orders have been read, the program should print the following metrics, plus 2 of your choosing, and then exit - your creativity is welcome!-
	-   The best selling product option
	-   The largest order by dollar amount
	-   The state with the most orders

### How to run
To run the project you will need:
- Java 1.8
- Maven

Download the contents and run the application with the following command:
`mvn clean compile exec:java -Dkey=<KEY>`

Where **KEY** is the API Key provided by Faire.
