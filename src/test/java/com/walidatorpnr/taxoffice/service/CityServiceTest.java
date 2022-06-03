package com.walidatorpnr.taxoffice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class CityServiceTest {


    @Autowired
    CityService cityService;

    @Test
    public void findAllByVoivodeshipIDTest() {
        assertEquals(2, cityService.getAllCitiesByVoivodeship(30).size());

    }
    @Test
    void FindAllTest(){
        assertEquals(8, cityService.findAll().size());
    }
}