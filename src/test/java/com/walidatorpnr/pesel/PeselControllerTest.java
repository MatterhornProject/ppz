package com.walidatorpnr.pesel;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
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

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(PeselController.class)
public class PeselControllerTest {



    //@Autowired
    Pesel pesel;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PeselService peselService;
    @MockBean
    PeselValidationService peselValidationService;


    @Autowired
    public PeselControllerTest(Pesel pesel) {
        this.pesel = pesel;
    }

   @Test
    void getPeselNumbersTest() throws Exception {

       mockMvc.perform(get("/pesel_form", pesel)
                                .accept(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(pesel)))
               .andExpect(model().attribute("numbersList", pesel))
                .andExpect(view().name("pesel_form"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

    }

    @Test
    public void postPeselNumbersTest() throws Exception {


       List <Integer> numberslist = (Lists.newArrayList(2,2,2,2,2,2,2,2,2,2,2));
       pesel.setNumbersList(numberslist);

        mockMvc.perform(MockMvcRequestBuilders.post("/validate_pesel")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(pesel)))
                .andExpect(model().attribute("numbersList", pesel))
                .andExpect(model().hasNoErrors())
               .andExpect(view().name("/pesel_validated"))
               .andExpect(status().isOk());
    }
}
