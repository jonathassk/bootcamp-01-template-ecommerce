package com.bootcampzup.mercadolivre.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Valor já existente";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldname();
    Class<?> domainClass();
}
