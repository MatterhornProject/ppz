package com.walidatorpnr.regon;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.Capacity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Data
@Component
public class Regon { //9 lub 14  cyfr

    @Capacity(min=9, nullable = false, message = "Żeby sprawdzić 9-cyfrowy numer REGON, wszytskie pola od 1-9 muszą być wypelnione a wszystkie pola od 10-14 puste!")
    private List<@PositiveOrZero(message = "Podana wartość musi być cyfą 0-9") Integer> numbersList9;

    @Capacity(min=5, nullable = true)
    private List<Integer> numbersList14;

    private List<Integer> numbersList;
    private int[] weights;
    private String result;
    private int control_number;
    private String voivodeship_code;
    private String serial_number;
    private String legal_entity_id;
    private String local_unit_ordinal_number;

    @Autowired
    public Regon(){
        numbersList9 = Lists.newArrayList(1,2,3,4,5,6,7,8,5);
        numbersList14 = Lists.newArrayList(1, 2, 3, 4, 7);
        weights=new int[]{2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8};
    }

    public Regon(List<Integer> numbersList9, List<Integer> numbersList14)
    {
        this.numbersList9 = numbersList9;
        this.numbersList14 = numbersList14;
    }

    public Regon(List<Integer> numbersList9, List<Integer> numbersList14, int[] weights)
    {
        this.numbersList9 = numbersList9;
        this.numbersList14 = numbersList14;
        this.weights = weights;
    }
}
