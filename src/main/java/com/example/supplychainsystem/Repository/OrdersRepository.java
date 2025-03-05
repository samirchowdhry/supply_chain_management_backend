package com.example.supplychainsystem.Repository;

import com.example.supplychainsystem.Model.Orders;
import com.example.supplychainsystem.ResponseDTO.OrderUserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "select id from orders o where supplier_id in (?)",nativeQuery = true)
    List<Integer> getOrderId(List<Integer> supplierId);

    @Query(value = "select id from orders o where buyer_id = ?",nativeQuery = true)
    List<Integer> getOrderByUserId(Integer userId);


}
