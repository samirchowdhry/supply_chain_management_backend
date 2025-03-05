package com.example.supplychainsystem.Controller;

import com.example.supplychainsystem.Model.Inventory;
import com.example.supplychainsystem.Model.Supplier;
import com.example.supplychainsystem.RequestDTO.InventoryRequestDTO;
import com.example.supplychainsystem.ResponseDTO.InventoryResponseDTO;
import com.example.supplychainsystem.Service.InventoryService;
import com.example.supplychainsystem.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/inventory/addInventory")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<InventoryResponseDTO> addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        InventoryResponseDTO inventoryResponseDTO = inventoryService.addInventory(inventoryRequestDTO);
        if(inventoryResponseDTO != null){
            return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/inventory/updateInventory")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<InventoryResponseDTO> updateInventory(@RequestParam Integer id,@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        InventoryResponseDTO inventoryResponseDTO = inventoryService.updateInventory(id,inventoryRequestDTO);
        if(inventoryResponseDTO != null){
            return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/getInventoryList")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<InventoryResponseDTO>> getInventoryList() {
        List<InventoryResponseDTO> inventoryResponseDTO = inventoryService.getInventoryList();
        if(inventoryResponseDTO.size() > 0){
            return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/admin/getInventoryListAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<InventoryResponseDTO>> getInventoryListAdmin() {
        List<InventoryResponseDTO> inventoryResponseDTO = inventoryService.getInventoryList();
        if(inventoryResponseDTO.size() > 0){
            return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getInventoryById")
    public ResponseEntity<InventoryResponseDTO> getInventoryById(@RequestParam Integer id) {
        InventoryResponseDTO inventoryResponseDTO = inventoryService.getInventoryById(id);
        if(inventoryResponseDTO != null){
            return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/inventory/getInventoryBySupplierId")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<List<InventoryResponseDTO>> getInventoryBySupplierId(@RequestParam Integer userId) {
        List<InventoryResponseDTO> inventoryResponseDTOList = inventoryService.getInventoryBySupplierId(userId);
        if(inventoryResponseDTOList != null){
            return new ResponseEntity<>(inventoryResponseDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

