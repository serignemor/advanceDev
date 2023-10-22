package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.RateDto;
import com.uadb.advancedev.services.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class RateResource {

    private final RateService rateService;


    @GetMapping("/rates")
    public ResponseEntity<List<RateDto>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }


    @GetMapping("/rates/{rateId}")
    public ResponseEntity<RateDto> getRateById(@PathVariable long rateId) {
        Optional<RateDto> rateDto = rateService.getRateById(rateId);
        return rateDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/rates")
    public ResponseEntity<?> save(@RequestBody RateDto rateDto) {
        rateService.save(rateDto);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/rates/{rateId}")
    public ResponseEntity<?> update(@PathVariable long rateId,
                                    @RequestBody RateDto rateDto) {
        Optional<RateDto> optionalRate = rateService.update(rateId, rateDto);
        return optionalRate
                .map(rate -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/rates/{rateId}")
    public ResponseEntity<?> delete(@PathVariable long rateId) {
        rateService.delete(rateId);
        return ResponseEntity.status(204).build();
    }
}
