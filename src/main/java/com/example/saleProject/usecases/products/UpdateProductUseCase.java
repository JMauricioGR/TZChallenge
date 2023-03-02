package com.example.saleProject.usecases.products;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.mapper.ProductsMapper;
import com.example.saleProject.repositories.IProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase implements Function<ProductsDTO, Mono<ProductsDTO>> {

    private final IProductsRepository repository;

    private final ProductsMapper mapper;

    @Override
    public Mono<ProductsDTO> apply(ProductsDTO productsDTO){
        return repository
                .findById(productsDTO.getId())
                .flatMap(product -> repository
                        .save(mapper
                                .dtoToProducts().apply(productsDTO))
                        .map(productB -> mapper.productsToDTO()
                                .apply(productB)))
                .switchIfEmpty(Mono.error(new IllegalStateException("EL productos solicitado no existe")));
    }
}
