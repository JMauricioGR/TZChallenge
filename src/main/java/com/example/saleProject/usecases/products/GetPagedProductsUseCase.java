package com.example.saleProject.usecases.products;

import com.example.saleProject.dto.ProductsDTO;
import com.example.saleProject.mapper.ProductsMapper;
import com.example.saleProject.repositories.IProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetPagedProductsUseCase {

    private final IProductsRepository repository;

    private final ProductsMapper mapper;

    public Flux<ProductsDTO> apply(Pageable pageable) {
        return repository
                .findAll((Sort) pageable)
                .skip(pageable.getOffset())
                .take(pageable.getPageSize())
                .map(product -> mapper.productsToDTO().apply(product));
    }
}
