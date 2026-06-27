# BFHL Java API

This is my Spring Boot solution for the Java API round.

## API

POST `/bfhl`

Sample request:

```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

## Change my details

Before submitting, update this file:

`src/main/resources/application.properties`

```properties
bfhl.full-name=raina_arora
bfhl.birth-date=DDMMYYYY
bfhl.email=your_email@example.com
bfhl.roll-number=YOUR_ROLL_NUMBER
```

## Run

```bash
mvn spring-boot:run
```

## Test API locally

```bash
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d '{"data":["a","1","334","4","R","$"]}'
```

## Run test cases

```bash
mvn test
```

## Deploy on Render

Build command:

```bash
mvn clean package -DskipTests
```

Start command:

```bash
java -jar target/bfhl-java-api-0.0.1-SNAPSHOT.jar
```

Submit the hosted URL with `/bfhl` at the end.
