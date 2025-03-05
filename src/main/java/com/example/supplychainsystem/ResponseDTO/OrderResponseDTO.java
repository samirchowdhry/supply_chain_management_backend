package com.example.supplychainsystem.ResponseDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderResponseDTO {

    private Integer id;
    private Integer buyerId;
    private Integer supplierId;
    private String orderStatus;
    private Date orderDate;
    private Date deliveryDate;
    private Instant createdAt;
    private Instant updatedAt;
    private List<OrderItemResponseDTO> orderItems;

}
