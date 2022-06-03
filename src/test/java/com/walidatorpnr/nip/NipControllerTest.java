package com.walidatorpnr.nip;


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

@WebMvcTest(NipController.class)
public class NipControllerTest {



   private Nip nip;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NipService nipService;


    @Autowired
    public NipControllerTest(Nip nip) {
        this.nip = nip;
    }



    @Test
    void getNIPNumbersTest() throws Exception {

        mockMvc.perform(get("/nip_form", nip)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(nip)))
                .andExpect(model().attribute("numbersList", nip))
                .andExpect(view().name("nip_form"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

    }

    @Test
    public void postPeselNumbersTest() throws Exception {

        List<Integer> numberslist = (Lists.newArrayList(9,3,5,5,6,3,9,4,5,6));
        nip.setNumbersList(numberslist);


        mockMvc.perform(MockMvcRequestBuilders.post("/validate_nip")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(nip)))
                .andExpect(model().attribute("numbersList", nip))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("/nip_validated"))
                .andExpect(status().isOk());
    }
}
