package com.icommerce.product.proxy;

import com.icommerce.product.model.request.AuditDTO;
import com.icommerce.product.model.response.AuditResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit-service",
        path = "/audits",
        fallback = AuditProxyFallback.class)
public interface AuditProxy {

    @PostMapping
    AuditResponseDTO createAudit(@RequestBody AuditDTO auditDTO);
}
