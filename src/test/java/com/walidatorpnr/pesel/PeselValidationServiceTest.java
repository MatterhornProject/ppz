package com.walidatorpnr.pesel;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class PeselValidationServiceTest {

    @Autowired
    private SumOfProductCounter sumOfProductCounter;

   private PeselValidationService peselValidationService;

   @Autowired
    private Pesel pesel;

    @BeforeEach
    void initializeService(){
        this.peselValidationService = new PeselValidationService(sumOfProductCounter, pesel);
    }

   @BeforeEach
   void InitPesel(){
        List<Integer> numbersList = Lists.newArrayList(8, 8, 1, 0, 1, 1, 0, 3, 9, 6, 3);
        pesel.setNumbersList(numbersList);
        pesel.setControl_number(numbersList.get(10));
    }

    @Test
void GetSumOfProductsTest(){

    int result = peselValidationService.GetSumOfProducts(pesel);
        Assertions.assertEquals(97, result);
}
@Test
void ReadControlNumberTest(){
        int result = peselValidationService.readControlNumber(pesel);
        Assertions.assertEquals(3, result);
    }
@Test
void ValidateTestTrue(){
        Pesel result = peselValidationService.Validate(pesel);
    Assertions.assertEquals("poprawny", result.getResult());
}

@Test
    void ValidateTestFalse(){
      List <Integer> numbersList = Lists.newArrayList(8, 8, 1, 0, 1, 1, 0, 3, 9, 6, 5);
        pesel.setNumbersList(numbersList);
        pesel.setControl_number(numbersList.get(10));
        Pesel result = peselValidationService.Validate(pesel);
        Assertions.assertEquals("błędny!", result.getResult());
}

    @Test
    void ValidateTestFalseNegativeNumber(){
        List <Integer> numbersList = Lists.newArrayList(8, 8, 1, 0, 1, 1, 0, -3, 9, 6, -5);
        pesel.setNumbersList(numbersList);
        pesel.setControl_number(numbersList.get(10));
        Pesel result = peselValidationService.Validate(pesel);
        Assertions.assertEquals("błędny!", result.getResult());
    }

/*@Test
void ResultTextTestTrue(){

       String result = peselValidationService.ResultText(pesel);
       Assertions.assertEquals("poprawny", result);
}

@Test
    void ResultTextTestFalse(){
    List <Integer> numbersList = Lists.newArrayList(8, 8, 1, 0, 1, 1, 0, 3, 9, 6, 5);
    pesel.setNumbersList(numbersList);
    pesel.setControl_number(numbersList.get(10));
    String result = peselValidationService.ResultText(pesel);
    Assertions.assertEquals("błędny!", result);
}*/


}
