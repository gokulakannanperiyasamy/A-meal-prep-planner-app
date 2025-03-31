package com.example.mealprep.controller;

import com.example.mealprep.entity.Order;
import com.example.mealprep.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(orderDetails.getStatus());
            return ResponseEntity.ok(orderRepository.save(order));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }
}
