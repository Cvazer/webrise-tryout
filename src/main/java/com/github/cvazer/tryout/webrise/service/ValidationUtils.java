package com.github.cvazer.tryout.webrise.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;

import java.util.stream.Collectors;

public class ValidationUtils {

    public static boolean isInvalid(Object target, Validator validator, Logger logger) {
        var violations = validator.validate(target);
        if (!violations.isEmpty()) {
            logger.debug(violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";\n")));
            return true;
        }
        return false;
    }

}
