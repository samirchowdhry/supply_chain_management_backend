package com.example.supplychainsystem.Repository;

import com.example.supplychainsystem.Model.Inventory;
import com.example.supplychainsystem.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    @Query(value = "select * from inventory i where supplier_id in (?)",nativeQuery = true)
    List<Inventory> findInventoryBySupplierId(List<Integer> userId);

}
