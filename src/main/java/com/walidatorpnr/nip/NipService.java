package com.walidatorpnr.nip;

import com.walidatorpnr.taxoffice.service.TaxOfficeService;
import com.walidatorpnr.utils.NumbersStringBuilder;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NipService {

    private final NumbersStringBuilder numbersStringBuilder;
    private final SumOfProductCounter sumOfProductCounter;


    private TaxOfficeService taxOfficeService;

    private Nip nip;

    @Autowired
    public NipService(NumbersStringBuilder numbersStringBuilder, SumOfProductCounter sumOfProductCounter, Nip nip, TaxOfficeService taxOfficeService) {
        this.numbersStringBuilder = numbersStringBuilder;
        this.sumOfProductCounter = sumOfProductCounter;
        this.nip = nip;
        this.taxOfficeService = taxOfficeService;
    }


    public int readControlNumber(Nip nip){ //10 cyfra to cyfra kontrolna
        nip.setControl_number(nip.getNumbersList().get(9));
        return nip.getControl_number();
    }
    public Nip readTaxOfficeID(Nip nip){ //pierwsze 3 cyfry, to kod urzędu skarbowego, który nadał dany nip
        nip.setTax_office_id(numbersStringBuilder.Build(0,3, nip.getNumbersList()));
        return nip;
    }
    public Nip readTaxOfficeName(Nip nip){
        nip.setTax_office_name(taxOfficeService.findByID(Integer.parseInt(nip.getTax_office_id())).getName());
        return nip;
    }
    public int GetSumOfProducts(Nip nip){ //cyfry 1-9 * wagi
        return sumOfProductCounter.Count(nip.getNumbersList(), nip.getWeights());
    }
    public Nip Validate(Nip nip){

        int sum = GetSumOfProducts(nip); //suma iloczynów
        int m = sum%11; //modulo 11
        int ck = readControlNumber(nip); //cyfra kontrolna

        if (m!=ck || m==10){
            nip.setResult("błędny!");
            return  nip;
        }
        if (m==ck){
            nip.setResult("poprawny");
            return nip;
        }
        return nip;
    }
}
