package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.RateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class RateServiceTest {


    @MockBean
    private RateRepository rateRepositoryMock;

    @Autowired
    private RateService rateService;


    @Test
    void getAllRates() {
        when(rateRepositoryMock.findAll()).thenReturn(TestObjectProvider.getRateList());

        assertEquals(1, rateService.getAllRates().size());
        assertEquals(1L, rateService.getAllRates().get(0).getId());
    }

    @Test
    void getRateById() {
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));

        Optional<RateDto> optionalRateDto = rateService.getRateById(1L);
        assertTrue(optionalRateDto.isPresent());
        assertEquals(1L, optionalRateDto.get().getId());
    }

    @Test
    void save() {
        when(rateRepositoryMock.save(any(Rate.class))).thenReturn(null);

        rateService.save(TestObjectProvider.getRateDto(RateValue.NEUTRAL));
        verify(rateRepositoryMock).save(any(Rate.class));
    }

    @Test
    void update() {
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));

        Optional<RateDto> optionalRateDto = rateService.update(1L, TestObjectProvider.getRateDto(RateValue.BAD));
        assertTrue(optionalRateDto.isPresent());
        assertEquals(RateValue.BAD, optionalRateDto.get().getRating());
    }

    @Test
    void delete() {
        when(rateRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getRate(1L, RateValue.NEUTRAL)));

        rateService.delete(1L);
        verify(rateRepositoryMock).deleteById(1L);
    }
}