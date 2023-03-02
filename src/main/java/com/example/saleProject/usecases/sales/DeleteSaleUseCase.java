package com.example.saleProject.usecases.sales;

import com.example.saleProject.repositories.ISalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DeleteSaleUseCase implements Function<String, Mono<Void>> {

    private final ISalesRepository repository;

    @Override
    public Mono<Void> apply(String saleId){
        return repository
                .deleteById(saleId);
    }
}
