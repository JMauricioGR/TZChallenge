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
public class DeleteProductUseCase implements Function<String, Mono<Void>> {

    private final IProductsRepository repository;

    @Override
    public Mono<Void> apply(String productId){
        return repository
                .deleteById(productId);
    }
}
