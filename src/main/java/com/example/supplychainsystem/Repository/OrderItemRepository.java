package com.example.supplychainsystem.Repository;

import com.example.supplychainsystem.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query(value = "select * from order_item oi where order_id in (?)",nativeQuery = true)
    List<OrderItem> getOrderItem(List<Integer> orderId);

}
