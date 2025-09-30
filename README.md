
# SpringBoot Gemini API Consumer – Documentation

## Purpose and Scope

This document provides an overview of the **springboot-gemini-api-consumer** repository, an application developed in **Spring Boot** that serves as an integration layer between client applications and the **Google Gemini AI** API.

The system exposes a **REST interface** that accepts prompts, transforms them into the format required by Gemini, manages authentication through API Keys, and returns the generated response.

* **Main Goal**: act as a **secure and simple proxy** to consume Gemini from frontend applications.
* **Audience**: frontend developers, backend developers, and architects who want to integrate Gemini into their systems.

For interactive testing, access the Swagger documentation:
[Swagger UI](https://springboot-gemini-api-consumer.onrender.com/swagger-ui/index.html#)

---

## System Description

The **springboot-gemini-api-consumer** is a **Java microservice** that:

* Receives prompt requests in JSON format.
* Builds the required hierarchy (`Prompt → Contents → Parts`).
* Sends the request to the Gemini endpoint (`generativelanguage.googleapis.com`).
* Returns the response to the client in JSON format.

The system is configured with **CORS** to accept requests from `http://localhost:3000`, enabling seamless integration with frontend applications.

---

## High-Level System Architecture

### Component Overview

* **Client (Frontend / Console / Postman)** → sends a JSON `{ "text": "..." }`.
* **Web Layer (ApiController)** → receives the request and builds the `Prompt` DTO.
* **Service Layer (AppService, GeminiService)** → coordinates logic and makes the HTTP call to the Gemini service.
* **Gemini API** → processes the prompt and returns the response.
* **ApiController** → sends the response back to the client.

---

## Technology Stack

| Component      | Technology               | Version  | Purpose                      |
| -------------- | ------------------------ | -------- | ---------------------------- |
| Runtime        | Java                     | 21       | Execution environment        |
| Framework      | Spring Boot              | 3.4.5    | Framework and DI             |
| AI Integration | Spring AI BOM            | 1.0.0-M7 | Abstractions for AI          |
| Web Layer      | Spring Boot Starter Web  | 3.4.5    | REST API                     |
| Build Tool     | Maven                    | 3.x      | Dependency management        |
| Code Reduction | Lombok                   | Latest   | Boilerplate reduction        |
| Testing        | Spring Boot Starter Test | 3.4.5    | Unit and integration testing |

---

## Core Components and Code Mapping

### Application Layer

* **`SpringbootGeminiApiConsumerApplication`**

  * Entry point.
  * Exposes a `RestTemplate` bean for HTTP communication.

### Web Layer

* **`ApiController`**

  * Exposes REST endpoints.
  * Main endpoint:

    ```
    POST /api/get-result-BreinLogic
    ```
  * CORS configured for `http://localhost:3000`.
  * Builds the `Prompt → Contents → Parts` DTO hierarchy.

### Service Layer

* **`AppService`**

  * Coordinates business logic.
  * Receives `Prompt`, sends it to `GeminiService`, and returns the response.

* **`GeminiService`**

  * Calls Gemini endpoint directly.
  * Injects Gemini URL from `application.properties`.
  * Uses `RestTemplate` for POST requests.

---

## Request Processing Flow

1. The client sends a JSON request:

   ```json
   { "text": "Explain the Singleton pattern" }
   ```

2. **ApiController** receives the `Parts`, wraps it into a `Contents`, and then into a `Prompt`.

3. **AppService** coordinates the call and forwards it to **GeminiService**.

4. **GeminiService** makes a POST request to the Gemini endpoint.

5. The generated response is received and returned to the client.

---

## Key Features

* **API Proxy Pattern**: acts as an intermediary between frontend and Gemini.
* **Cross-Origin Support**: CORS setup to allow requests from `localhost:3000`.
* **API Key Management**: Gemini key externalized via environment variables.
* **Hierarchical DTO**: `Prompt → Contents → Parts` model for complex prompts.
* **Spring AI Integration**: abstractions for AI integration.
* **RESTful Interface**: clean and simple API for integration.

---

## Example Request & Response

### Request

```bash
curl -X POST https://springboot-gemini-api-consumer.onrender.com/api/get-result-BreinLogic \
  -H "Content-Type: application/json" \
  -d '{"text": "Hi, what is your name?"}'
```

### Response

```json
{
  "response": "My name is BreinLogic. I am a friendly assistant and can answer questions about programming or general topics."
}
```

---

## Future Improvements

* Dynamic personality configuration (e.g., BreinLogic as a friendly, general-purpose assistant).
* Multi-model support (Gemini 1.5 Flash, Gemini 1.5 Pro, etc.).
* Conversational context management (message history).
* Database integration for prompt logging and analysis.


