# API Gestión de Clientes - Prueba Técnica

API REST desarrollada con Spring Boot para el manejo completo de clientes (CRUD), aplicando buenas prácticas de programación y Clean Code.

Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA + Hibernate
- MySQL
- Lombok
- Jakarta Bean Validation
- SpringDoc OpenAPI (Swagger)
- JUnit 5 + Mockito

Requisitos

- Java 17 o superior
- Maven 3.8+
- MySQL 8.0+

Configuración

1. Base de Datos

Crea la base de datos en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS gestor_usuarios 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_spanish_ci;
```
Configura application.properties

# ==================== Configuración General ====================
spring.application.name=prueba-tecnica-generation

# ==================== Base de Datos MySQL ====================
spring.datasource.url=jdbc:mysql://localhost:3306/gestor_usuarios?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=

# Driver (opcional, Spring Boot lo detecta automáticamente)
 spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ==================== JPA / Hibernate ====================
spring.jpa.hibernate.ddl-auto=update     
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ==================== Swagger / OpenAPI ====================
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true

# Mensaje para saber dónde ver la documentación
springdoc.info.title=API Gestión de Clientes - Prueba Técnica

Endpoints Disponibles


Estructura del proyecto

src/main/java/com/manejo_clientes/prueba_tecnica_generation/
├── exception/
├── model/
├── repository/
├── service/
├── controller/

src/main/resources/
├── application.properties
├── data.sql


Luis Villada 
alonsovillada63@gmail.com

