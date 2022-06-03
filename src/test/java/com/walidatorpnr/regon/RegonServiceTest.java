package com.walidatorpnr.regon;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.NumbersStringBuilder;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegonServiceTest {

    @Qualifier("Regon14")
    @Autowired
    Regon regon14;

    @Qualifier("Regon9")
    @Autowired
    Regon regon9;

    @Autowired
    NumbersStringBuilder numbersStringBuilder;

    @Autowired
    SumOfProductCounter sumOfProductCounter;

    RegonService regonService;

    @BeforeEach
    public void init() {
        this.regonService = new RegonService(regon9, regon14, numbersStringBuilder, sumOfProductCounter);
        this.regon14.setNumbersList9(Lists.newArrayList(2,3,5,1,1,3,3,2,8));
        this.regon14.setNumbersList14(Lists.newArrayList(5,7,1,8,8));
        this.regon9.setNumbersList9(Lists.newArrayList(7,3,2,0,6,5,8,1,4));
        this.regon9.setNumbersList14(Lists.newArrayList(null,null,null,null,null));
    }

    @Test
      void MergeRegonNumbers14Test(){
           Regon result = regonService.MergeRegonNumbers(regon14);
           List<Integer> list = Lists.newArrayList(2,3,5,1,1,3,3,2,8,5,7,1,8,8);
           Assertions.assertEquals(list, result.getNumbersList());
       }

    @Test
    void MergeRegonNumbers9Test(){
        Regon result = regonService.MergeRegonNumbers(regon9);
        List<Integer> list = Lists.newArrayList(7,3,2,0,6,5,8,1,4);
        Assertions.assertEquals(list, result.getNumbersList());
    }
   @Test
    void readVovoivodeshipNumberTest(){
        String result = regonService.readVovoivodeshipNumber(regon9);
        Assertions.assertEquals("73", result);
   }
   @Test
    void readSerialNumberTest(){
        String result = regonService.readSerialNumber(regon9);
        Assertions.assertEquals("206581", result);
   }
   @Test
    void readLegalEntityIDTest(){
        String result = regonService.readLegalEntityID(regon14);
        Assertions.assertEquals("235113328", result);
   }
   @Test
    void readLocalUnitOrdinalNumberTet(){
        String result = regonService.readLocalUnitOrdinalNumber(regon14);
        Assertions.assertEquals("5718", result);
   }
   @Test
    void readControlNumber9Test(){
        int result = regonService.readControlNumber(regon9,8);
        Assertions.assertEquals(4, result);
    }
    @Test
    void readControlNumber14Test(){
        int result = regonService.readControlNumber(regon14,13);
        Assertions.assertEquals(8, result);
    }
    @Test
    void getSumOfProducts9Test(){
        List <Integer>numbers = Lists.newArrayList(7,3,2,0,6,5,8,1,4);
        regon9.setNumbersList(numbers);
        int result = regonService.GetSumOfProducts(regon9);
        Assertions.assertEquals(191, result);
    }
    @Test
    void getSumOfProducts14Test(){
        List <Integer>numbers = Lists.newArrayList(2,3,5,1,1,3,3,2,8,5,7,1,8,8);
        regon14.setNumbersList(numbers);
        int result = regonService.GetSumOfProducts(regon14);
        Assertions.assertEquals(250, result);
    }
    @Test
    void getValidationResult9TrueTest(){
        List <Integer>numbers = Lists.newArrayList(7,3,2,0,6,5,8,1,4);
        regon9.setNumbersList(numbers);
        String result = regonService.GetValidationResult(regon9, 8);
        Assertions.assertEquals("poprawny",result);
    }
    @Test
    void getValidationResult9FalseTest(){
        List<Integer> numbersFalse = Lists.newArrayList(7,3,2,0,6,5,8,1,5);
        regon9.setNumbersList(numbersFalse);
        String result = regonService.GetValidationResult(regon9, 8);
        Assertions.assertEquals("błędny!",result);
    }
    @Test
    void getValidationResult14TrueTest(){
        List <Integer>numbers = Lists.newArrayList(2,3,5,1,1,3,3,2,8,5,7,1,8,8);
        regon14.setNumbersList(numbers);
        String result = regonService.GetValidationResult(regon14, 13);
        Assertions.assertEquals("poprawny",result);
    }
    @Test
    void getValidationResult14FalseTest(){
        List<Integer> numbersFalse = Lists.newArrayList(2,3,5,1,1,3,3,2,8,5,7,1,8,9);
        regon14.setNumbersList(numbersFalse);
        String result = regonService.GetValidationResult(regon14, 13);
        Assertions.assertEquals("błędny!",result);
    }
    @Test
    void Validate9True(){
        Regon result = regonService.Validate(regon9);
        Assertions.assertEquals("206581", result.getSerial_number());
        Assertions.assertEquals("73", result.getVoivodeship_code());
        Assertions.assertEquals("poprawny", result.getResult());
    }
    @Test
    void Validate9False(){
        regon9.setNumbersList9(Lists.newArrayList(7,3,2,0,6,5,8,1,5));
        Regon result = regonService.Validate(regon9);
        Assertions.assertEquals("206581", result.getSerial_number());
        Assertions.assertEquals("73", result.getVoivodeship_code());
        Assertions.assertEquals("błędny!", result.getResult());
    }
    @Test
    void Validate14True(){
        Regon result = regonService.Validate(regon14);
        Assertions.assertEquals("235113328", result.getLegal_entity_id());
        Assertions.assertEquals("5718", result.getLocal_unit_ordinal_number());
        Assertions.assertEquals("poprawny", result.getResult());
    }
    @Test
    void Validate14False(){
        regon14.setNumbersList14(Lists.newArrayList(5,7,1,8,9));
        Regon result = regonService.Validate(regon14);
        Assertions.assertEquals("235113328", result.getLegal_entity_id());
        Assertions.assertEquals("5718", result.getLocal_unit_ordinal_number());
        Assertions.assertEquals("błędny!", result.getResult());
    }
}
