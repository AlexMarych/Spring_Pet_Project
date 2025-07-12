package org.spring.pet_project.Config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class XorValidator implements ConstraintValidator<XOR, Object> {

    private String[] fields;

    @Override
    public void initialize(XOR constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        int count = 0;

        for (String field : fields) {
            try {
                Object value = new BeanWrapperImpl(object).getPropertyValue(field);
                if (value != null) count++;
            } catch (Exception e) {
                return false;
            }
        }

        return count == 1;
    }
}

