package com.walidatorpnr.pesel;

import com.walidatorpnr.utils.SumOfProductCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeselValidationService {

    private final SumOfProductCounter sumOfProductCounter;

    private Pesel pesel;

    @Autowired
    public PeselValidationService(SumOfProductCounter sumOfProductCounter, Pesel pesel) {
        this.sumOfProductCounter = sumOfProductCounter;
        this.pesel = pesel;
    }

    public int GetSumOfProducts(Pesel pesel){ //cyfry 1-10 * wagi
        return sumOfProductCounter.Count(pesel.getNumbersList(), pesel.getWeights());
    }
    public int readControlNumber(Pesel pesel){ //ostatnia cyfra numeru pesel to cyfra kontrolna
        return pesel.getNumbersList().get(10);
    }
    public Pesel Validate(Pesel pesel){

       int sum = GetSumOfProducts(pesel);
       int m = sum%10; //wartość m = suma iloczynów modulo 10;
        int ck = readControlNumber(pesel); //cyfra kontrolna

        if(m == 0 && m==ck){//jeśli wartość m = 0 i cyfra kontrolna = 0, pesel jest poprawny
            pesel.setResult("poprawny");
            return pesel;
        }
        if(10-m == ck){//jeśli wartość 10-m = cyfra kontrolna, pesel jest poprawny
            pesel.setResult("poprawny");
            return pesel;
        }
        if(10-m != ck){//jeśli wartość 10-m jest różna od cyfry kontrolnej, pesel jest błędny
            pesel.setResult("błędny!");
            return pesel;
        }

        pesel.setResult("błędny!");
        return pesel;
    }

  /*  public String ResultText(Pesel pesel){
        Validate(pesel);
        return pesel.getResult();
    }*/

}
