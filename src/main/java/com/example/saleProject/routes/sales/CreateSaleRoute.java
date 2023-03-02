package com.example.saleProject.routes.sales;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.usecases.sales.CreateSaleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateSaleRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Create sale", operationId = "createSale",
            tags = "Sale", responses = @ApiResponse(
            responseCode = "200", content = @Content(schema = @Schema(implementation = SalesDTO.class)))))
    public RouterFunction<ServerResponse> createProductRoute(CreateSaleUseCase useCase){

        return route(
                POST("/v1/api/save/sale").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(SalesDTO.class)
                        .flatMap(salesDTO -> useCase.apply(salesDTO))
                        .flatMap(salesDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(salesDTO))
        );
    }

}


