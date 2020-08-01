package com.icommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "sku")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class SkuEntity {

    @EmbeddedId
    private SkuId skuId;

    @Column(name="code")
    private String code;

    @Column(name="price")
    private Double price;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
