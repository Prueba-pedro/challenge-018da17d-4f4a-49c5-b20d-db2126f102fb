# Implementación de una API Reactiva con Spring WebFlux

El equipo de desarrollo necesita una API que maneje solicitudes de manera reactiva utilizando Spring WebFlux. La API debe registrar solicitudes de clientes, validar los datos recibidos, persistir las solicitudes en una base de datos y responder con un estado de aceptación o rechazo. Los clientes enviarán solicitudes a través de tres canales diferentes: web, móvil y API externa. La API debe ser idempotente y capaz de manejar backpressure en caso de alta carga. Los datos recibidos incluyen nombre del cliente, monto de la solicitud y fecha de solicitud. La validación debe asegurar que el monto sea positivo y que el nombre del cliente no esté vacío. En caso de error en la persistencia, la API debe devolver un código de error adecuado y registrar el fallo en un sistema de auditoría.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Java Spring WebFlux |
| **Nivel** | junior-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: Un IDE o editor de código.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Verifica que el proyecto arranca sin errores.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno Reactivo

**Objetivo:** Establecer un entorno de desarrollo para una API reactiva utilizando Spring WebFlux.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura un proyecto Spring Boot con WebFlux.
- Define los endpoints para recibir solicitudes de los tres canales.
- Implementa la validación básica de los datos recibidos.

**Entregable:** Proyecto Spring Boot con endpoints configurados y validación básica implementada.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda que Spring WebFlux utiliza un modelo de programación no bloqueante.
- Los endpoints deben ser capaces de manejar múltiples solicitudes concurrentes.

</details>

### Fase 2: Implementación de la Persistencia Reactiva

**Objetivo:** Implementar la persistencia de las solicitudes recibidas en una base de datos utilizando R2DBC.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configura una conexión a una base de datos utilizando R2DBC.
- Implementa la persistencia de las solicitudes recibidas.
- Asegura que la persistencia sea idempotente y maneje backpressure.

**Entregable:** API con persistencia de solicitudes implementada y capaz de manejar backpressure.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza transacciones reactiva para asegurar la idempotencia.
- Implementa mecanismos para manejar backpressure en caso de alta carga.

</details>

### Fase 3: Manejo de Errores y Auditoría

**Objetivo:** Implementar el manejo de errores y la auditoría de las solicitudes procesadas.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementa el manejo de errores en la persistencia de solicitudes.
- Registra los errores en un sistema de auditoría.
- Asegura que la API devuelva un código de error adecuado en caso de fallo.

**Entregable:** API con manejo de errores y auditoría implementados.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza excepciones personalizadas para manejar diferentes tipos de errores.
- Implementa un logger para registrar los errores en el sistema de auditoría.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Spring WebFlux y por qué se utiliza en este contexto?
- **paraQueSirve**: ¿Para qué sirve la idempotencia en la persistencia de solicitudes?
- **comoSeUsa**: ¿Cómo se utiliza R2DBC para la persistencia reactiva en este escenario?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir al implementar una API reactiva con Spring WebFlux y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño de una API reactiva en términos de manejo de errores y auditoría?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo para una API reactiva con Spring WebFlux.
- Implementación de la persistencia de solicitudes utilizando R2DBC.
- Manejo adecuado de errores y registro en un sistema de auditoría.

---

*Reto generado automaticamente por Challenge Generator - Pragma*
