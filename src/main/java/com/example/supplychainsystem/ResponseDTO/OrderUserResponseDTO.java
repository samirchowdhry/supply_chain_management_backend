package com.example.supplychainsystem.ResponseDTO;

import com.example.supplychainsystem.Model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUserResponseDTO {

    private Date deliveryDate;
    private String productName;
    private double totalPrice;
    private String displayName;
    private String companyName;
    private Integer qtyOrdered;
    private Integer orderId;
    private String orderStatus;
    private String category;

}
