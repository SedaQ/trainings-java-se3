# trainings-java-se3
This is the parent project representing several technologies in Java back-end development including REST, SOAP, JDBC, JPA etc. It is divided into several modules.

## Authors

Email        | Name 
------------ | -------------
pavelseda@email.cz | Šeda Pavel

## Documentation for REST API
Documentation is done in Swagger framework. It is possible to reach it on the following page:

```
~/jprog2-rest/api/v1/swagger-ui.html
```

e.g. on localhost it should be:

```
http://localhost:8080/jprog2-rest/api/v1/swagger-ui.html
```

NOTE: please note that client for that REST API could be generated using [Swagger codegen] (https://swagger.io/tools/swagger-codegen/). It is crucial to annotate each RestController method properly!


## Database migration
Prerequisities running PostgreSQL and created database named 'jprog2' with schema 'public'.
To migrate database data it is necessary to run these two scripts:

```
$ mvn flyway:migrate -Djdbc.url=jdbc:postgresql://{url to DB}/jprog2 -Djdbc.username={username in DB} -Djdbc.password={password to DB}
```
e.g.:
```
$ mvn flyway:migrate -Djdbc.url=jdbc:postgresql://localhost:5432/jprog2 -Djdbc.username=postgres -Djdbc.password=postgre

```

NOTE: This script must be run in [jprog2-jpa] (https://github.com/SedaQ/trainings-java-se3/tree/master/jprog2-jpa) module.

## Startin up Project
Installing project by maven (run in root directory):

```
$ mvn clean install
```

Running war projects (REST, SOAP) from cmd.

```
$ mvn spring-boot:run
```

## Used Technologies
The project was built and tested with these technologies, so if you have any unexpected troubles let us know.

```
Maven         : 3.3.9
Java          : jdk-10.0.2, vendor: OpenJDK
Spring boot   : 2.0.4.RELEASE
Swagger       : 2.8.0
Hibernate     : 5+
Jackson       : 2.9.6
Tomcat        : 8
PostgreSQL    : 10
```


