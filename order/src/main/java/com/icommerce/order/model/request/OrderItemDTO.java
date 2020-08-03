package com.icommerce.order.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private String name;

    private String code;

    private String brand;

    private Double price;

    private String color;

    private String size;

}
