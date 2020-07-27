package com.icommerce.audit.model.request;

import com.icommerce.audit.validation.ValidSearchActivity;
import com.icommerce.audit.validation.ValidViewActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidSearchActivity
@ValidViewActivity
public class AuditDTO {

    private String productCode;

    @NotNull(message = "cannot be blank")
    private String activity;

    private String query;

    private String filter;

    private String sortBy;

    private String order;

    private Integer page;

}
