package com.example.saleProject.usecases.sales;

import com.example.saleProject.dto.SalesDTO;
import com.example.saleProject.mapper.SalesMapper;
import com.example.saleProject.repositories.ISalesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UpdateSaleUseCase implements Function<SalesDTO, Mono<SalesDTO>> {

    private final ISalesRepository repository;

    private final SalesMapper mapper;

    @Override
    public Mono<SalesDTO> apply(SalesDTO salesDTO){
        return repository
                .findById(salesDTO.getId())
                .flatMap(sale -> repository
                        .save(mapper
                                .dtoToSales()
                                .apply(salesDTO))
                        .map(saleB -> mapper.salesToDTO()
                                .apply(saleB)))
                .switchIfEmpty(Mono.error(new IllegalStateException("La venta a actualizar no existe")));
    }
}
