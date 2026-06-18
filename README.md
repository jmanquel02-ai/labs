# Laboratorio Ruleta - Iteración 10

## Rama de trabajo

La versión correspondiente a la Ruleta 10 se trabaja en la rama `lab10`.

La rama `master` debe mantenerse libre hasta la entrega final, según la indicación del curso.

## Descripción

Esta iteración incorpora manejo de validaciones y excepciones. La idea principal es separar los errores esperables del flujo normal de los errores excepcionales del dominio o del sistema.

En esta versión, las vistas Swing validan datos ingresados por el usuario mediante `if`, mientras que los controladores y modelos conservan excepciones para proteger reglas internas importantes.

## Cambios implementados

1. `VentanaLogin` valida campos vacíos antes de iniciar sesión.
2. `VentanaRegistro` valida nombre, usuario, clave vacía y largo mínimo de clave.
3. `SessionController` ahora mantiene usuarios registrados en memoria y lanza `IllegalStateException` para credenciales incorrectas o usuario duplicado.
4. `RuletaController` valida que exista sesión activa antes de jugar o consultar historial.
5. `VentanaJuego` valida monto vacío, monto no numérico, monto menor o igual a cero y saldo insuficiente antes de crear la apuesta.
6. `VentanaMenu` y `VentanaHistorial` verifican sesión activa antes de mostrar información sensible.
7. `RepositorioArchivo` valida la ruta del historial y maneja errores de archivo de forma controlada.
8. `RepositorioArchivo` ignora líneas corruptas del CSV y continúa leyendo el resto del historial.
9. `Launcher` configura un manejador global de errores inesperados para Swing.

## Casos de prueba implementados

Se mantienen las pruebas de la iteración 9 y se agregan/actualizan pruebas para la iteración 10:

1. Inicio de sesión con usuario no registrado lanza excepción de dominio.
2. Inicio de sesión sin usuarios registrados lanza excepción de dominio.
3. Inicio de sesión con username nulo se rechaza sin excepción.
4. Registro con username nulo se rechaza.
5. Registro duplicado lanza `IllegalStateException`.
6. Acceso a usuario actual sin sesión lanza `IllegalStateException`.
7. Lectura del historial ignora líneas corruptas y continúa con el resto.

## Estructura de pruebas

Las pruebas se encuentran en:

```text
src/test/java/RuletaTest.java
src/test/java/EstadisticasTest.java
src/test/java/SessionControllerTest.java
src/test/java/RepositorioArchivoTest.java
```

## Ejecución

Desde IntelliJ IDEA:

1. Abrir el proyecto.
2. Cargar los cambios de Maven si aparece la opción `Load Maven Changes`.
3. Hacer clic derecho sobre `src/test/java`.
4. Seleccionar `Run Tests`.

Desde terminal:

```bash
mvn test
```

## Idea clave de la iteración

- Las validaciones se usan para controlar errores normales del usuario, como campos vacíos o montos inválidos.
- Las excepciones se usan para situaciones más graves, como reglas de dominio incumplidas, sesión inexistente en métodos internos o fallos de archivo.
