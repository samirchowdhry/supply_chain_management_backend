package com.example.supplychainsystem.RequestDTO;

import com.example.supplychainsystem.Model.Supplier;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Setter
@Getter
public class InventoryRequestDTO {

    private String productName;
    private Float pricePerUnit;
    private Integer quantityAvailable;
    private Integer supplierId;

}
