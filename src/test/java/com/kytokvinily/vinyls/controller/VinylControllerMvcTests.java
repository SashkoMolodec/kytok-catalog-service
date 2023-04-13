package com.kytokvinily.vinyls.controller;

import com.kytokvinily.vinyls.domain.VinylNotFoundException;
import com.kytokvinily.vinyls.domain.VinylService;
import com.kytokvinily.vinyls.web.VinylController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(VinylController.class)
public class VinylControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VinylService vinylService;

    @Test
    void getNewVinylThenReturn404() throws Exception{
        String badTitle = "badTitle";
        int year = 1989;
        given(vinylService.getByTitleAndYear(badTitle, year))
                .willThrow(VinylNotFoundException.class);
        mockMvc.perform(get("/vinyls/" + badTitle + "/" + year))
                .andExpect(status().isNotFound());
    }
}
