# API REST Spring Boot – Pays et villes

API REST de gestion de pays et de villes, structurée en couches distinctes.

## Architecture
- **Entités JPA** et **repositories** Spring Data
- **Services** métier
- **DTO** et **mappers MapStruct**, pour séparer le modèle exposé du modèle stocké
- **Contrôleurs REST**, documentation OpenAPI (springdoc)
- **Tests** des repositories

## Technologies
Java 17 · Spring Boot 3 · Spring Data JPA · MapStruct · Lombok · H2 / PostgreSQL · Maven

## Lancer le projet
./mvnw spring-boot:run
