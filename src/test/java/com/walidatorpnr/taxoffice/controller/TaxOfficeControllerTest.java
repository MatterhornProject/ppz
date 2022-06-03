package com.walidatorpnr.taxoffice.controller;

import com.google.gson.Gson;
import com.walidatorpnr.taxoffice.City;
import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import com.walidatorpnr.taxoffice.Voivodeship;
import com.walidatorpnr.taxoffice.service.CityService;
import com.walidatorpnr.taxoffice.service.TaxOfficeService;
import com.walidatorpnr.taxoffice.service.TaxOffice_DetailService;
import com.walidatorpnr.taxoffice.service.VoivodeshipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaxOfficeController.class)
public class TaxOfficeControllerTest {

    @Autowired
    private TaxOffice taxOffice;

    @Autowired
    private TaxOffice_Detail taxOffice_detail;

    @Autowired
    private Voivodeship voivodeship;

    @Autowired
    private City city;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaxOfficeService taxOfficeService;

    @MockBean
    TaxOffice_DetailService taxOffice_detailService;

    @MockBean
    VoivodeshipService voivodeshipService;

    @MockBean
    CityService cityService;


    @Test
    void getVoivodeshipTest() throws Exception {
        List<Voivodeship> voivodeships = voivodeshipService.findAll();
        mockMvc.perform(get("/taxoffice", voivodeships)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(voivodeships)))
                .andExpect(model().attribute("voivodeships", voivodeships))
                .andExpect(view().name("taxoffice"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());
    }

    @Test
    public void selectVoivodeshipTest() throws Exception { //post

        Integer voivodeship = 10;
        List<Voivodeship> voivodeships = voivodeshipService.findAll();
        List<City> cities = cityService.getAllCitiesByVoivodeship(voivodeship);
        mockMvc.perform(MockMvcRequestBuilders.post("/select_voivodeship")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("voivodeship", 10)
                        .content(new Gson().toJson(voivodeships)))
                .andExpect(model().attribute("voivodeships", voivodeships))
                .andExpect(model().attribute("voivodeship_selected", voivodeship))
                .andExpect(model().attribute("cities", cities))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("/taxoffice"))
                .andExpect(status().isOk());
    }
}
