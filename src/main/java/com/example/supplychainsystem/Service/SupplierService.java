package com.example.supplychainsystem.Service;

import com.example.supplychainsystem.Model.Supplier;
import com.example.supplychainsystem.Model.UserInfo;
import com.example.supplychainsystem.Repository.SupplierRepository;
import com.example.supplychainsystem.Repository.UserInfoRepository;
import com.example.supplychainsystem.RequestDTO.SupplierRequestDTO;
import com.example.supplychainsystem.ResponseDTO.SupplierResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public SupplierResponseDTO addSupplier(SupplierRequestDTO supplierRequestDTO) {

        UserInfo userInfo = userInfoRepository.findById(supplierRequestDTO.getUserId()).get();

        Supplier supplier = new Supplier();
        supplier.setCompanyName(supplierRequestDTO.getCompanyName());
        supplier.setContactNumber(supplierRequestDTO.getContactNumber());
        supplier.setProductCategories(supplierRequestDTO.getProductCategories());
        supplier.setUser(userInfo);
        supplier.setAddress(supplierRequestDTO.getAddress());

        Supplier supplierObj = supplierRepository.save(supplier);

        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
        supplierResponseDTO.setId(supplierObj.getId());
        supplierResponseDTO.setCreatedAt(supplierObj.getCreatedAt());
        supplierResponseDTO.setUpdatedAt(supplierObj.getUpdatedAt());
        supplierResponseDTO.setCompanyName(supplierObj.getCompanyName());
        supplierResponseDTO.setContactNumber(supplierObj.getContactNumber());
        supplierResponseDTO.setProductCategories(supplierObj.getProductCategories());
        supplierResponseDTO.setUserId(supplierObj.getUser().getId());
        supplierResponseDTO.setAddress(supplierObj.getAddress());

        if(supplierResponseDTO != null){
            return supplierResponseDTO;
        }
        else{
            return null;
        }


    }

    public SupplierResponseDTO getSupplierById(Integer id) {

        Supplier supplier = supplierRepository.findById(id).get();

        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
        supplierResponseDTO.setId(supplier.getId());
        supplierResponseDTO.setCreatedAt(supplier.getCreatedAt());
        supplierResponseDTO.setUpdatedAt(supplier.getUpdatedAt());
        supplierResponseDTO.setCompanyName(supplier.getCompanyName());
        supplierResponseDTO.setContactNumber(supplier.getContactNumber());
        supplierResponseDTO.setProductCategories(supplier.getProductCategories());
        supplierResponseDTO.setUserId(supplier.getUser().getId());
        supplierResponseDTO.setAddress(supplier.getAddress());

        if(supplierResponseDTO != null){
            return supplierResponseDTO;
        }
        else{
            return null;
        }
    }

    public List<SupplierResponseDTO> getParticularSupplierList(Integer userId) {
        List<Supplier> supplierList = supplierRepository.findParticularSupplierList(userId);
        List<SupplierResponseDTO> supplierResponseDTOList = new ArrayList<>();
        for (Supplier supplier:supplierList) {
            SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
            supplierResponseDTO.setId(supplier.getId());
            supplierResponseDTO.setCreatedAt(supplier.getCreatedAt());
            supplierResponseDTO.setUpdatedAt(supplier.getUpdatedAt());
            supplierResponseDTO.setCompanyName(supplier.getCompanyName());
            supplierResponseDTO.setContactNumber(supplier.getContactNumber());
            supplierResponseDTO.setProductCategories(supplier.getProductCategories());
            supplierResponseDTO.setUserId(supplier.getUser().getId());
            supplierResponseDTO.setAddress(supplier.getAddress());
            supplierResponseDTOList.add(supplierResponseDTO);
        }

        if(supplierResponseDTOList != null){
            return supplierResponseDTOList;
        }
        else{
            return null;
        }


    }

    public List<SupplierResponseDTO> getSupplierList() {
        List<Supplier> supplierList = supplierRepository.findAll();
        List<SupplierResponseDTO> supplierResponseDTOList = new ArrayList<>();
        for (Supplier supplier:supplierList) {
            SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();
            supplierResponseDTO.setId(supplier.getId());
            supplierResponseDTO.setCreatedAt(supplier.getCreatedAt());
            supplierResponseDTO.setUpdatedAt(supplier.getUpdatedAt());
            supplierResponseDTO.setCompanyName(supplier.getCompanyName());
            supplierResponseDTO.setContactNumber(supplier.getContactNumber());
            supplierResponseDTO.setProductCategories(supplier.getProductCategories());
            supplierResponseDTO.setUserId(supplier.getUser().getId());
            supplierResponseDTO.setAddress(supplier.getAddress());
            supplierResponseDTO.setSupplierName(supplier.getUser().getDisplayName());
            supplierResponseDTOList.add(supplierResponseDTO);
        }

        if(supplierResponseDTOList != null){
            return supplierResponseDTOList;
        }
        else{
            return null;
        }


    }



}
