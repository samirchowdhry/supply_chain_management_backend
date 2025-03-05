package com.example.supplychainsystem.RequestDTO;

import com.example.supplychainsystem.Model.UserInfo;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Setter
@Getter
public class SupplierRequestDTO {

    private String companyName;
    private String contactNumber;
    private String address;
    private String productCategories;
    private Integer userId;

}
