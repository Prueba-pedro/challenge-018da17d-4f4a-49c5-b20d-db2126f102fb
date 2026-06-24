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