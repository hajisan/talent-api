# Talent API – Tech Chapter Praktikant-udfordring

Implementering af Talent API specifikationen fra Tech Chapter.

Bygget med **Java 21 + Spring Boot 3** og dokumenteret med **springdoc-openapi (Swagger UI)**.

## Endpoints

| Method | Path | Beskrivelse |
|--------|------|-------------|
| GET | `/talent` | Liste over alle talents |
| GET | `/talent/{id}` | Specifik talent |
| GET | `/talent/{id}/documents` | Dokumenter for en talent |
| GET | `/talent/{id}/documents/{documentId}` | Specifikt dokument |

## Kør lokalt

```bash
docker pull ghcr.io/hajisan/talent-api:latest
docker run -p 8080:8080 ghcr.io/hajisan/talent-api:latest
```

Swagger UI: http://localhost:8080/swagger-ui.html  
API docs (JSON): http://localhost:8080/api-docs

## Byg selv

```bash
mvn package -DskipTests
docker build -t talent-api .
docker run -p 8080:8080 talent-api
```

## Talent ID

Nima Salami: `a1b2c3d4-e5f6-7890-abcd-ef1234567890`

```bash
# Hent talent
curl http://localhost:8080/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890

# Hent dokumenter
curl http://localhost:8080/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890/documents
```
