# Nearby shops API Presentation

### ** steps to run the project :
*   import the two collections "city" and "shop" that you can find in the folder "Database_Collections", to a mongoDB database named "nearbyShopDB".
*   to change MongoDD information (host, username, password, ...) you can go to the file 'src/main/resources/application.yml'.
*   in CMD go to the project folder and run the command " `mvn spring-boot:run` " to start the application.
*   you can start the application also from an integrated development environment (Eclipse, IntelliJ, ...) by running the class "ApiNearbyShopsApplication" 

### I) Features :
* <Strong>create user : </Strong>(no need for authentication) allows the creation of new user. it does not allows the creation of two account with the same email.
* <Strong>authenticate : </Strong>(no need for authentication) allows user to authenticate and generate jwt token and sends it as response.
* <Strong>check token validity : </Strong>(needs authentication) check the token validity and returns true if the token is valid and false if not.
* <Strong>get nearby shops using default user location: </Strong>(needs authentication) returns nearby shops sorted by distance to the default location of the authenticated user that he gives when he create his account the first time.
  (Disliked shop won’t be returned within “Nearby Shops” list during the next 2 hours, favorites shops won't be returned)  
* <Strong>get nearby shops using custom location : </Strong>(needs authentication) returns nearby shops to a custom location received with the request.
  (Disliked shop won’t be returned within “Nearby Shops” list during the next 2 hours, favorites shops won't be returned) 
* <Strong>get favorites shops : </Strong>(needs authentication) returns favorites shops that received positive reaction(like) from the authenticated user.
* <Strong>add reaction about a shop : </Strong>(needs authentication) allows to add negative(dislike) or positive(like) reaction about a shop
* <Strong>remove shop from favorites shops : </Strong>(needs authentication) remove a shop from favorites shops of the authenticated user

### II) Used technologies :
####  1) development : (Framework Spring Boot)
* <Strong>Spring web : </Strong>Starter for building web applications using Spring MVC. It uses Tomcat as the default embedded container.
* <Strong>Spring Security : </Strong> provides authentication, authorization, and protection against common attacks.
* <Strong>Spring data Mongodb :</Strong> includes repository support for MongoDB and facilitate the interaction with mongodb databases.
####  2) database :
*   MongoDB
####  3) tests :
* <Strong>JUnit : </Strong>is a unit testing framework for the Java programming language.
* <Strong>Mockito : </Strong> is an open source testing framework for Java that allows the test of each functionality independently from the others.

### III) Project structure :

* <Strong> security.configuration Package :</Strong> contains components used to configure and manage the security of the API.
* <Strong> model Package :</Strong> contains entity classes.
* <Strong> repository Package :</Strong> contains a repository for each one of the documents (city, shop and user) saved in the data base it contains also an other package that contains a custom repository and its implementation that allows to write custom query.
* <Strong> service Package : </Strong> contains services that calls repository to retrieve data or apply changes data in the data base, it prepare the data to be sent as a response.
* <Strong> controller Package : </Strong> contains rest controllers of the api.
* <Strong> exception Package : </Strong> contains exception classes.



