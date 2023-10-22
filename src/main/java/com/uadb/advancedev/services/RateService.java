package com.uadb.advancedev.services;


import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.mappers.RateMapper;
import com.uadb.advancedev.repositories.RateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final RateMapper rateMapper;


    public List<RateDto> getAllRates() {
        return rateMapper.toDto(rateRepository.findAll());
    }


    public Optional<RateDto> getRateById(long idRate) {
        Optional<Rate> optionalRate = rateRepository.findById(idRate);
        if (optionalRate.isPresent()) {
            Rate rate = optionalRate.get();
            return of(rateMapper.toDto(rate));
        }

        return Optional.empty();
    }


    public void save(RateDto rateDto) {
        rateRepository.save(rateMapper.toEntity(rateDto));
    }


    public Optional<RateDto> update(long idRate, RateDto rateDto) {
        Optional<Rate> optionalRate = rateRepository.findById(idRate);
        if (optionalRate.isPresent()) {
            Rate rate = optionalRate.get();
            rateMapper.update(rateDto, rate);
            rateRepository.save(rate);
            return of(rateDto);
        }

        return Optional.empty();
    }


    public void delete(long idRate) {
        rateRepository.deleteById(idRate);
    }
}
