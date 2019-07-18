# Eversay 🐷 [![Build Status](https://travis-ci.com/grace000/erversay.svg?branch=master)](https://travis-ci.com/grace000/erversay)

An HTTP Server

-----

## Setup

#### Requirements
- Java 12 (I followed the instructions provided from [Oracle](hhttps://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html))
- Gradle 5.4.1 (Instructions available [here](https://gradle.org/install/))

### Getting Started
1 - Clone this repo to your local machine: https://github.com/grace000/erversay.git
```
$ git clone https://github.com/grace000/erversay.git

```
2 - Navigate to the cloned project's root folder and run the following command to create a package:
 
 ```
 $ ./gradlew jar
 ```
 3 - Run the following to build the project:
 ```
 $ ./gradlew build
 ```
 
 ### Using It
 
 Start the server on a port (i.e. 5000)
 ```
 $ java -jar build/libs/httpserver.jar 5000
 ```
 
 Use a client such as your browser or [Postman](https://www.getpostman.com/). You can also type in cURL requests directly into your terminal. Demo the features by adding pre-setup paths after your host (ex: localhost:5000/simple_get).
 The server currently supports the following methods:
 
 1. GET request (/simple_get)
 2. HEAD request (/simple_get)
 3. OPTIONS request(/method_options and /method_options2)
 4. POST request(/echo_body), note: you'll need to add a body of text to your request
 
 Sending a GET request to /get_with_body will incur a response with the requests allowed on that path. Sending a request to any other path will invoke a not found response.
 
 ### Running Tests
 In the terminal window, navigate to the project's root folder and run the following command:
 ```
 $ gradle test
 ```
 
