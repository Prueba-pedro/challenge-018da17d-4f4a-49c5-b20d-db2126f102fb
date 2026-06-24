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