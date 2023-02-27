package com.example.saleProject.usecases.products;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.mapper.ProductsMapper;
import com.example.saleProject.repositories.IProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Validated
public class CreateProductUseCase implements Function<ProductsDTO, Mono<ProductsDTO>> {

    private  final IProductsRepository repository;

    private final ProductsMapper mapper;

    @Override
    public Mono<ProductsDTO> apply(ProductsDTO productsDTO){
        return repository
                .save(mapper.dtoToProducts().apply(productsDTO))
                .map(product -> mapper.productsToDTO().apply(product));
    }
}
