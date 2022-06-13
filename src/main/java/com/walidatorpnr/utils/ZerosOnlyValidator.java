package com.walidatorpnr.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ZerosOnlyValidator implements ConstraintValidator<ZerosOnly, List<Integer>> {

    private boolean allZeros;

    @Override
    public void initialize(ZerosOnly constraintAnnotation) {
        this.allZeros = constraintAnnotation.allZeros();
    }

    @Override
    public boolean isValid(List<Integer> list, ConstraintValidatorContext context) {
        if (list.stream().allMatch(x->x==0)) {
            return this.allZeros;
        }
        else{
            return !this.allZeros;
        }
    }
}
