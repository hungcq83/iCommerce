package com.icommerce.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shipment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="shipment_code")
    private String shipmentCode;

    @Column(name="shipment_method")
    private String shipmentMethod;

    @Column(name="shipping_charge")
    private Double shippingCharge;

    @Column(name="shipping_address")
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;
}
