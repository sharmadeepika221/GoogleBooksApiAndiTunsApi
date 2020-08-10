
# Google Books and iTunes Albums using GraphQL

This is a spring boot and Angular 8 application to fetch the books and albums by input string 
using the Google Books API and iTunes API service.

Using the GraphQL to fetch sub parts from  the JSON response returned from the external APIs.


##### GraphQL
Using the GraphQL schema 
 we return specific details of the book/Album which user has mentioned in the search.graphql file 
 
 ----
 The Important GraphQL schema is mentioned as below
 
 ```$xslt
schema {
  query: Query
}

type Query{
    searchByString(title: String): [SearchResponse]
}

type SearchResponse {
    title: String
    authors: String
    itemName: String
}
```


## Prerequisites
The project assumes that following packages are installed to before using it
```
Java 8, Maven, Angular 8, npm
```

## Steps to run the project

1. Clone the git repo to your local directory

2. cd to that directory and run the below command
```bash
./mvnw spring-boot:run
```
This will install all the necessary dependencies for the backend
and starts the application at http://localhost:8080

3.Now cd to "client" directory and run
```bash
npm install
```
This will install all the required node packages

4.Start the frontend using
```bash
ng serve
```
Now navigate to the link http://localhost:4200

By default the books/albums will be searched using the GraphQL implementation.

The REST API details are mentioned in the below url . The port used is 8080.
```
        Swagger Ui url --- http://localhost:8080/swagger-ui.html#/
        metrics url --- http://localhost:8080/actuator/metrics
        prometheus url --- http://localhost:8080/actuator/prometheus
```
## Endpoints :
* /retrieveResult/{searchString}           --To search related books and albums from Google Books API and iTunes API for the passed input string
* /customMetric                         --To expose time taken by request and request status