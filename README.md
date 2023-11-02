# purchaseapp
API that stores a purchase

## Prerequisites
* [Java] 1.17+;
* [Gradle] 6/7+;


## Architecture

The project is design following the clean architecture concepts.

## Setting up local containers

The project has a docker-compose file that allows you to set up containers on a service network of the database, sonarqube and later on other services.To run the the docker, with sudo permissions
on Linux, use the following command:

```shell
docker-compose up --build -d
```

**Rising up an specific service**

```shell
docker-compose up [ service_name_01 | service_name_02] --build -d
```

**Stop all the containers**

```shell
docker-compose down
```

**Another commands**

To delete images/containers/volumes/network not used.

```shell
docker system prune -f && docker network prune -f && docker image prune -a -f && docker volume prune -f
```

**Note:** The following data are the default configuration to access the database:
* URL: ``` jdbc:postgres:thin@//localhost:5432/postgres ```
* POSTGRES_USER: PURCHASE_ACCOUNTING_ADMIN
* POSTGRES_PASSWORD: PURCHASE_ACCOUNTING_LOCAL

## Setting up the database's tables

This project runs the migrations automatically but to run the migrations by yourself, to do so use the following command:

```shell
./gradlew flywayMigrate -DServer=local_database
```

## Setting up the profile in IntelliJ

To set the profile it is necessary to use the following:

```
-Dspring.profiles.active=<profile_name>
```

## Run on terminal

To run on terminal it is necessary to use the following command:


```
./gradlew bootRun
```

You can set up and environment either:

```
./gradlew bootRun --args='--spring.profiles.active=<profile_name>'
```

## Swagger

Swagger is set up to be access in {HOST}/swagger-ui/index.html
