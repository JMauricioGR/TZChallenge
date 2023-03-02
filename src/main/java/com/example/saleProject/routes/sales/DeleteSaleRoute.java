package com.example.saleProject.routes.sales;

import com.example.saleProject.usecases.sales.DeleteSaleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class DeleteSaleRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Delete sale by Id", operationId = "deleteSale",
            tags = "Sale", parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "SaleId")}, responses =
    @ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = Void.class)))))
    public RouterFunction<ServerResponse> deleteByRiderCodeRouter(DeleteSaleUseCase useCase) {
        return route(
                DELETE("/v1/api/delete/sale/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .body(BodyInserters
                                .fromPublisher(useCase.apply(request.pathVariable("id")), Void.class))
        );

    }
}