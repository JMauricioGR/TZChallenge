package com.example.saleProject.routes.sales;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.usecases.sales.GetPagedSalesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetPagedSalesRoute {

    private final GetPagedSalesUseCase useCase;

    public GetPagedSalesRoute(GetPagedSalesUseCase useCase) {
        this.useCase = useCase;
    }


    @Bean
    public RouterFunction<ServerResponse> getPagedSalesRoute(){
        return route(
                GET("/v1/api/sales/"), this::handleGetSales);
    }

    private Mono<ServerResponse> handleGetSales(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("0"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        Pageable pageable = PageRequest.of(page, size);
        Flux<SalesDTO> sales = useCase.apply(pageable);
        return ServerResponse.ok().body(sales,ProductsDTO.class);
    }
}
