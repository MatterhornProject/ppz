package com.walidatorpnr.utils;

//import javax.validation.Constraint;
//import javax.validation.Payload;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CapacityValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE,   })
@Retention(RetentionPolicy.RUNTIME)
public @interface Capacity {

    String message() default "Żeby sprawdzić 14-cyfrowy numer REGON, wszystkie pola od 1 do 14 muszą być wypełnione!";
   // String message() default "{javax.validation.constraints.Size.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min();
   // int max();
    boolean nullable();
}
