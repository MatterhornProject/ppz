package com.walidatorpnr.taxoffice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class TaxOfficeServiceTest {

    @Autowired
    TaxOfficeService taxOfficeService;


    @Test
    void  findAllByCity_idTest(){
        assertEquals(1, taxOfficeService.findAllByCity_id(48).size());
        assertEquals( "Urząd Skarbowy Poznań-Wilda", taxOfficeService.findAllByCity_id(48).get(0).getName());
        assertEquals( "Poznań-Wilda", taxOfficeService.findAllByCity_id(48).get(0).getCity().getName());
    }
}
