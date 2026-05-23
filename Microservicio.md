

# Microservicio Librería
github: https://github.com/Luisangelriverosbautista/microservicio-libreria.git

## Descripción General

**Microservicio Librería** es una aplicación desarrollada bajo una arquitectura de microservicios, cuyo propósito principal es gestionar la información de una librería mediante servicios independientes y organizados por responsabilidades. Cada microservicio es autónomo, está desarrollado en Java con Spring Boot y se comunica mediante APIs REST.

La arquitectura de microservicios permite construir aplicaciones escalables, mantenibles y desacopladas, facilitando el crecimiento del sistema, la integración de nuevas funcionalidades y el trabajo colaborativo entre equipos.

## Objetivo General

Desarrollar un sistema de gestión de librería basado en microservicios utilizando Spring Boot y APIs REST, que permita realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre libros, usuarios y préstamos, así como facilitar la integración de nuevos módulos en el futuro.

---

## Ventajas de la Arquitectura de Microservicios

- **Escalabilidad:** Cada microservicio puede escalarse de manera independiente según la demanda.
- **Mantenibilidad:** El código está organizado por responsabilidad, facilitando la localización y corrección de errores.
- **Despliegue independiente:** Permite actualizar o desplegar un microservicio sin afectar a los demás.
- **Tecnologías heterogéneas:** Posibilidad de usar diferentes tecnologías o versiones en cada microservicio si se requiere.

---

## Estructura General del Proyecto

```
microservicio-libreria/
├── Libro/
│   ├── src/main/java/com/jessica/libro/
│   ├── src/main/resources/
│   └── pom.xml
├── Prestamo/
│   ├── src/main/java/com/jessica/prestamo/
│   ├── src/main/resources/
│   └── pom.xml
├── Usuario/
│   ├── src/main/java/com/jessica/usuario/
│   ├── src/main/resources/
│   └── pom.xml
└── microservicio-libreria.postman_collection.json
```

Cada microservicio contiene:
- **controller/**: Controladores REST, exponen los endpoints de la API.
- **services/**: Lógica de negocio y validaciones.
- **repository/**: Acceso a datos mediante JPA/Hibernate.
- **model/**: Entidades del dominio (tablas de la base de datos).
- **DTO/**: Objetos de transferencia de datos (para comunicación entre capas o servicios).

---

## Tecnologías Utilizadas

- **Java 21** (recomendado)
- **Spring Boot 4.x**
- **Spring Data JPA**
- **Spring Cloud OpenFeign** (para comunicación entre servicios)
- **Maven**
- **Lombok** (para reducir boilerplate)
- **Jakarta Validation** (validaciones de datos)
- **H2/MySQL** (base de datos, configurable)
- **Postman** (colección de pruebas incluida)

---

## Descripción de Microservicios y Entidades

### 1. Microservicio **Libro**
Gestiona la información de los libros disponibles en la librería.

**Entidades principales:**
- **Libro**: idLibro, titulo, editorial, añoPublicacion, stockTotal, stockDisponible. Relaciones:
  - Muchos a muchos con **Autor** (un libro puede tener varios autores y viceversa).
  - Muchos a uno con **Categoria** (un libro pertenece a una categoría).
- **Autor**: idAutor, nombre. Relación muchos a muchos con Libro.
- **Categoria**: idCategoria, nombre. Relación uno a muchos con Libro.

**Ejemplo de entidad Libro:**
```java
@Entity
public class Libro {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idLibro;
   private String titulo;
   @ManyToMany(fetch = FetchType.EAGER)
   private List<Autor> autores;
   private String editorial;
   private Integer añoPublicacion;
   @ManyToOne(fetch = FetchType.EAGER)
   private Categoria categoria;
   private Integer stockTotal;
   private Integer stockDisponible;
}
```

**Otros aspectos relevantes:**
- Permite registrar, actualizar, eliminar y consultar libros.
- Control de stock disponible y total.
- Gestión de autores y categorías asociadas.

### 2. Microservicio **Prestamo**
Administra los préstamos de libros a los usuarios.

**Entidades principales:**
- **Prestamo**: idPrestamo, idUsuario, idLibro, fechaPrestamo, fechaDevolucion, estado (ACTIVO/DEVUELTO).
- **EstadoPrestamo**: Enum para el estado del préstamo.

**Ejemplo de entidad Prestamo:**
```java
@Entity
public class Prestamo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idPrestamo;
   private Long idUsuario;
   private Long idLibro;
   private LocalDate fechaPrestamo;
   private LocalDate fechaDevolucion;
   @Enumerated(EnumType.STRING)
   private EstadoPrestamo estado;
}
```

**Otros aspectos relevantes:**
- Permite registrar nuevos préstamos, consultar préstamos activos y finalizados.
- Control de fechas de préstamo y devolución.
- Relación directa con los microservicios de Libro y Usuario mediante los IDs.

### 3. Microservicio **Usuario**
Gestiona la información de los usuarios registrados.

**Entidades principales:**
- **Usuario**: idUsuario, nombre, apellidos, correo, telefono, direccion.

**Ejemplo de entidad Usuario:**
```java
@Entity
public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idUsuario;
   private String nombre;
   private String apellidos;
   private String correo;
   private String telefono;
   private String direccion;
}
```

**Otros aspectos relevantes:**
- Permite registrar, actualizar, eliminar y consultar usuarios.
- Validación de correo electrónico y teléfono.
- Relación con préstamos realizados por el usuario.

---

## Ejecución de los Microservicios

1. Clona el repositorio y navega a la carpeta de cada microservicio (`Libro`, `Prestamo`, `Usuario`).
2. Ejecuta el siguiente comando en cada carpeta para iniciar el microservicio:
   ```bash
   ./mvnw spring-boot:run
   # o en Windows
   mvnw.cmd spring-boot:run
   ```
3. Cada microservicio se ejecuta en un puerto diferente (configurable en `application.properties`).

---

## Pruebas y Colección Postman

- Se incluye una colección de Postman (`microservicio-libreria.postman_collection.json`) para probar los endpoints de los microservicios.
- Importa la colección en Postman y ejecuta las peticiones de ejemplo.

---

## Estructura de Paquetes Recomendada

Cada microservicio sigue la siguiente estructura de paquetes:

- `controller`: Controladores REST (API pública).
- `services`: Lógica de negocio y validaciones.
- `repository`: Acceso a datos (JPA/Hibernate).
- `model` y `DTO`: Entidades y objetos de transferencia de datos.

---

## Contribución

1. Realiza un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección.
3. Realiza un pull request describiendo tus cambios.

---

## Créditos

LUIS ANGEL RIVEROS BAUTISTA
MARLENE MARICELA OSORIO RAMÍREZ
IVONNE AYLIN CUEVAS LOPEZ
JESSICA REYES ROSARIO


---

Este proyecto es un ejemplo educativo de arquitectura de microservicios con Java y Spring Boot para la gestión de una librería.
