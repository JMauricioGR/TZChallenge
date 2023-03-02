package com.example.saleProject.routes.products;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.usecases.products.GetPagedProductsUseCase;
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
public class GetPagedProductsRoute {

    private final GetPagedProductsUseCase useCase;

    public GetPagedProductsRoute(GetPagedProductsUseCase useCase) {
        this.useCase = useCase;
    }

    @Bean
    public RouterFunction<ServerResponse> getPagedProductsRoute(){
        return route(
                GET("/v1/api/products/"), this::handleGetProducts);
    }

    private Mono<ServerResponse> handleGetProducts(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("0"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        Pageable pageable = PageRequest.of(page, size);
        Flux<ProductsDTO> products = useCase.apply(pageable);
        return ServerResponse.ok().body(products,ProductsDTO.class);
    }
}
