# Bank Microservices Learning App

A personal sandbox project for exploring and experimenting with microservices patterns and technologies. Built around a simple banking domain (accounts, loans, cards) purely as a vehicle for learning — not a production system.

## What is this?

This project is a hands-on playground for trying out various technologies commonly used in microservices architectures. The domain is a fictitious bank with three core services. The goal is to incrementally add new concerns (observability, security, event-driven messaging, orchestration, etc.) and learn how they fit together.

## Architecture

Three core business microservices, each with its own MySQL database, wired together via a service mesh of Spring Cloud infrastructure components.

```
Client
  └── Gateway Server (Spring Cloud Gateway + Redis)
        ├── Accounts Service  ──> accountsdb (MySQL)
        ├── Loans Service     ──> loansdb    (MySQL)
        └── Cards Service     ──> cardsdb    (MySQL)

Infrastructure
  ├── Config Server    (Spring Cloud Config)
  └── Eureka Server    (Spring Cloud Netflix)
```

## Services

| Service | Port | Description |
|---|---|---|
| `accounts` | 8080 | Account management and customer details |
| `loans` | 8090 | Loan management |
| `cards` | 9000 | Card management |
| `config-server` | 8071 | Centralised configuration (Spring Cloud Config) |
| `eureka-server` | 8070 | Service discovery (Spring Cloud Netflix Eureka) |
| `gateway-server` | 8072 | API gateway with routing, rate limiting, circuit breaking |

## Technologies

### Core Stack

| Technology | Purpose |
|---|---|
| Java 21 | Language |
| Spring Boot 3.4 / 4.0 | Application framework |
| Spring Cloud 2024/2025 | Microservices infrastructure |
| Maven | Build tool |
| Docker & Docker Compose | Containerisation and local orchestration |

### Spring Cloud Components

| Component | Purpose |
|---|---|
| Spring Cloud Config | Centralised, git-backed configuration |
| Spring Cloud Netflix Eureka | Service discovery and registration |
| Spring Cloud Gateway (WebFlux) | API gateway with reactive routing |
| Spring Cloud OpenFeign | Declarative HTTP client for inter-service calls |
| Resilience4j | Circuit breaker, retry, rate limiter, HTTP timeout |

### Data

| Technology | Purpose |
|---|---|
| MySQL | Per-service relational database (database-per-service pattern) |
| Spring Data JPA | ORM and repository layer |
| Redis | Distributed rate limiting in the API gateway |

### API & Developer Experience

| Technology | Purpose |
|---|---|
| Springdoc / OpenAPI 3 | Auto-generated Swagger UI per service |
| Spring Boot Actuator | Health checks, readiness/liveness probes, metrics endpoint |
| Lombok | Boilerplate reduction |

### Observability

| Technology | Purpose |
|---|---|
| Grafana Loki | Log aggregation (read/write/backend topology) |
| Grafana Alloy | Log collection agent |
| MinIO | S3-compatible object storage backend for Loki |
| Nginx | Loki read/write gateway and load balancer |
| Grafana | Dashboards and log exploration |
| Micrometer + Prometheus | Metrics instrumentation |

## Planned / Coming Up

The following topics are on the roadmap to be explored and integrated:

### Observability & Monitoring
- **Prometheus** — metrics scraping from all services via the `/actuator/prometheus` endpoint
- **OpenTelemetry** — distributed tracing across service boundaries
- **Grafana dashboards** — unified metrics, logs, and traces in Grafana (the "three pillars")

### Security
- **Keycloak** — AuthN & AuthZ with OAuth2 / OpenID Connect
- Token propagation across services via the API gateway

### Event-Driven Architecture
- **RabbitMQ / Kafka** — async messaging between services
- **Spring Cloud Function** — function-as-a-service style business logic
- **Spring Cloud Stream** — binder abstraction over messaging brokers

### Container Orchestration
- **Kubernetes** — deploying and managing the services in a cluster
- **Helm** — packaging and templating Kubernetes manifests

## Running Locally

Docker Compose profiles are organised by concern:

```
docker-compose/
  default/        # All services + databases (no observability stack)
  prod/           # Services + Loki stack (observability enabled)
  observability/  # Loki and Alloy configuration files
```

Start the default stack:

```bash
cd docker-compose/default
docker compose up -d
```

Start with observability:

```bash
cd docker-compose/prod
docker compose up -d
```

### Ports at a Glance

| Service | URL |
|---|---|
| Accounts API + Swagger | http://localhost:8080/swagger-ui.html |
| Loans API + Swagger | http://localhost:8090/swagger-ui.html |
| Cards API + Swagger | http://localhost:9000/swagger-ui.html |
| API Gateway | http://localhost:8072 |
| Eureka Dashboard | http://localhost:8070 |
| Config Server | http://localhost:8071 |
| Grafana | http://localhost:3000 |
| Loki Gateway | http://localhost:3100 |
| Grafana Alloy UI | http://localhost:12345 |

## Building Docker Images

A build script is provided to build and push all service images:

```bash
./scripts/docker-build-all.sh
```
