package com.icommerce.order.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private String orderStatus;

    private Timestamp orderedDate;

    private Double orderTotal;

    private List<OrderItemDTO> orderItems;

    private ShipmentDTO shipment;

    private PaymentDTO payment;

}
