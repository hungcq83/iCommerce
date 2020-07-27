package com.icommerce.audit.service;

import com.icommerce.audit.entity.AuditEntity;
import com.icommerce.audit.model.request.AuditDTO;
import com.icommerce.audit.repository.AuditRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuditService {

    Logger log = LoggerFactory.getLogger(AuditService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private AuditRepository auditRepository;

    @Transactional
    public long auditProductActivity(AuditDTO auditRequest) {

        AuditEntity auditEntity = AuditEntity.builder()
                .productCode(auditRequest.getProductCode())
                .activity(auditRequest.getActivity())
                .query(auditRequest.getQuery())
                .filter(auditRequest.getFilter())
                .sortBy(auditRequest.getSortBy())
                .order(auditRequest.getOrder())
                .page(auditRequest.getPage() != null ? auditRequest.getPage() : 0)
                .build();

        auditRepository.save(auditEntity);
        return auditEntity.getId();
    }

    public AuditDTO findById(long id) {
        Optional<AuditEntity> auditEntityOptional = auditRepository.findById(id);
        if (auditEntityOptional.isPresent()) {
            return mapper.map(auditEntityOptional.get(), AuditDTO.class);
        }

        return null;
    }
}
