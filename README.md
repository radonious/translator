# Web-application "Translate"

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-13A13A?style=for-the-badge&logo=Swagger&logoColor=white)
![H2](https://img.shields.io/badge/H2-36F?style=for-the-badge&logo=square&logoColor=white)

## Table of Contents

1. [Specification](#game-mechanics)
2. [Requires](#requires)
3. [Before run](#before-run)
4. [How to run](#additional-features)
5. [Request example](#request-body-example)
6. [Versions](#versions)


## Specification

- Web application for translating text from one language to another.
- It is necessary to use a third-party translation service.
- Input: from language, to language, text.
- Output: translated text.
- Each word must be translated separately in several threads. The number of simultaneously working threads must not exceed 10.
- The application must save information about the request to a relational database: the user's IP address, the input string to translate, and the translation result.
- Spring/SpringBoot framework can be used.
- Use only JDBC to work with the database.
- To call an external system, use RestTemplate.

## Requires

- JDK: 17 or above
- Maven 3.9.0 or above

## Before run
```text
Edit Translator/src/main/resources/application.properties
Enter your API key into service.apiKey
```

## How to run 


```bash
cd Translator
mvn clean spring-boot:run 
open http://localhost:8080/swagger-ui/index.html
```

## Request body example
```text
{
  "sourceLanguageCode": "en",
  "target_language_code": "ru",
  "texts": "Hello world"
}
```

## Versions:

- Application version: 1.0.0
- Java: 17</br>
- H2DB: 2.3.230<br/>
- Spring 6.1.11</br>
- Spring Boot: 3.3.2</br>
- Maven: 3.8.6</br>
- Lombok: 1.18.34</br>
- Swagger: 2.6.0<br/>
- Jackson 2.17.2</br>
