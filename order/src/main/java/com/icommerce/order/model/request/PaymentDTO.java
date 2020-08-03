package com.icommerce.order.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private String code;

    private Double amount;

    private String cardNumber;

    private String cardType;
}
