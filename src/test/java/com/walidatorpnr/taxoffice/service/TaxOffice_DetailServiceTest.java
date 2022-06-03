package com.walidatorpnr.taxoffice.service;

import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class TaxOffice_DetailServiceTest {
    
    @Autowired
    TaxOffice_DetailService taxOffice_detailService;

    @Autowired
    TaxOfficeService taxOfficeService;
    
    @Test
    void findByTaxOfficeID(){
         assertEquals("Poznań", taxOffice_detailService.findAllByID(3026).get(0).getCity());
    }

    @Test
    void taxOffice_detailListWithNamesTest(){
        List<TaxOffice> taxOffices = taxOfficeService.findAllByCity_id(48);
        List<TaxOffice_Detail> taxOffice_details = taxOffice_detailService.findAllByID(3026);
        assertEquals("Urząd Skarbowy Poznań-Wilda", taxOffice_detailService.taxOffice_detailListWithNames(taxOffices, taxOffice_details).get(0).getTaxoffice_name());
    }

    @Test
    void findAllByIDsTest(){
        List<TaxOffice> taxOffices = taxOfficeService.findAllByCity_id(48);
        assertEquals("us.poznan-wilda@mf.gov.pl", taxOffice_detailService.findAllByIDs(taxOffices).get(0).getEmail());
    }
}
