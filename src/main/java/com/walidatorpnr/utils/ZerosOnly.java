package com.walidatorpnr.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ZerosOnlyValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE,   })
@Retention(RetentionPolicy.RUNTIME)
public @interface ZerosOnly {

    String message() default "Wartości nie mogą być równe 0 we wszyskich polach naraz!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean allZeros();
}


