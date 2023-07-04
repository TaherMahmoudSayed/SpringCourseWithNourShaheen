package com.book.booksproject.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {IpAddressImpl.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IpAddress {
    String message() default "{validation.constraints.ip-address.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
