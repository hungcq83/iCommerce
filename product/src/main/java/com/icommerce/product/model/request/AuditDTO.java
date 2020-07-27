package com.icommerce.product.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {

    private String productCode;

    private String activity;

    private String query;

    private String filter;

    private String sortBy;

    private String order;

    private Integer page;

}
