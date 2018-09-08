# trainings-java-se3


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
