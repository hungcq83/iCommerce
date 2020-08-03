package com.icommerce.order.repository;

import com.icommerce.order.entity.OrderEntity;
import com.icommerce.order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
