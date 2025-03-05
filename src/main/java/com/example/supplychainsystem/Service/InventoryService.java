package com.example.supplychainsystem.Service;

import com.example.supplychainsystem.Model.Inventory;
import com.example.supplychainsystem.Model.Supplier;
import com.example.supplychainsystem.Repository.InventoryRepository;
import com.example.supplychainsystem.Repository.SupplierRepository;
import com.example.supplychainsystem.RequestDTO.InventoryRequestDTO;
import com.example.supplychainsystem.ResponseDTO.InventoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public InventoryResponseDTO addInventory(InventoryRequestDTO inventoryRequestDTO) {

        Supplier supplier = supplierRepository.findById(inventoryRequestDTO.getSupplierId()).get();

        Inventory inventory = new Inventory();
        inventory.setPricePerUnit(inventoryRequestDTO.getPricePerUnit());
        inventory.setProductName(inventoryRequestDTO.getProductName());
        inventory.setQuantityAvailable(inventoryRequestDTO.getQuantityAvailable());
        inventory.setSupplier(supplier);

        Inventory inventoryObj = inventoryRepository.save(inventory);

        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();

        inventoryResponseDTO.setId(inventoryObj.getId());
        inventoryResponseDTO.setCreatedAt(inventoryObj.getCreatedAt());
        inventoryResponseDTO.setUpdatedAt(inventoryObj.getUpdatedAt());
        inventoryResponseDTO.setProductName(inventoryObj.getProductName());
        inventoryResponseDTO.setPricePerUnit(inventoryObj.getPricePerUnit());
        inventoryResponseDTO.setQuantityAvailable(inventoryObj.getQuantityAvailable());
        inventoryResponseDTO.setSupplierId(inventoryObj.getSupplier().getId());

        if (inventoryResponseDTO != null) {
            return inventoryResponseDTO;
        } else {
            return null;
        }

    }

    public InventoryResponseDTO updateInventory(Integer id,InventoryRequestDTO inventoryRequestDTO) {

        Inventory inventory = inventoryRepository.findById(id).get();

        inventory.setPricePerUnit(inventoryRequestDTO.getPricePerUnit());
        inventory.setProductName(inventoryRequestDTO.getProductName());
        inventory.setQuantityAvailable(inventoryRequestDTO.getQuantityAvailable());

        Inventory inventoryObj = inventoryRepository.save(inventory);

        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();

        inventoryResponseDTO.setId(inventoryObj.getId());
        inventoryResponseDTO.setCreatedAt(inventoryObj.getCreatedAt());
        inventoryResponseDTO.setUpdatedAt(inventoryObj.getUpdatedAt());
        inventoryResponseDTO.setProductName(inventoryObj.getProductName());
        inventoryResponseDTO.setPricePerUnit(inventoryObj.getPricePerUnit());
        inventoryResponseDTO.setQuantityAvailable(inventoryObj.getQuantityAvailable());
        inventoryResponseDTO.setSupplierId(inventoryObj.getSupplier().getId());

        if (inventoryResponseDTO != null) {
            return inventoryResponseDTO;
        } else {
            return null;
        }

    }


    public InventoryResponseDTO getInventoryById(Integer id) {
        Inventory inventory = inventoryRepository.findById(id).get();

        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        inventoryResponseDTO.setId(inventory.getId());
        inventoryResponseDTO.setCreatedAt(inventory.getCreatedAt());
        inventoryResponseDTO.setUpdatedAt(inventory.getUpdatedAt());
        inventoryResponseDTO.setProductName(inventory.getProductName());
        inventoryResponseDTO.setPricePerUnit(inventory.getPricePerUnit());
        inventoryResponseDTO.setQuantityAvailable(inventory.getQuantityAvailable());
        inventoryResponseDTO.setSupplierId(inventory.getSupplier().getId());

        if (inventoryResponseDTO != null) {
            return inventoryResponseDTO;
        } else {
            return null;
        }
    }

    public List<InventoryResponseDTO> getInventoryBySupplierId(Integer userId) {

        List<Supplier> supplierList = supplierRepository.findParticularSupplierList(userId);

        List<Integer> userList = new ArrayList<>();

        for (Supplier supp:supplierList) {
            userList.add(supp.getId());
        }

        List<Inventory> inventoryList = inventoryRepository.findInventoryBySupplierId(userList);

        List<InventoryResponseDTO> inventoryResponseDTOList = new ArrayList<>();

        for (Inventory inventory:inventoryList) {

            InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
            inventoryResponseDTO.setId(inventory.getId());
            inventoryResponseDTO.setCreatedAt(inventory.getCreatedAt());
            inventoryResponseDTO.setUpdatedAt(inventory.getUpdatedAt());
            inventoryResponseDTO.setProductName(inventory.getProductName());
            inventoryResponseDTO.setPricePerUnit(inventory.getPricePerUnit());
            inventoryResponseDTO.setQuantityAvailable(inventory.getQuantityAvailable());
            inventoryResponseDTO.setSupplierId(inventory.getSupplier().getId());
            inventoryResponseDTO.setProductCategories(inventory.getSupplier().getProductCategories());
            inventoryResponseDTOList.add(inventoryResponseDTO);

        }

        if (inventoryResponseDTOList != null) {
            return inventoryResponseDTOList;
        } else {
            return null;
        }


    }

    public List<InventoryResponseDTO> getInventoryList() {

        List<InventoryResponseDTO> inventoryResponseDTOList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryRepository.findAll();

        for (Inventory inventory:inventoryList) {

            InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
            inventoryResponseDTO.setId(inventory.getId());
            inventoryResponseDTO.setCreatedAt(inventory.getCreatedAt());
            inventoryResponseDTO.setUpdatedAt(inventory.getUpdatedAt());
            inventoryResponseDTO.setProductName(inventory.getProductName());
            inventoryResponseDTO.setPricePerUnit(inventory.getPricePerUnit());
            inventoryResponseDTO.setQuantityAvailable(inventory.getQuantityAvailable());
            inventoryResponseDTO.setSupplierId(inventory.getSupplier().getId());
            inventoryResponseDTO.setProductCategories(inventory.getSupplier().getProductCategories());
            inventoryResponseDTO.setSupplierName(inventory.getSupplier().getUser().getDisplayName());
            inventoryResponseDTOList.add(inventoryResponseDTO);

        }

        if (inventoryResponseDTOList != null) {
            return inventoryResponseDTOList;
        } else {
            return null;
        }


    }

}
