package com.icommerce.order.controller;

import com.icommerce.order.model.request.OrderDTO;
import com.icommerce.order.model.response.GenericResponseDTO;
import com.icommerce.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> viewOrder(@NotNull @Min(1) @PathVariable("id") Long id) {

        OrderDTO orderDTO = orderService.findOrder(id);

        if (orderDTO != null) {
            log.info("Found product with ID: {}", id);
            return ResponseEntity.ok(orderDTO);
        }

        log.info("Could not find product with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        long productId = orderService.createOrder(orderDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponseDTO(productId));
    }

}
