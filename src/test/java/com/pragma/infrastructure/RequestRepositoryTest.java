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