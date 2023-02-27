package com.example.saleProject.usecases.sales;

import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.mapper.SalesMapper;
import com.example.saleProject.repositories.ISalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Validated
public class CreateSaleUseCase implements Function<SalesDTO, Mono<SalesDTO>> {

    private final ISalesRepository repository;

    private final SalesMapper mapper;

    @Override
    public Mono<SalesDTO> apply(SalesDTO salesDTO) {
        return repository
                .save(mapper.dtoToSales().apply(salesDTO))
                .map(sale -> mapper.salesToDTO().apply(sale));
    }
}
