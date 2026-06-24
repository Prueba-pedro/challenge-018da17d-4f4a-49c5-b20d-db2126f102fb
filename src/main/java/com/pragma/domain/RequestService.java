package com.pragma.domain;

import com.pragma.infrastructure.RequestDto;
import reactor.core.publisher.Mono;

public interface RequestService {
    Mono<String> processRequest(RequestDto requestDto);
}