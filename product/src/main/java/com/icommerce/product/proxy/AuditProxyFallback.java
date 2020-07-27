package com.icommerce.product.proxy;

import com.icommerce.product.model.request.AuditDTO;
import com.icommerce.product.model.response.AuditResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditProxyFallback implements AuditProxy {

    Logger log = LoggerFactory.getLogger(AuditProxy.class);

    @Override
    public AuditResponseDTO createAudit(AuditDTO auditDTO) {
        log.warn("Failed to call Audit service. Returning value from fallback method createAudit().");
        return new AuditResponseDTO(-1l);
    }

}
