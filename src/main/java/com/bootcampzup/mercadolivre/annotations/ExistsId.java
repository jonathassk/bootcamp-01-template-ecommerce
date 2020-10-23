package com.bootcampzup.mercadolivre.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {existsIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExistsId {
    String message() default "não foi encontrado objeto com esse id";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldname();
    Class<?> domainClass();
}
