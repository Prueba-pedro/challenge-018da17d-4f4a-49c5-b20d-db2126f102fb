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