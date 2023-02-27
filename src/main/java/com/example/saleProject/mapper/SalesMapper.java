package com.example.saleProject.mapper;

import com.example.saleProject.collections.Sales;
import com.example.saleProject.dto.SalesDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesMapper {

    private ModelMapper mapper;

    public SalesMapper(ModelMapper mapper) { this.mapper = mapper; }

    public Function<Sales, SalesDTO> salesToDTO(){ return salesToDTO -> mapper.map(salesToDTO, SalesDTO.class); }

    public Function<SalesDTO, Sales> dtoToSales(){ return sales -> mapper.map(sales, Sales.class); }
}
