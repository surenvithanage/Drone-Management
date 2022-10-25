package com.server.musalasoft.drone_management.validator;

import com.server.musalasoft.drone_management.annotation.enums.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<EnumValue, CharSequence> {
    private List<String> values;

    @Override
    public void initialize(EnumValue annotation) {
        values = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        return values.contains(value.toString());
    }
}