package com.icommerce.audit.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = AuditViewActivityValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented


public @interface ValidViewActivity {

    String message() default
            "View activity requires product code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}