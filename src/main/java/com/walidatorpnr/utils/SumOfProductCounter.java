package com.walidatorpnr.utils;

import java.util.List;

public class SumOfProductCounter {

    public int Count(List<Integer> list, int[] weights){

        int sum = 0;
        for(int i = 0; i<list.size()-1; i++){
            int product = list.get(i) * weights[i];
            sum += product; //suma iloczynów
        }
        return sum;
    }
}
