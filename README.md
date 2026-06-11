# Laboratorio Ruleta - Iteración 09

## Rama de trabajo

La versión correspondiente a la Ruleta 09 se trabaja en la rama `lab09`.

La rama `master` debe mantenerse libre hasta la entrega final, según la indicación del curso.

## Descripción

Esta iteración incorpora pruebas unitarias con Maven y JUnit 5 para verificar la lógica central del sistema de ruleta antes de probar capas como interfaz gráfica o experiencia de usuario.

## Casos de prueba implementados

Se agregaron pruebas para los casos prioritarios solicitados:

1. Constructor de `Ruleta` rechaza saldo inicial negativo.
2. Depósito válido incrementa el saldo.
3. Apuesta nula es rechazada.
4. Apuesta con monto mayor al saldo es rechazada.
5. `Estadisticas` calcula total de jugadas, victorias, porcentaje de victorias, racha máxima y tipo de apuesta más jugado.
6. Inicio de sesión con usuario no registrado es rechazado.
7. Inicio de sesión con username nulo es rechazado.

## Estructura de pruebas

Las pruebas se encuentran en:

```text
src/test/java/RuletaTest.java
src/test/java/EstadisticasTest.java
src/test/java/SessionControllerTest.java
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

## Cambios relevantes

Además de los tests, se reforzaron validaciones en el modelo:

- `Ruleta` valida saldo inicial negativo.
- `Ruleta` rechaza apuestas nulas.
- `Ruleta` rechaza apuestas cuyo monto supera el saldo disponible.
- `Usuario` valida datos requeridos.
- `Usuario.validarCredenciales` rechaza datos nulos.
- `Estadisticas.calcularTipoMasJugado` ignora apuestas con tipo nulo o vacío.
