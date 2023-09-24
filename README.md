<div align="center">
<h1 align="center">Clients (Simple REST API)</h1>
</div>

## About The Project

The project consists of a RestApi service and a PostgreSQL database, packaged in containers. Swagger is connected to send and receive requests. The Controller and Service layers are covered with Unit tests JUnit + Mockito.

Technologies used:
* [Java 17](https://www.java.com/en/)
* [Spring 3.0.6](https://spring.io/)
* [REST](https://ru.wikipedia.org/wiki/REST)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/), [CI/CD](https://ru.wikipedia.org/wiki/CI/CD)
* [Swagger](https://swagger.io/)
* [JUnit](https://junit.org/junit5/), [Mockito](https://site.mockito.org)

## Dependencies are required to build the project:
* [Java SE Development Kit 17+](https://www.oracle.com/java/technologies/downloads/archive/)
* [Docker](https://www.docker.com/)
* [Make](https://en.wikipedia.org/wiki/Make_(software))
* [Gradle](https://gradle.org)

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these example steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Polliver-hub/clients.git
   ```
2. Make sure [docker](https://www.docker.com/) and [Gradle](https://gradle.org) is installed
3. In the project root, through the terminal, use the command
   ```sh
   make (or "make all")
   ```
4. To send requests to localhost you can use Swagger to the address in the browser:
   ```sh
   http://localhost:8080/swagger-ui/index.html
   ```
5. To stop the service:
   ```sh
   make stop
   ```
6. To remove containers:
   ```sh
   make clean
   ```
7. To recreate containers:
   ```sh
   make rebuild
   ```
8. To remove images from docker:
   ```sh
   docker rmi "IMAGE ID your containers"
   ```

## Contact
Ivan Kudryashov[@telegram](https://t.me/IvanKudryashov) - i.kudryashov@inbox.ru
