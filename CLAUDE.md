# Tech Chapter – Talent API

## Projekt

Praktikant-udfordring til **Tech Chapter** i København. Opgaven er at implementere en given Swagger/OpenAPI-spec som et REST API, pakke det i et Docker container-image og publicere det.

## Stack

- **Java 21** + **Spring Boot 3.5.7**
- **springdoc-openapi 2.6.0** (Swagger UI auto-genereret fra annotations)
- **Lombok** (boilerplate-reduktion)
- **Maven** (bygværktøj)
- **Docker** (multi-stage build med `eclipse-temurin:21-jre-alpine`)

## API endpoints (fra `swagger.yaml`)

| Method | Path | Beskrivelse |
|--------|------|-------------|
| GET | `/talent` | Liste over alle talents |
| GET | `/talent/{id}` | Specifik talent via UUID |
| GET | `/talent/{id}/documents` | Dokumenter for en talent |
| GET | `/talent/{id}/documents/{documentId}` | Specifikt dokument |

## Projektstruktur

```
src/main/java/com/nima/talentapi/
├── TalentApiApplication.java       # Spring Boot entry point
├── controller/TalentController.java # REST endpoints
├── model/Talent.java               # Talent model (Lombok + Swagger annotations)
├── model/Document.java             # Document model
└── data/TalentStore.java           # In-memory data (ingen database)
```

Data er hardcodet i `TalentStore` – ingen database. Talent ID er `a1b2c3d4-e5f6-7890-abcd-ef1234567890`.

## Kør lokalt

```bash
# Byg og kør med Maven + Docker
mvn package -DskipTests
docker build -t talent-api .
docker run -p 8080:8080 talent-api

# Eller pull direkte
docker pull ghcr.io/hajisan/talent-api:latest
docker run -p 8080:8080 ghcr.io/hajisan/talent-api:latest
```

- Swagger UI: http://localhost:8080/swagger-ui.html
- API docs (JSON): http://localhost:8080/api-docs

## Container registry

Publiceret på GitHub Container Registry: `ghcr.io/hajisan/talent-api:latest`

## Konventioner

- Swagger-dokumentation via **annotations** i koden (`@Operation`, `@Parameter`, `@Schema`) – ikke via ekstern yaml-fil i runtime
- `swagger.yaml` i roden er den originale spec fra Tech Chapter (kilde til opgaven)
- Ingen database – data lever i `TalentStore` som en simpel in-memory liste/map
- Returnerer `404` hvis talent eller dokument ikke findes
