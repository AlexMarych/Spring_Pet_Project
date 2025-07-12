package org.spring.pet_project.Config;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XorValidator.class)
@Target( ElementType.TYPE )
@Retention(RetentionPolicy.RUNTIME)
public @interface XOR {
    String message() default " more than one entity is found ";

    String[] fields();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
