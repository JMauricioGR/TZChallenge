package com.example.saleProject.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class Sales {
    @Id
    private String id;
    private String date;
    private String idType;
    private Boolean idNumber;
    private String clientName;
    private Products products;
}