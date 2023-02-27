package com.example.saleProject.repositories;

import com.example.saleProject.collections.Sales;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends ReactiveMongoRepository<Sales, String> {
}
