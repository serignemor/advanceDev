package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.providers.TestObjectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RateMapperTest {

    @Autowired
    private RateMapper rateMapper;

    @Test
    void toDto() {
        Rate rate = TestObjectProvider.getRate(1L, RateValue.NEUTRAL);
        RateDto rateDto = rateMapper.toDto(rate);

        assertEquals(rate.getId(), rateDto.getId());
        assertEquals(rate.getRating(), rateDto.getRating());
        assertEquals(
                rate.getCourse().getName(),
                rateDto.getCourse().getName());
        assertEquals(
                rate.getStudent().getName(),
                rateDto.getStudent().getName()
        );
        assertEquals(
                rate.getProfessor().getName(),
                rateDto.getProfessor().getName()
        );
    }


    @Test
    void toEntity() {
        RateDto rateDto = TestObjectProvider.getRateDto( RateValue.NEUTRAL);
        Rate rate = rateMapper.toEntity(rateDto);

        assertEquals(rateDto.getRating(), rate.getRating());
        assertEquals(
                rateDto.getCourse().getName(),
                rate.getCourse().getName());
        assertEquals(
                rateDto.getStudent().getName(),
                rate.getStudent().getName()
        );
        assertEquals(
                rateDto.getProfessor().getName(),
                rate.getProfessor().getName()
        );
    }
}