package com.example.supplychainsystem.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDTO {

    private Integer id;
    private int quantityOrdered;
    private double totalPrice;

}
