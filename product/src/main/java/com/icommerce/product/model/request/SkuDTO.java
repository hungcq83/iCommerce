package com.icommerce.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuDTO {

    private String code;

    @JsonProperty("color")
    private String skuIdColor;

    @JsonProperty("size")
    private String skuIdSize;

    private Double price;

    private String productId;
}
