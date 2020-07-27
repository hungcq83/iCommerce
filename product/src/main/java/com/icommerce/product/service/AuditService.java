package com.icommerce.product.service;

import com.icommerce.product.constant.ApplicationConstants;
import com.icommerce.product.model.request.AuditDTO;
import com.icommerce.product.model.response.AuditResponseDTO;
import com.icommerce.product.proxy.AuditProxy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuditService {

    private Logger log = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditProxy auditProxy;

    @Async
    public void createViewAudit(String productCode) {
        AuditDTO auditDTO = AuditDTO.builder()
                .activity(ApplicationConstants.ACTIVITY_VIEW)
                .productCode(productCode)
                .build();
        auditProxy.createAudit(auditDTO);
    }

    @Async
    public CompletableFuture<AuditResponseDTO> createSearchAudit(String query, String filter, String sortBy,
                                                                 String order, Integer page) {
        AuditDTO auditDTO = AuditDTO.builder()
                .activity(ApplicationConstants.ACTIVITY_SEARCH)
                .query(query)
                .filter(filter)
                .sortBy(sortBy)
                .order(StringUtils.isNotBlank(order) ? order : "asc")
                .page(page != null ? page : 0)
                .build();
        return CompletableFuture.completedFuture(auditProxy.createAudit(auditDTO));

    }
}