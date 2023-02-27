package com.example.saleProject.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class Products {
    @Id
    private String id;
    private String name;
    private String inventory;
    private Boolean enabled;
    private String min;
    private String max;
}
