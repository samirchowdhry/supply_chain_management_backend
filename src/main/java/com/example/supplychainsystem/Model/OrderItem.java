package com.example.supplychainsystem.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id" , nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id" , nullable = false)
    private Inventory inventory;

    private int quantityOrdered;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderItem.OrderStatus orderStatus;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public enum OrderStatus {
        PENDING, SHIPPED, DELIVERED
    }

}
