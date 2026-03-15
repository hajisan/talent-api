# Hello Tech Chapter

REST API bygget som praktikansøgning til Tech Chapter 2026.

Serverer talentprofiler og dokumenter over et JSON API med Swagger UI og en statisk frontend der henter dynamisk fra API'et.

Bygget med **Java 21 + Spring Boot 3**, dokumenteret med **Swagger UI** og deployeret via **GitHub Actions → GHCR**.

## Stack

- Java 21 + Spring Boot 3.5
- springdoc-openapi (Swagger UI)
- Docker (multi-stage build, non-root user, HEALTHCHECK, multi-platform)
- GitHub Actions CI/CD → GHCR

## Kør med Docker

```bash
docker pull ghcr.io/hajisan/hello-tech-chapter:latest
docker run -p 8080:8080 ghcr.io/hajisan/hello-tech-chapter:latest
```

| URL | Beskrivelse |
|-----|-------------|
| `http://localhost:8080` | Frontend |
| `http://localhost:8080/swagger-ui.html` | Swagger UI |
| `http://localhost:8080/api-docs` | OpenAPI JSON |

## Endpoints

| Method | Path | Beskrivelse |
|--------|------|-------------|
| GET | `/talent` | Liste over alle talents |
| GET | `/talent/{id}` | Specifik talent |
| GET | `/talent/{id}/documents` | Dokumenter for en talent |
| GET | `/talent/{id}/documents/{documentId}` | Specifikt dokument |

## Byg lokalt

```bash
mvn package -DskipTests
docker build -t hello-tech-chapter .
docker run -p 8080:8080 hello-tech-chapter
```
