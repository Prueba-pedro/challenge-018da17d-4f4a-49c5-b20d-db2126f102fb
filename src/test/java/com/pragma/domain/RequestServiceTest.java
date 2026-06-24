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