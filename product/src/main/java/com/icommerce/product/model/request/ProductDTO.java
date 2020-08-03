package com.icommerce.product.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.icommerce.product.validation.Extended;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull(groups = Extended.class)
    private Long id;

    private String categoryId;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String brand;

    private List<SkuDTO> skus;

    @JsonProperty("category")
    private String categoryName;

    private Long supplierId;

}
