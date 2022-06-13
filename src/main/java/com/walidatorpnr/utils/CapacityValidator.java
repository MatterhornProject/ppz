package com.walidatorpnr.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CapacityValidator implements ConstraintValidator<Capacity, List<Integer>> {

    private int min;
   // private int max;
  private boolean nullable;

    @Override
    public void initialize(Capacity constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.nullable = constraintAnnotation.nullable();
        System.out.println("Init");
       // this.max = constraintAnnotation.max();
    }


    @Override
    public boolean isValid(List<Integer> list, ConstraintValidatorContext constraintValidatorContext) {

        //int counter = 0;

           if(list.size() != this.min){
               System.out.println("List size = " + list.size());
               return false;
           }
           if(list.stream().noneMatch(Objects::nonNull)){ //jeśli wszytstkie pola są puste
               System.out.println("NULLS");
               return nullable;
           }

               if(list.size() == this.min && list.stream().allMatch(Objects::nonNull)){
                   System.out.println("NOT NULLS");
                   return true;

               }
               System.out.println("False");
        return false;

    }


}
