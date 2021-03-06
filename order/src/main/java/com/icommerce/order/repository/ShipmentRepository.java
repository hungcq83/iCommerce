package com.icommerce.order.repository;

import com.icommerce.order.entity.OrderEntity;
import com.icommerce.order.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {

}
