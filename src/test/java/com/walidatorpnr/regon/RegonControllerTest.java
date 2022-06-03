package com.walidatorpnr.regon;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegonController.class)
public class RegonControllerTest {

    @Qualifier("Regon14")
    @Autowired
    private Regon regon;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegonService regonService;

    @Test
    void getREGONNumbersTest() throws Exception {

        mockMvc.perform(get("/regon_form", regon)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(regon)))
                .andExpect(model().attribute("numbersList", regon))
                .andExpect(view().name("regon_form"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

    }

    @Test
    public void postRegonNumbersTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/validate_regon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(regon)))
                .andExpect(model().attribute("numbersList", regon))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("/regon_validated"))
                .andExpect(status().isOk());
    }

}
