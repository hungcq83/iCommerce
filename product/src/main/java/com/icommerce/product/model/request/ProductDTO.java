package com.icommerce.product.model.request;

import com.icommerce.product.validation.Extended;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull(groups = Extended.class)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @Min(1)
    private Double price;

    @NotBlank
    private String brand;

    private String color;

}
