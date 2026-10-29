# Music catalog · Java/Spring Boot

Aplicación de catálogo de artistas y canciones, con API REST y una interfaz web incluida.

## Demo local

Requiere Java 17 (JDK y JAVA_HOME para compilar). Desde la raíz:

```powershell
.\mvnw.cmd clean verify
java -jar target/myapp-0.0.1-SNAPSHOT.jar --spring.profiles.active=demo
```

En macOS/Linux utiliza bash ./mvnw clean verify. Abre http://localhost:8082.
La demo contiene un artista y una canción ficticios en H2 en memoria. Los datos se reinician al parar.
API: GET/POST /artists y /songs; GET/PUT/DELETE /artists/{id} y /songs/{id}.
Solo escucha en la máquina local; esta aplicación no incluye autenticación.

## MySQL externo

Sin perfil demo, configura DB_URL, DB_USERNAME y DB_PASSWORD y provisiona el esquema antes del arranque.
No reutilices credenciales históricas. La demo H2 no acredita compatibilidad MySQL.

## Autor

Carlos Zabala. Se conserva el historial del proyecto original.

Prueba HTTP reproducible después del arranque: `node scripts/smoke.cjs` (Node22/24). Utiliza y elimina únicamente registros ficticios de prueba.
