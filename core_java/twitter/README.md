# Introduction

The Twitter CLI application allows users to create a tweet with a geotag via Twitter REST API and output the created tweet object in JSON format. 
This application throws an exception in case the length of the tweet is over 140 characters ,or the geo tag format is invalid (For example, the latitude or longitude is out of range).
Through this MVC (minus V) based application, the user can also lookup or delete a tweet by ID as well as sending an error message if the provided ID is invalid.

Implementing this application is a great practice to gain valuable hands-on experience and better understanding of concepts below:
- Different design patterns(DTO, DAO,CRUD and MVC)
- Various Java libraries (HTTP Client, JSON serializer/deserializer) 
- Testing frameworks like Junit and Mockito
- Spring frameworks
# Quick Start

## Package the app using mvn
1. The first step is to set Twitter authorization keys (consumerKey, consumerSecret, accessToken, TokenSecret) as environment variables.
2. The following command line builds the package : ``mvn package`` 
3. The following command line runs this application: <br />
``java -jar target/ twitter-1.0-SNAPSHOT.jar [post|show|delete] [arguments]``

## Running the app
- Post a tweet: ``twitterCLIApp post "sample text" "Longitude:Latitude"``
- Show a tweet: ``twitterCLIApp show "ID" "sample text"``
- Delete a tweet: ``twitterCLIApp delete "ID" "sample text" ``

# Design
## UML diagram

![UML Diagram](./assets/UMLDiagram.png)

The application consists of the following components:

- Controller Layer

    The controller layer interacts and parsers user input (CLI arguments). It also calls the service layer and returns results.

- Service Layer

    The service layer handles business logic and calls DAO layer in order to interact with the underlying storage/service which is the Twitter REST API in this app.

- Data Access Layer (DAO)

    The data access layer handles models (implemented with POJOs). In this app, the DAO layer posts/shows/deletes tweet(s).

# Spring

In the TwitterCLIApp, the `main` method is used to create all components and the dependencies are setup manually. Since it will become extremely difficult to manage  dozens of components that each might require more than one dependency,
Spring framework is used in this app. 

### Beans approach
- It defines dependency relationship through `@Bean` and passes dependencies through method arguments.
- The IoC container/context will automatically instantiate all Beans base on the relationship specified in the step one.
- The user should start the program from the main entry point (TwitterCLIApp).
### ComponentScan approach
Although the `@Bean` approach replaced the traditional `main` method dependency management, it still requires a lot of manual work which requires developers to specify Beans and dependencies one by one. 
When an interface is implemented, the dependencies will pass through constructors. This approach takes an advantage of this and eliminates all manual work.

For example, `TwitterController` depends on Service. Therefore, `@Autowired` annotation is used to tell IoC container to inject dependency through the constructor. Moreover, `@Controller` (`@org.springframework.stereotype.Controller` for preventing naming conflict) annotation is used to identifies this class as a bean for IoC container and be managed by IoC container.

###SpringBoot approach
Spring Boot is basically an extension of the Spring framework, which eliminates the boilerplate configurations required for setting up a Spring application. In addition, Spring Boot also comes with a default web servlet.

#Models
Models are implemented with POJOs, which is a class with private member variables and public getter and setters. This class encapsulates Tweet data, which often is displayed in JSON format.  A sample Json output used in the application: 
````{
  "created_at" : "Thu Nov 28 20:11:33 +0000 2019",
  "id" : 1200145224103841792,
  "id_str" : "1200145224103841792",
  "text" : "test post",
  "entities" : {
    "hashtags" : [ ],
    "user_mentions" : [ ]
  },
  "retweet_count" : 0,
  "favorite_count" : 0,
  "favorited" : false,
  "retweeted" : false
}
````
The Model contains of five objects: Tweet, Coordines, Entities, Hashtag, and UserMention.

# Improvements
1. Adding a new feature for updating tweets.
2. An additional feature that the user can post images as well.
3. Adding an option that tweets could be found from the posted time.