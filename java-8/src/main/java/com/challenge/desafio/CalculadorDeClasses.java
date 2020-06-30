package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object o) {
        // Get fields
        return Arrays.stream(o.getClass().getDeclaredFields())
                // Check if the field is a BigDecimal and have Somar annotation
                .filter(field -> fieldIsBigDecimal(field) && hasAnnotationSomar(field))
                // Get field value
                .map(field -> getFieldValue(field, o))
                // Sum all values
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal subtrair(Object o) {
        // Get fields
        return Arrays.stream(o.getClass().getDeclaredFields())
                // Check if the field is a BigDecimal and have Subtrair annotation
                .filter(field -> fieldIsBigDecimal(field) && hasAnnotationSubtrair(field))
                // Get field value
                .map(field -> getFieldValue(field, o))
                // Sum all values
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal totalizar(Object o) {
        return somar(o).subtract(subtrair(o));
    }

    private boolean hasAnnotationSomar(Field field){
        return Arrays.stream(field.getAnnotations()).anyMatch(annotation -> annotation instanceof Somar);
    }

    private boolean hasAnnotationSubtrair(Field field){
        return Arrays.stream(field.getAnnotations()).anyMatch(annotation -> annotation instanceof Subtrair);
    }

    private boolean fieldIsBigDecimal(Field field){
        return field.getType().equals(BigDecimal.class);
    }

    private BigDecimal getFieldValue(Field field, Object o){
        field.setAccessible(true);
        try {
            return new BigDecimal(String.valueOf(field.get(o)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
