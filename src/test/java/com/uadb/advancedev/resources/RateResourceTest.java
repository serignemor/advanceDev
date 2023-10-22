package com.uadb.advancedev.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.RateRepository;
import com.uadb.advancedev.services.RateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RateResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RateService rateService;

    @MockBean
    private RateRepository rateRepositoryMock;


    @Test
    void getAllRates() throws Exception {
        mockMvc.perform(get("/rates")
        ).andExpect(status().isOk());
    }

    @Test
    void getRateById() throws Exception {
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));

        mockMvc.perform(get("/rates/1")
        ).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        when(rateRepositoryMock.save(any(Rate.class))).thenReturn(null);

        mockMvc.perform(post("/rates")
                .contentType("application/json")
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getRateDto(RateValue.NEUTRAL)
                        )
                )
        ).andExpect(status().isCreated());

    }

    @Test
    void update() throws Exception{
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));
        when(rateRepositoryMock.save(any(Rate.class))).thenReturn(null);

            mockMvc.perform(put("/rates/1")
                    .contentType("application/json")
                    .content(
                            objectMapper.writeValueAsString(
                                    TestObjectProvider.getRateDto(RateValue.NEUTRAL)
                            )
                    )
            ).andExpect(status().isOk());

    }

    @Test
    void delete() throws Exception {
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/rates/1"))
                .andExpect(status().isNoContent());
    }
}