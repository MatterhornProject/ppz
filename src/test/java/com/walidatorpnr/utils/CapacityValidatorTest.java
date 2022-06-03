package com.walidatorpnr.utils;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class CapacityValidatorTest {

    private int min;

    @Test
    void initialize() {
    }

    @Test
    void isValid() {

         List<Integer> list = Lists.newArrayList(1,2,3,null,null);
          min = 5;
          boolean result;

        if(list.size() != this.min){
            System.out.println("List size = " + list.size());
            result = false;
            System.out.println("Result: " +result);
            Assertions.assertFalse(result);
        }
        if(list.stream().noneMatch(Objects::nonNull)){
            System.out.println("NULLS");
            result = false;
            System.out.println("Result: " +result);
            Assertions.assertFalse(result);
        }

        if(list.size() == this.min && list.stream().allMatch(Objects::nonNull)){
            System.out.println("NOT NULLS");
            result = true;
            System.out.println("Result: " +result);
            Assertions.assertTrue(result);

        }
        System.out.println("False..");
       // return false;

    }
}