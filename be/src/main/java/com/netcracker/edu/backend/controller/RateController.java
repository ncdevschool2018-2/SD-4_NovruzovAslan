package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Rate;
import com.netcracker.edu.backend.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Rate> getRateById(@PathVariable(name = "id") Long id) {
        Optional<Rate> rate = rateService.getRateById(id);
        if (rate.isPresent()) {
            return ResponseEntity.ok(rate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Rate saveRate(@RequestBody Rate account) {
        return rateService.saveRate(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRate(@PathVariable(name = "id") Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

}
