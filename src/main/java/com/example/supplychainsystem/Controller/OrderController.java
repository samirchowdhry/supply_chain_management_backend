package com.example.supplychainsystem.Controller;

import com.example.supplychainsystem.Model.OrderItem;
import com.example.supplychainsystem.Model.Orders;
import com.example.supplychainsystem.RequestDTO.InventoryRequestDTO;
import com.example.supplychainsystem.RequestDTO.OrderItemRequestDTO;
import com.example.supplychainsystem.RequestDTO.OrderRequestDTO;
import com.example.supplychainsystem.ResponseDTO.InventoryResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderListResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderUserResponseDTO;
import com.example.supplychainsystem.Service.InventoryService;
import com.example.supplychainsystem.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class OrderController {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/user/addOrder")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        String orderResponseDTO = ordersService.addOrder(orderRequestDTO);
        if(!orderResponseDTO.equals("")){
            return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/getOrdersByUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<OrderUserResponseDTO>> getOrdersByUser(@RequestParam Integer userId) {
        List<OrderUserResponseDTO> orderUserResponseDTOList = ordersService.getOrderByUser(userId);
        if(orderUserResponseDTOList.size() > 0){
            return new ResponseEntity<>(orderUserResponseDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/getOrdersBySupplier")
    @PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
    public ResponseEntity<List<OrderUserResponseDTO>> getOrdersBySupplier(@RequestParam Integer userId) {
        List<OrderUserResponseDTO> orderUserResponseDTOList = ordersService.getOrderBySupplier(userId);

        if (orderUserResponseDTOList != null && !orderUserResponseDTOList.isEmpty()) {
            int size = orderUserResponseDTOList.size();
            return new ResponseEntity<>(orderUserResponseDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/order/getOrderList")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OrderListResponseDTO>> getOrderList() {
        List<OrderListResponseDTO> orderListResponseDTOS = ordersService.getOrderList();

        if (orderListResponseDTOS != null && !orderListResponseDTOS.isEmpty()) {
            int size = orderListResponseDTOS.size();
            return new ResponseEntity<>(orderListResponseDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/order/updateOrder")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateOrder(@RequestBody OrderItemRequestDTO orders) {
        String orderResponseDTO = ordersService.updateOrder(orders);
        if(!orderResponseDTO.equals("")){
            return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
