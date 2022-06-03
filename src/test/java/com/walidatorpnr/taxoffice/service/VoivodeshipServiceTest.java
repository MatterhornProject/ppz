package com.walidatorpnr.taxoffice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class VoivodeshipServiceTest {

    @Autowired
    VoivodeshipService voivodeshipService;

    @Test
    void testFetchAll(){
        assertEquals(5, voivodeshipService.findAll().size());
    }
}
