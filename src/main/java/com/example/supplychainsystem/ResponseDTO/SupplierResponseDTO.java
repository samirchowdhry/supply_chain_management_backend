package com.example.supplychainsystem.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class SupplierResponseDTO {

    private Integer id;
    private String companyName;
    private String contactNumber;
    private String address;
    private String productCategories;
    private Integer userId;
    private Instant createdAt;
    private Instant updatedAt;
    private String supplierName;

}
