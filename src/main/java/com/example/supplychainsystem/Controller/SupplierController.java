package com.example.supplychainsystem.Controller;

import com.example.supplychainsystem.Model.Supplier;
import com.example.supplychainsystem.Model.UserInfo;
import com.example.supplychainsystem.RequestDTO.SupplierRequestDTO;
import com.example.supplychainsystem.ResponseDTO.SupplierResponseDTO;
import com.example.supplychainsystem.ResponseDTO.UserResponseDTO;
import com.example.supplychainsystem.Service.SupplierService;
import com.example.supplychainsystem.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/supplier/addSupplier")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<SupplierResponseDTO> addSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        SupplierResponseDTO supplierResponseDTO = supplierService.addSupplier(supplierRequestDTO);
        if(supplierResponseDTO != null){
            return new ResponseEntity<>(supplierResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getSupplierById")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@RequestParam Integer id) {
        SupplierResponseDTO supplierResponseDTO = supplierService.getSupplierById(id);
        if(supplierResponseDTO != null){
            return new ResponseEntity<>(supplierResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/supplier/getUserByEmail")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        UserResponseDTO userResponseDTOList = userInfoService.getUserByEmail(email);
        if(userResponseDTOList != null){
            return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/getUserByEmailUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserResponseDTO> getUserByEmailUser(@RequestParam String email) {
        UserResponseDTO userResponseDTOList = userInfoService.getUserByEmail(email);
        if(userResponseDTOList != null){
            return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/admin/getSupplierList")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<SupplierResponseDTO>> getSupplierList() {
        List<SupplierResponseDTO> supplierList = supplierService.getSupplierList();
        if(supplierList.size() > 0){
            return new ResponseEntity<>(supplierList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/supplier/getParticularSupplierList")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<List<SupplierResponseDTO>> getParticularSupplierList(@RequestParam Integer userId) {
        List<SupplierResponseDTO> supplierResponseDTOList = supplierService.getParticularSupplierList(userId);
        if(supplierResponseDTOList != null){
            return new ResponseEntity<>(supplierResponseDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
