package com.example.saleProject.routes.products;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.usecases.products.CreateProductUseCase;
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
public class CreateProductRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Create product", operationId = "createProduct",
            tags = "Product", responses = @ApiResponse(
                    responseCode = "200", content = @Content(schema = @Schema(implementation = ProductsDTO.class)))))
    public RouterFunction<ServerResponse> createProductRoute(CreateProductUseCase useCase){

        return route(
                POST("/v1/api/save/product").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductsDTO.class)
                        .flatMap(productsDTO -> useCase.apply(productsDTO))
                        .flatMap(productsDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(productsDTO))
        );
    }

}
