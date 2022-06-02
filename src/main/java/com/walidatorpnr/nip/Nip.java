package com.walidatorpnr.nip;

import com.google.common.collect.Lists;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@Component
public class Nip { //10 cyfr

   private List<@NotNull(message = "Pole nie może być puste!") @PositiveOrZero(message = "Podana wartość musi być cyfą 0-9") Integer> numbersList;
    private int[] weights;
    private String result;
    private int control_number;
    private String tax_office_id;
    private String tax_office_name;
    private List<TaxOffice_Detail>tax_office_details;

    @Autowired
    public Nip()
    {
        numbersList = Lists.newArrayList(9,3,5,5,6,3,9,4,5,6);
        weights = new int[]{6, 5, 7, 2, 3, 4, 5, 6, 7};
    }

}
