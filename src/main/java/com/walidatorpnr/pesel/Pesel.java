package com.walidatorpnr.pesel;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.ZerosOnly;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Data
@Component
public class Pesel { //11 cyfr

    @ZerosOnly(allZeros = false)//lista nie może zawierać samych zer
    private List<@NotNull(message = "Pole nie może być puste!") @PositiveOrZero(message = "Podana wartość musi być cyfą 0-9") Integer> numbersList;
    private int[] weights;
    private String result;
    private int control_number;
    private String serial_number;
    private String sex;
    private int year;
    private int month;
    private int day;
    private String birthday;


    @Autowired
   public Pesel()

    {
        numbersList = Lists.newArrayList(2,2,2,2,2,2,2,2,2,2,2);
      weights = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
   }

    public Pesel(List<@NotNull(message = "Pole nie może być puste!") @PositiveOrZero(message = "Podana wartość musi być cyfą 0-9") Integer> numbersList) {
        this.numbersList = numbersList;
        weights = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    }

}
