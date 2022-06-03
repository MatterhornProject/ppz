package com.walidatorpnr.nip;

import com.google.common.collect.Lists;
import com.walidatorpnr.taxoffice.service.TaxOfficeService;
import com.walidatorpnr.utils.NumbersStringBuilder;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class NipServiceTest {

    @Autowired
    NumbersStringBuilder numbersStringBuilder;

    @Autowired
    SumOfProductCounter sumOfProductCounter;


    @Autowired
    Nip nip;

    @Qualifier("Nip0")
    @Autowired
    Nip nip0;

    private NipService nipService;

    @Autowired
    TaxOfficeService taxOfficeService;

    @BeforeEach
    void init() {
        this.nipService =  new NipService(numbersStringBuilder, sumOfProductCounter, nip, taxOfficeService);
        nip.setNumbersList(Lists.newArrayList(8,3,2,1,0,0,3,0,3,5));
    }


    @Test
    void ReadControlNumberTest(){
        int result = nipService.readControlNumber(nip);
        Assertions.assertEquals(5, result);
    }
    @Test
    void ReadTaxOfficeIDTest(){
        Nip result = nipService.readTaxOfficeID(nip);
        Assertions.assertEquals("832", result.getTax_office_id());
    }
    @Test
    void readTaxOfficeName(){
        Assertions.assertEquals("Urząd Skarbowy w Wieluniu", nipService.readTaxOfficeName(nip).getTax_office_name() );
    }
    @Test
    void GetSumOfProductsTest(){
        int result = nipService.GetSumOfProducts(nip);
        Assertions.assertEquals(115, result);
    }
    @Test
    void ValidateTrueTest(){ //jeśi nip jest poprawny
        Nip result = nipService.Validate(nip);
        Assertions.assertEquals("poprawny", result.getResult());
    }

    @Test
    void ValidateFalseTest(){ //jeśli nip jest błędny
        nip.setNumbersList(Lists.newArrayList(1,2,3,4,5,6,7,8,9,0));
        Nip result = nipService.Validate(nip);
        Assertions.assertEquals("błędny!", result.getResult());
    }

    @Test
    void Validate0Test(){ //jeśli nip zawiera same 0
        Nip result = nipService.Validate(nip0);
        Assertions.assertEquals("poprawny", result.getResult());
    }
}
