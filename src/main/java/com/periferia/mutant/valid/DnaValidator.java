package com.periferia.mutant.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public void initialize(ValidDna constraintAnnotation) {
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        if (value == null || value.length == 0) {
            return false; // Array should not be null or empty
        }

        // Check if any element in the array is an empty string
        for (String element : value) {
            if (element == null || element.isEmpty()) {
                return false; // Array elements must not be empty strings
            }
        }

        // Check if all elements in the array have the same length
        int length = value[0].length();
        for (String element : value) {
            if (element.length() != length) {
                return false; // All elements should have the same length
            }
        }

        return true;
    }
}
