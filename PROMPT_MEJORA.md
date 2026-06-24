# Prompt para Mejorar el Codigo Base

Copia y pega el siguiente contenido completo en un asistente de IA (Claude, ChatGPT, etc.)
para obtener un ZIP con el proyecto corregido y listo para compilar.

---

```
Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación o descripciones sin código, genera los archivos
correspondientes sin aplicar análisis de compilación
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:
// === ARCHIVO: src/main/java/com/pragma/api/WebClientConfig.java ===
package com.pragma.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

// === ARCHIVO: src/main/java/com/pragma/api/RequestController.java ===
package com.pragma.api;

import com.pragma.domain.RequestService;
import com.pragma.infrastructure.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/requests")
    public Mono<ResponseEntity<String>> handleRequest(@RequestBody RequestDto requestDto) {
        return requestService.processRequest(requestDto)
           .map(response -> ResponseEntity.ok().body("Request processed successfully"))
           .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Error processing request: " + e.getMessage())));
    }
}

// === ARCHIVO: src/main/java/com/pragma/domain/RequestService.java ===
package com.pragma.domain;

import com.pragma.infrastructure.RequestDto;
import reactor.core.publisher.Mono;

public interface RequestService {
    Mono<String> processRequest(RequestDto requestDto);
}

// === ARCHIVO: src/main/java/com/pragma/domain/RequestServiceImpl.java ===
package com.pragma.domain;

import com.pragma.infrastructure.RequestDto;
import com.pragma.infrastructure.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Mono<String> processRequest(RequestDto requestDto) {
        // Validación básica de los datos recibidos
        if (requestDto.getName() == null || requestDto.getName().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Client name cannot be empty"));
        }
        if (requestDto.getAmount() <= 0) {
            return Mono.error(new IllegalArgumentException("Amount must be positive"));
        }

        // Persistencia de la solicitud
        return requestRepository.save(requestDto)
           .map(savedDto -> "Request saved successfully");
    }
}

// === ARCHIVO: src/main/java/com/pragma/infrastructure/RequestRepository.java ===
package com.pragma.infrastructure;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RequestRepository extends R2dbcRepository<RequestDto, Long> {
}

// === ARCHIVO: src/main/resources/config/application.yml ===
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/reactivedb
    username: user
    password: password

// === ARCHIVO: src/test/java/com/pragma/api/RequestControllerTest.java ===
package com.pragma.api;

import com.pragma.domain.RequestService;
import com.pragma.infrastructure.RequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RequestService requestService;

    @Test
    public void testHandleRequest() {
        RequestDto requestDto = new RequestDto("John Doe", 100.0);
        Mockito.when(requestService.processRequest(requestDto))
           .thenReturn(Mono.just("Request saved successfully"));

        webTestClient.post()
           .uri("/api/requests")
           .bodyValue(requestDto)
           .exchange()
           .expectStatus().isOk()
           .expectBody(String.class).isEqualTo("Request processed successfully");
    }
}

// === ARCHIVO: src/test/java/com/pragma/domain/RequestServiceTest.java ===
package com.pragma.domain;

import com.pragma.infrastructure.RequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

@SpringBootTest
public class RequestServiceTest {

    @MockBean
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @Test
    public void testProcessRequest() {
        RequestDto requestDto = new RequestDto("John Doe", 100.0);
        Mockito.when(requestRepository.save(requestDto))
           .thenReturn(Mono.just(requestDto));

        requestService.processRequest(requestDto)
           .as(StepVerifier::create)
           .expectNext("Request saved successfully")
           .verifyComplete();
    }
}

// === ARCHIVO: src/test/java/com/pragma/infrastructure/RequestRepositoryTest.java ===
package com.pragma.infrastructure;

import com.pragma.infrastructure.RequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@SpringBootTest
public class RequestRepositoryTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void testSaveRequest() {
        RequestDto requestDto = new RequestDto("John Doe", 100.0);

        databaseClient.execute("INSERT INTO requests (name, amount) VALUES (:name, :amount)")
           .bind("name", requestDto.getName())
           .bind("amount", requestDto.getAmount())
           .fetch()
           .rowsUpdated()
           .as(StepVerifier::create)
           .expectNext(1L)
           .verifyComplete();
    }
}

```
