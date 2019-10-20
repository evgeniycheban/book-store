package com.example.bookstore.validation.annotation;

import com.example.bookstore.validation.validator.UniqueIsbnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueIsbnValidator.class)
public @interface UniqueIsbn {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
