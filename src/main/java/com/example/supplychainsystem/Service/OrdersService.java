package com.example.supplychainsystem.Service;

import com.example.supplychainsystem.Model.*;
import com.example.supplychainsystem.Repository.*;
import com.example.supplychainsystem.RequestDTO.InventoryRequestDTO;
import com.example.supplychainsystem.RequestDTO.OrderItemRequestDTO;
import com.example.supplychainsystem.RequestDTO.OrderRequestDTO;
import com.example.supplychainsystem.ResponseDTO.InventoryResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderListResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderResponseDTO;
import com.example.supplychainsystem.ResponseDTO.OrderUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public String addOrder(OrderRequestDTO orderRequestDTO) {

        Supplier supplier = supplierRepository.findById(orderRequestDTO.getSupplierId()).get();
        UserInfo userInfo = userInfoRepository.findById(orderRequestDTO.getBuyerId()).get();

        Orders orders = new Orders();
        orders.setDeliveryDate(orderRequestDTO.getDeliveryDate());
        orders.setOrderDate(new Date());
        orders.setBuyer(userInfo);
        orders.setSupplier(supplier);

        Orders ordersObj = ordersRepository.save(orders);

        for (OrderItemRequestDTO itemDTO : orderRequestDTO.getOrderItems()) {
            Optional<Inventory> inventoryItem = inventoryRepository.findById(itemDTO.getInventoryId());

            if (inventoryItem.isPresent()) {
                Inventory inventory = inventoryItem.get();

                if (inventory.getQuantityAvailable() >= itemDTO.getQuantityOrdered()) {
                    inventory.setQuantityAvailable(inventory.getQuantityAvailable() - itemDTO.getQuantityOrdered());
                    inventoryRepository.save(inventory);

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(ordersObj);
                    orderItem.setInventory(inventory);
                    orderItem.setQuantityOrdered(itemDTO.getQuantityOrdered());
                    orderItem.setTotalPrice(itemDTO.getTotalPrice());
                    orderItem.setOrderStatus(OrderItem.OrderStatus.valueOf("PENDING"));

                    orderItemRepository.save(orderItem);
                } else {
                    return "Not enough stock for product: " + inventory.getProductName();
                }
            } else {
                return "Inventory item not found!";
            }
        }

        return "Order placed successfully!";
    }

    public List<OrderUserResponseDTO> getOrderByUser(Integer userId){

        List<Integer> orderList = ordersRepository.getOrderByUserId(userId);
        List<OrderItem> orderItemList = orderItemRepository.getOrderItem(orderList);

        List<OrderUserResponseDTO> orderUserResponseDTOList = new ArrayList<>();

        for (OrderItem order:orderItemList) {
            OrderUserResponseDTO orderUserResponseDTO = new OrderUserResponseDTO();
            orderUserResponseDTO.setOrderStatus(order.getOrderStatus().name());
            orderUserResponseDTO.setQtyOrdered(order.getQuantityOrdered());
            orderUserResponseDTO.setTotalPrice(order.getTotalPrice());
            orderUserResponseDTO.setProductName(order.getInventory().getProductName());
            orderUserResponseDTO.setCategory(order.getInventory().getSupplier().getProductCategories());
            orderUserResponseDTO.setDeliveryDate(order.getOrder().getDeliveryDate());
            orderUserResponseDTO.setDisplayName(order.getOrder().getSupplier().getUser().getDisplayName());
            orderUserResponseDTOList.add(orderUserResponseDTO);
        }

        if(orderUserResponseDTOList.size() > 0){
            return orderUserResponseDTOList;
        }
        else{
            return null;
        }

    }

    public List<OrderListResponseDTO> getOrderList(){

        List<OrderItem> orderItemList = orderItemRepository.findAll();

        List<OrderListResponseDTO> orderUserResponseDTOList = new ArrayList<>();

        for (OrderItem order:orderItemList) {
            OrderListResponseDTO orderUserResponseDTO = new OrderListResponseDTO();
            orderUserResponseDTO.setOrderStatus(order.getOrderStatus().name());
            orderUserResponseDTO.setQtyOrdered(order.getQuantityOrdered());
            orderUserResponseDTO.setTotalPrice(order.getTotalPrice());
            orderUserResponseDTO.setProductName(order.getInventory().getProductName());
            orderUserResponseDTO.setProductCategory(order.getInventory().getSupplier().getProductCategories());
            orderUserResponseDTO.setDeliveryDate(order.getOrder().getDeliveryDate());
            orderUserResponseDTO.setSupplierName(order.getOrder().getSupplier().getUser().getDisplayName());
            orderUserResponseDTO.setBuyerName(order.getOrder().getBuyer().getDisplayName());
            orderUserResponseDTO.setOrderId(order.getOrder().getId());
            orderUserResponseDTO.setOrderItemId(order.getId());
            orderUserResponseDTOList.add(orderUserResponseDTO);
        }

        if(orderUserResponseDTOList.size() > 0){
            return orderUserResponseDTOList;
        }
        else{
            return null;
        }

    }


    public List<OrderUserResponseDTO> getOrderBySupplier(Integer userId) {

        List<Integer> supplierList = supplierRepository.getSupplierId(userId);
        if (supplierList != null) {
            List<Integer> orderList = ordersRepository.getOrderId(supplierList);

            List<OrderItem> orderItemList = orderItemRepository.getOrderItem(orderList);

            List<OrderUserResponseDTO> orderUserResponseDTOList = new ArrayList<>();

            for (OrderItem order : orderItemList) {
                OrderUserResponseDTO orderUserResponseDTO = new OrderUserResponseDTO();
                orderUserResponseDTO.setProductName(order.getInventory().getProductName());
                orderUserResponseDTO.setDisplayName(order.getOrder().getBuyer().getDisplayName());
                orderUserResponseDTO.setOrderStatus(order.getOrderStatus().name());
                orderUserResponseDTO.setDeliveryDate(order.getOrder().getDeliveryDate());
                orderUserResponseDTO.setTotalPrice(order.getTotalPrice());
                orderUserResponseDTO.setQtyOrdered(order.getQuantityOrdered());
                orderUserResponseDTO.setOrderId(order.getId());
                orderUserResponseDTOList.add(orderUserResponseDTO);

            }

            if (orderUserResponseDTOList.size() > 0) {
                return orderUserResponseDTOList;
            } else {
                return null;
            }

        }
        return null;
    }

    public String updateOrder(OrderItemRequestDTO ordersObj) {

        OrderItem orderItem = orderItemRepository.findById(ordersObj.getId()).get();

        orderItem.setOrderStatus(OrderItem.OrderStatus.valueOf(ordersObj.getOrderStatus().name()));

        orderItemRepository.save(orderItem);

        return "Status Updated";

    }




}
