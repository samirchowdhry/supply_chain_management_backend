package com.example.supplychainsystem.RequestDTO;

import com.example.supplychainsystem.Model.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDTO {

    private Integer inventoryId;
    private int quantityOrdered;
    private double totalPrice;
    private OrderItem.OrderStatus orderStatus;
    private Integer id;

}
