package com.example.saleProject.routes.sales;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.usecases.sales.UpdateSaleUseCase;
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
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateSaleRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Update sale by Id", operationId = "updateSale",
            tags = "Sale", parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "SaleId")}, responses =
    @ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = SalesDTO.class)))))
    public RouterFunction<ServerResponse> updateRiderRouter(UpdateSaleUseCase useCase){
        return route(
                PUT("/v1/api/update/sale").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(SalesDTO.class)
                        .flatMap(salesDTO -> useCase.apply(salesDTO))
                        .flatMap(salesDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(salesDTO))
        );
    }
}