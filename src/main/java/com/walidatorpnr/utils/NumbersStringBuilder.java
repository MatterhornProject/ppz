package com.walidatorpnr.utils;

import java.util.List;

public class NumbersStringBuilder {

    public String Build(int start, int stop, List<Integer> list){
        StringBuilder builder = new StringBuilder();
        for(int i = start; i<stop; i++){
            builder.append(String.valueOf(list.get(i)));
        }
        return builder.toString();
    }
}
