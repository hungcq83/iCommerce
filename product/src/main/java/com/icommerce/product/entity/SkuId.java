package com.icommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SkuId implements Serializable {

    @Column(name = "product_id")
    private String productId;
    private String color;
    private String size;
}
