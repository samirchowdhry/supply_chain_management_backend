package com.example.supplychainsystem.RequestDTO;

import com.example.supplychainsystem.Model.Orders;
import com.example.supplychainsystem.Model.Supplier;
import com.example.supplychainsystem.Model.UserInfo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderRequestDTO {

    private Integer buyerId;
    private Integer supplierId;
    private List<OrderItemRequestDTO> orderItems;
    private Date deliveryDate;
    private String orderStatus;


}
