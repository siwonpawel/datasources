# Demo project of usage Spring's [AbstractRoutingDataSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/datasource/lookup/AbstractRoutingDataSource.html)

## How to run

Scripts should be executed from project's root directory.

### Start environment database

```shell
docker-compose up -d -f src/main/docker/mariadb/docker-compose.yml
```

### Start project

```shell
mvn spring-boot:run
```

### Initial setup overview

| Configuration         | Value |
|-----------------------|-------|
| Database port         | 3333  |
| Database username     | root  |
| Database password     | pw    |
| Spring WebServer port | 8080  |

Basic Insomnia collection of HTTP requests can be found -> [here](insomnia-datasources.json)

By default, the project contains needed database with two schemas:

* administrative - used to manage company and company's advertisement campaigns
* two schemas of advertisement campaigns named c_1_db_1 amd c_1_db_2 that belongs to company from administrative schema

## Purpose of this demo

Usage of AbstractRoutingDataSource as datasource of Spring's application allows using multiple datasources with application. Things that are needed for this implementation to work are:

* thread-context that keeps routing key, which will be used to identify the datasource to use
* set of datasources identified by routing key, created at application startup or created in application runtime

Project uses Aspect Oriented Programming to set thread-context routing key on execution of controller's methods that are annotated with @RoutedDataSource. After executing annotated methods it clears the routing key.

If no @RoutedDataSource is used on controller method then default fallback DataSource connected to administrative schema is used.