# and-phone-api

Small phone API

Current endpoints:

* GET /api/phone - list all phones
* GET /api/customer/{customerId}/phones - list all phones for customer
* POST /api/phone/{phoneId}/activate - activate a phone

---

This app uses [Maven](https://maven.apache.org/download.cgi) , so make sure you have it installed.

Navigate to project dir iy you want to run the following.

To run tests:
`mvn test`

To run the application:
`mvn spring-boot:run`
or
`mvn package && java -jar target/and-phone-api-0.0.1-SNAPSHOT.jar`

The app will run on port 8080 with some predefined data.
To test the endpoints you can view them in browser for GET request,
but for activating a phone i recommend using [Postman](https://www.getpostman.com/downloads/)

Some example of requests are:
* GET [http:/localhost:8080/api/phone](http:/localhost:8080/api/phone)
* GET [localhost:8080/api/customer/2/phones](http:/localhost:8080/api/phone)
* POST localhost:8080/api/phone/4/activate