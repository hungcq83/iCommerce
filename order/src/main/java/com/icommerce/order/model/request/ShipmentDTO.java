package com.icommerce.order.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    private Long id;

    private String shipmentCode;

    private String shipmentMethod;

    private Double shippingCharge;

    private String shippingAddress;
}
