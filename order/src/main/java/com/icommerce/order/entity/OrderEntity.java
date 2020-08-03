package com.icommerce.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="ordered_date")
    private Timestamp orderedDate;

    @Column(name="order_total")
    private Double orderTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.PERSIST)
    private ShipmentEntity shipment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.PERSIST)
    private PaymentEntity payment;

    @Column(name="modified_date")
    @Transient
    private Timestamp modifiedDate;
}
