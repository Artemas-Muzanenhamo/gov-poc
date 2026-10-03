# Gov-POC

[![Java CI with Gradle](https://github.com/Artemas-Muzanenhamo/gov-poc/actions/workflows/gradle.yml/badge.svg)](https://github.com/Artemas-Muzanenhamo/gov-poc/actions/workflows/gradle.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Artemas-Muzanenhamo_gov-poc&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Artemas-Muzanenhamo_gov-poc)

A Spring Boot and Spring Cloud microservices proof of concept for government-style citizen services, with centralized configuration, service discovery, gateway routing, MongoDB-backed domain services, and contract testing.

---

## Table of Contents

- [About](#about)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Services](#services)
- [Getting Started](#getting-started)
- [Running with Docker](#running-with-docker)
- [Running Locally](#running-locally)
- [API Reference](#api-reference)
- [Configuration](#configuration)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Roadmap Ideas](#roadmap-ideas)
- [Contributing](#contributing)
- [License](#license)

---

## About

`gov-poc` is a multi-module microservices demo that models a small government services platform.

The current domain is centered around three service areas:

- **Identity Service** — manages citizen identity records
- **License Service** — manages license records and validates identity ownership
- **Health Service** — manages patient records and also depends on valid identity data

The platform also includes the core infrastructure needed in a distributed system:

- **Configuration Server** for centralized configuration
- **Discovery Server** for service registration and lookup
- **Gateway** as the main entry point to backend services
- **MongoDB** as the persistence layer for domain services

This repository is intended as a practical microservices POC rather than a production-ready platform, but it already demonstrates service boundaries, inter-service communication, externalized configuration, and consumer-driven contract testing.

---

## Architecture

### High-Level Flow

1. Services load configuration from the **Configuration Server**
2. Services register themselves with **Eureka** in the **Discovery Server**
3. Clients access the platform through the **Gateway**
4. Domain services communicate with each other where needed
5. MongoDB stores service-specific data

### Service Relationships

- `license-service` depends on `identity-service` to validate identity references
- `health-service` depends on `identity-service` to validate identity references
- `configuration-server` provides centralized properties for:
  - `gateway`
  - `identity-service`
  - `license-service`
  - `health-service`
- `discovery-server` acts as the registry for all discoverable services

### Architecture Diagram

<p>
  <img src="https://user-images.githubusercontent.com/29547780/61170379-379d2600-a560-11e9-8e7e-e48a55221488.jpg" alt="Gov POC architecture">
</p>

> Note: the current codebase uses **Spring Cloud Gateway** in the `gateway` module.

---

## Tech Stack

- **Java 17**
- **Gradle**
- **Spring Boot 3.2.4**
- **Spring Cloud 2023.0.1**
- **Spring Cloud Config**
- **Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Data MongoDB**
- **Spring Web / WebFlux**
- **OpenFeign**
- **MongoDB**
- **Docker / Docker Compose**
- **JUnit 5**
- **JaCoCo**
- **Pact**

---

## Services

### Infrastructure Services

| Service | Port | Purpose |
|---|---:|---|
| `configuration-server` | `8888` | Centralized configuration for services |
| `discovery-server` | `8761` | Eureka service registry |
| `gateway` | `9999` | Main API entry point / routing layer |
| Host MongoDB | `27017` | Existing local MongoDB instance used by domain services when running locally or in Docker |

### Domain Services

| Service | Port | Purpose | Data Store |
|---|---:|---|---|
| `identity-service` | `8080` | Manages citizen identity records | MongoDB |
| `license-service` | `8081` | Manages licenses and validates identity ownership | MongoDB |
| `health-service` | `8082` | Manages patient records and validates identity ownership | MongoDB |

### Service Notes

#### `identity-service`
- Exposes identity CRUD/search-style endpoints under `/identities`
- Used by other services as the source of identity truth
- Includes provider-side Pact testing dependencies

#### `license-service`
- Exposes license endpoints under `/licenses`
- Uses OpenFeign to call `identity-service`
- Prevents operations that require a valid identity when one does not exist

#### `health-service`
- Exposes patient routes under `/patients`
- Uses Spring WebFlux functional routing
- Uses OpenFeign to call `identity-service`

---

## Getting Started

### Prerequisites

Make sure the following are installed:

- **Java 17**
- **Gradle** or use the included Gradle wrapper
- **Docker**
- **Docker Compose**
- **Git**

### Build the Project

To build all modules:

```bash
./gradlew build
```

If you want to skip tests during packaging:

```bash
./gradlew -x test build
```

---

## Running with Docker

This is the quickest way to start the full platform.

> Before starting the containers, make sure MongoDB is already running on your machine at `localhost:27017`.
> The current `docker-compose.yml` does **not** start a MongoDB container. Instead, the app containers connect to your host MongoDB using `host.docker.internal`.

### Build the project artifacts

```bash
./gradlew -x test build
```

### Start all services

```bash
docker compose -f ./docker-compose.yml up -d
```

### Check running containers

```bash
docker ps
```

### Stop all services

```bash
docker compose -f ./docker-compose.yml down
```

### Rebuild and restart containers

```bash
docker compose -f ./docker-compose.yml up -d --build
```

---

## Running Locally

If you want to run services individually during development, start them in roughly this order.

### 1) Start MongoDB

Make sure MongoDB is already running locally on your machine at `localhost:27017`.

If you are running the Spring Boot services directly with `bootRun`, the `dev` profile uses:

```text
host=localhost
port=27017
```

If you are running the services in Docker, the containers use `host.docker.internal` to reach that same host MongoDB instance.

Use your preferred local MongoDB setup, for example a native installation, Docker Desktop app container started separately, or another already-running MongoDB service on your Mac.

### 2) Start the Configuration Server

The configuration server includes a native profile that reads from bundled shared config.

```bash
./gradlew :configuration-server:bootRun --args='--spring.profiles.active=native'
```

### 3) Start the Discovery Server

```bash
./gradlew :discovery-server:bootRun
```

### 4) Start the Gateway

```bash
./gradlew :gateway:bootRun --args='--spring.profiles.active=dev'
```

### 5) Start the Domain Services

```bash
./gradlew :identity-service:bootRun --args='--spring.profiles.active=dev'
./gradlew :license-service:bootRun --args='--spring.profiles.active=dev'
./gradlew :health-service:bootRun --args='--spring.profiles.active=dev'
```

### Local Development Notes

- `identity-service`, `license-service`, and `gateway` are configured to use the config server in `dev`
- Shared config for local development points services to:
  - `localhost:8888` for config
  - `localhost:8761` for Eureka
  - `localhost:27017` for MongoDB
- The Compose-based container setup expects MongoDB to be running on the host machine and reaches it from containers via `host.docker.internal:27017`
- `health-service` is part of the same environment, but its bootstrap configuration differs slightly from the other services and may be a good candidate for future consistency cleanup

---

## API Reference

### Infrastructure Endpoints

| Component | URL |
|---|---|
| Discovery Server (Eureka dashboard) | `http://localhost:8761/` |
| Configuration Server | `http://localhost:8888/` |
| Gateway | `http://localhost:9999/` |
| Gateway service instance lookup | `http://localhost:9999/service-instances/{applicationName}` |

### Domain Service Endpoints

| Service | Base URL | Example Endpoints |
|---|---|---|
| `identity-service` | `http://localhost:8080` | `/identities`, `/identities/name`, `/identities/reference` |
| `license-service` | `http://localhost:8081` | `/licenses`, `/licenses/ref` |
| `health-service` | `http://localhost:8082` | `/patients`, `/patients/{id}` |

### Example Requests

#### Get all identities

```bash
curl http://localhost:8080/identities
```

#### Get all licenses

```bash
curl http://localhost:8081/licenses
```

#### Get all patients

```bash
curl http://localhost:8082/patients
```

### API Documentation

Per the current module setup:

- `identity-service` includes Swagger/Springfox dependencies
- `license-service` includes Swagger/Springfox dependencies

If enabled successfully at runtime, the UI is typically available at:

- `http://localhost:8080/swagger-ui/index.html`
- `http://localhost:8081/swagger-ui/index.html`

---

## Configuration

Centralized configuration lives in:

- `configuration-server/src/main/resources/shared/gateway.yml`
- `configuration-server/src/main/resources/shared/identity-service.yml`
- `configuration-server/src/main/resources/shared/license-service.yml`
- `configuration-server/src/main/resources/shared/health-service.yml`

### Current Ports

| Service | Port |
|---|---:|
| `configuration-server` | `8888` |
| `discovery-server` | `8761` |
| `gateway` | `9999` |
| `identity-service` | `8080` |
| `license-service` | `8081` |
| `health-service` | `8082` |
| Host MongoDB | `27017` |

### Profiles

#### `configuration-server`
- `native` — reads config from bundled resources
- `remote` — points to an external Git-backed config repository

#### Other services
- `docker` — used when running inside Docker
- `dev` — used for local development

---

## Testing

This repository includes both unit/integration-style tests and consumer-driven contract testing.

### Run all tests

```bash
./gradlew test
```

### Run tests for a single module

```bash
./gradlew :identity-service:test
./gradlew :license-service:test
./gradlew :health-service:test
```

### Generate JaCoCo reports

```bash
./gradlew jacocoTestReport
```

### Contract Testing

The repository includes Pact contract files in the `pacts/` directory, including:

- `pacts/health-service-identity-service.json`
- `pacts/license-service-identity-service.json`

Current contract-testing relationships include:

- `license-service` as a consumer of `identity-service`
- `health-service` as a consumer of `identity-service`
- `identity-service` with Pact provider-side test support

---

## Project Structure

```text
gov-poc/
├── build.gradle
├── docker-compose.yml
├── settings.gradle
├── configuration-server/
│   ├── src/main/resources/shared/
│   └── README.md
├── discovery-server/
│   └── README.md
├── gateway/
│   └── README.md
├── health-service/
│   └── README.md
├── identity-service/
│   └── README.md
├── license-service/
│   └── README.md
├── mongodb/
└── pacts/
```

### Module Overview

- `configuration-server/` — Spring Cloud Config Server and shared service config
- `discovery-server/` — Eureka server
- `gateway/` — API gateway and service instance lookup endpoint
- `identity-service/` — citizen identity domain service
- `license-service/` — license domain service
- `health-service/` — patient domain service
- `mongodb/` — supporting Docker assets for MongoDB
- `pacts/` — generated Pact contract files

---

## Roadmap Ideas

Potential next improvements for the platform:

- Add authentication and authorization
- Standardize API documentation across all services
- Add distributed tracing and structured logging
- Add resilience patterns such as retries and circuit breakers
- Add database migrations or seed/versioning support
- Standardize configuration loading across all services
- Add Kubernetes deployment manifests
- Add end-to-end test coverage through the gateway

---

## Contributing

Contributions are welcome.

Suggested workflow:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run the test suite
5. Open a pull request

If you contribute code, please try to keep changes:
- focused
- well-tested
- aligned with the current module structure

---

## License

This project is licensed under the terms of the repository's [LICENSE](LICENSE) file.
