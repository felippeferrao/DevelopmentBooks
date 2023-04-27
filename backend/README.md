# DevelopmentBooks
Development Books - Full Solution API

This RESTAPI was developed to Accomplish the KATA described under the following link: https://github.com/stephane-genicot/katas/blob/master/DevelopmentBooks.md

Short Description of the solution:

There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books (5 books available in total). 

Rules
The rules are described below :
One copy of the five books costs 50 EUR.

 - If, however, you buy two different books from the series, you get a 5% discount on those two books.
 - If you buy 3 different books, you get a 10% discount.
 - With 4 different books, you get a 20% discount.
 - If you go for the whole hog, and buy all 5, you get a huge 25% discount.

Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.

Developers seeking to deliver quality products are queueing up with shopping baskets overflowing with these books. Your mission is to write a piece of code to calculate the price of any conceivable shopping basket.

Please read the following steps to setup the project in your local machine.


# **Getting Started**

The following project is deployed with the following features:

- Java 11
- Spring Boot 2.7
- Spring Security with Bearer token
- Spring Data to persistence the data with H2 as database.
- Spring Fox with Swagger to generate the API documentation
- Docker as a container
