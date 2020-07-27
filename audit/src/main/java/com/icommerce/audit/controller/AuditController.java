package com.icommerce.audit.controller;

import com.icommerce.audit.model.request.AuditDTO;
import com.icommerce.audit.service.AuditService;
import com.icommerce.audit.model.response.AuditResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/audits")
public class AuditController {

    Logger log = LoggerFactory.getLogger(AuditController.class);

    @Autowired
    private AuditService auditService;

    @PostMapping()
    public ResponseEntity<AuditResponseDTO> audit(@Valid @RequestBody AuditDTO auditRequest) {

        long auditId = auditService.auditProductActivity(auditRequest);

        log.info("New audit record created. ID: {}", auditId);

        return ResponseEntity.status(HttpStatus.OK).body(new AuditResponseDTO(auditId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditDTO> getAudit(@PathVariable(name = "id") long id) {
        AuditDTO entity = auditService.findById(id);

        if (entity != null) {
            log.info("Found audit record with ID: {}", id);
            return ResponseEntity.ok(entity);
        }

        log.info("Could not find audit record with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
