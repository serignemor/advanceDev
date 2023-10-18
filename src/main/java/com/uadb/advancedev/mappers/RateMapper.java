package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.entities.Rate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateMapper {
    private final ModelMapper modelMapper;

    public RateDto toDto(Rate rate) {
        return modelMapper.map(rate, RateDto.class);
    }

    public Rate toEntity(RateDto rateDto) {
        return modelMapper.map(rateDto, Rate.class);
    }
}
