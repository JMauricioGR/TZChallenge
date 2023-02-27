package com.example.saleProject.repositories;

import com.example.saleProject.collections.Products;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends ReactiveMongoRepository<Products, String> {
}
