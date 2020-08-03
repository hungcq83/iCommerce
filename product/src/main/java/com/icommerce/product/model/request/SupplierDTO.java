package com.icommerce.product.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private long id;

    private String name;

    private String code;

    private String address;

    private String email;

    private String phoneNumber;
}
