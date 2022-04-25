# curl requests

- GET - `curl http://localhost:8080/api/v1/student/840915ae-9c0d-4f8d-b546-db037c9787a9 -v`
- POST- `curl -X POST -H "Content-type:application/json" http://localhost:8080/api/v1/student -d '{"firstName":"Željko","lastName":"Brdarić","age":33,"subject":"Programiranje 2","grade":3}' -v`
- PUT - `curl -X PUT -H "Content-type:application/json" http://localhost:8080/api/v1/student -d '{"id":"840915ae-9c0d-4f8d-b546-db037c9787a9","firstName":"Tomislav","lastName":"Jakopec","age":38,"subject":"Programiranje 2","grade":1}' -v`
- DELETE - `curl -X DELETE -H "Content-type:application/json" http://localhost:8080/api/v1/student/840915ae-9c0d-4f8d-b546-db037c9787a9 -v`

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.7/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.7/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

