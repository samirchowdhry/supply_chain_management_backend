package com.example.supplychainsystem.Repository;

import com.example.supplychainsystem.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    @Query(value = "select * from supplier s where user_id = ?",nativeQuery = true)
    List<Supplier> findParticularSupplierList(Integer userId);

    @Query(value = "select id from supplier s where user_id = ?",nativeQuery = true)
    List<Integer> getSupplierId(Integer userId);

}
