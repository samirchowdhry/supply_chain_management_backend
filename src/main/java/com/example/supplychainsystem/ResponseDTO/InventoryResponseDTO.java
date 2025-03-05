package com.example.supplychainsystem.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class InventoryResponseDTO {

    private Integer id;
    private String productName;
    private Float pricePerUnit;
    private Integer quantityAvailable;
    private Integer supplierId;
    private Instant createdAt;
    private Instant updatedAt;
    private String productCategories;
    private String supplierName;

}
