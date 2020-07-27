package com.icommerce.audit.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = AuditSearchActivityValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidSearchActivity {

    String message() default
            "Search activity requires query";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}