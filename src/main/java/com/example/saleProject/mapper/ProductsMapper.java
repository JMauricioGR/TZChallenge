package com.example.saleProject.mapper;

import com.example.saleProject.collections.Products;
import com.example.saleProject.dto.ProductsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductsMapper {

    private ModelMapper mapper;

    public ProductsMapper(ModelMapper mapper) { this.mapper = mapper; }

    public Function<Products, ProductsDTO> productsToDTO() { return productsDTO -> mapper.map(productsDTO, ProductsDTO.class);}

    public Function<ProductsDTO, Products> dtoToProducts() { return products -> mapper.map(products, Products.class);}
}
