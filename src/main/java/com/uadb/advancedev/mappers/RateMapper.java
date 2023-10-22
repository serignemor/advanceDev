package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.entities.Rate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RateMapper {

    private final ModelMapper modelMapper;


    public RateDto toDto(Rate rate) {
        return modelMapper.map(rate, RateDto.class);
    }

    public List<RateDto> toDto(List<Rate> rateList){
        return rateList.stream()
                .map(this::toDto)
                .toList();
    }


    public Rate toEntity(RateDto rateDto) {
        return modelMapper.map(rateDto, Rate.class);
    }

    public List<Rate> toEntity(List<RateDto> rateDtoList){
        return rateDtoList.stream()
                .map(this::toEntity)
                .toList();
    }


    public void update(RateDto rateDto, Rate rate) {
        modelMapper.map(rateDto, rate);
    }
}
