package com.example.saleProject.usecases.sales;

import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.mapper.SalesMapper;
import com.example.saleProject.repositories.ISalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetPagedSalesUseCase {

    private final ISalesRepository repository;

    private final SalesMapper mapper;

    public Flux<SalesDTO> apply(Pageable pageable) {

        return repository
                .findAll((Sort) pageable)
                .skip(pageable.getOffset())
                .take(pageable.getPageSize())
                .map( sale -> mapper.salesToDTO().apply(sale));
    }
}
