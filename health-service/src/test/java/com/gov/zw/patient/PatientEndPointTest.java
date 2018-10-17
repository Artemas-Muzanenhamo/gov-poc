package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PatientEndPointTest {

    @Test
    public void getAllPatientsTest() {
        HandlerFunction<ServerResponse> handlerFunction = request -> ServerResponse.ok().build();
        RouterFunction<ServerResponse> routerFunction1 = request -> Mono.empty();
        RouterFunction<ServerResponse> routerFunction2 = request -> Mono.just(handlerFunction);

        RouterFunction<ServerResponse> result = routerFunction1.and(routerFunction2);
        assertNotNull(result);

        MockServerRequest request = MockServerRequest.builder().build();
        Mono<HandlerFunction<ServerResponse>> resultHandlerFunction = result.route(request);

        StepVerifier.create(resultHandlerFunction)
                .expectNext(handlerFunction)
                .expectComplete()
                .verify();
    }
}
