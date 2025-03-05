package com.example.supplychainsystem.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderListResponseDTO {

    private String orderStatus;
    private Integer qtyOrdered;
    private Double totalPrice;
    private String productName;
    private String productCategory;
    private Date deliveryDate;
    private String buyerName;
    private String supplierName;
    private Integer orderId;
    private Integer orderItemId;

}
