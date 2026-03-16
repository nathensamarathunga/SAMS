# SAMS — README

## Project
Simple CLI attendance management system (Java / Maven). Source: `src/main/java/com/sams`. Database schema: `src/main/java/com/sams/db/schema.sql`.

## Prerequisites (macOS)
- Java 21 installed (`/Library/Java/JavaVirtualMachines/jdk-21.jdk`)
- Maven 3.x
- MySQL server (local Homebrew install or system MySQL)

## Default database credentials (for assignment / local testing)
- Database name: `sams`
- Root user: `root` (use the root password you set)
- Example non-root user used in this README:
  - Username: `sams_user`
  - Password: `sams_pass`
- Example root password referenced in docs: `rootpass`
- JDBC URL (default used by the app):  
  `jdbc:mysql://localhost:3306/sams?useSSL=false&serverTimezone=UTC`

> If you change credentials, update `src/main/java/com/sams/db/Database.java` accordingly.
