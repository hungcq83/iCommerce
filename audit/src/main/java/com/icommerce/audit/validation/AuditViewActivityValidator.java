package com.icommerce.audit.validation;

import com.icommerce.audit.model.request.AuditDTO;
import com.icommerce.audit.constant.ApplicationConstants;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuditViewActivityValidator implements ConstraintValidator<ValidViewActivity, AuditDTO> {
    @Override
    public void initialize(ValidViewActivity constraintAnnotation) {

    }

    @Override
    public boolean isValid(AuditDTO auditDTO, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(auditDTO.getActivity())
            || (auditDTO.getActivity().equalsIgnoreCase(ApplicationConstants.ACTIVITY_VIEW)
                ? StringUtils.isNotBlank(auditDTO.getProductCode())
                : true);
    }
}
