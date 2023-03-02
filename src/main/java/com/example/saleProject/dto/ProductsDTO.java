package com.example.saleProject.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    private String inventory;
    private Boolean enabled;
    @Min(value = 0, message = "El valor mínimo no puede ser menor a cero")
    private String min;
    @Min(value = 1, message = "El valor máximo no puede ser menor a uno")
    private String max;
}
