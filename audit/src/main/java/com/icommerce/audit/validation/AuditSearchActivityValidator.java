package com.icommerce.audit.validation;

import com.icommerce.audit.constant.ApplicationConstants;
import com.icommerce.audit.model.request.AuditDTO;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuditSearchActivityValidator implements ConstraintValidator<ValidSearchActivity, AuditDTO> {
    @Override
    public void initialize(ValidSearchActivity constraintAnnotation) {

    }

    @Override
    public boolean isValid(AuditDTO auditDTO, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(auditDTO.getActivity())
                || (auditDTO.getActivity().equalsIgnoreCase(ApplicationConstants.ACTIVITY_SEARCH)
                ? StringUtils.isNotBlank(auditDTO.getQuery())
                : true);
    }
}
