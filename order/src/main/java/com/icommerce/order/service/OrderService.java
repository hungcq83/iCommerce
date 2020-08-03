package com.icommerce.order.service;

import com.icommerce.order.entity.OrderEntity;
import com.icommerce.order.entity.OrderItemEntity;
import com.icommerce.order.entity.PaymentEntity;
import com.icommerce.order.entity.ShipmentEntity;
import com.icommerce.order.model.request.OrderDTO;
import com.icommerce.order.repository.OrderItemRepository;
import com.icommerce.order.repository.OrderRepository;
import com.icommerce.order.repository.PaymentRepository;
import com.icommerce.order.repository.ShipmentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    Logger log = LoggerFactory.getLogger(OrderService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public long createOrder(OrderDTO productDTO) {
        OrderEntity orderEntity = mapper.map(productDTO, OrderEntity.class);
        orderEntity.getShipment().setOrder(orderEntity);
        orderEntity.getPayment().setOrder(orderEntity);
        orderEntity.getOrderItems().stream().forEach(item -> item.setOrder(orderEntity));

        orderRepository.save(orderEntity);

        log.info("Successfully saved new order entity with id: {}", orderEntity.getId());

        return orderEntity.getId();
    }

    public OrderDTO findOrder(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);

        if (orderEntityOptional.isPresent()) {
            OrderEntity order = orderEntityOptional.get();
            return mapper.map(order, OrderDTO.class);
        }

        return null;
    }

}
