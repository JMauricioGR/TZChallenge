package com.example.saleProject.dto;

import com.example.saleProject.collections.Products;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

    @NotBlank
    private String date;
    @NotBlank
    private String idType;
    @NotBlank
    private String idNumber;
    @NotBlank
    private String clientName;
    @NotBlank
    private Products products;

}
