package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.RateViewModel;
import com.netcracker.edu.fapi.service.RateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateDataController {

    @Autowired
    private RateDataService rateDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<RateViewModel>> getAllRates() {
        return ResponseEntity.ok(rateDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RateViewModel> getRateById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(rateDataService.getRateById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RateViewModel> saveRate(@RequestBody RateViewModel rate /*todo server validation*/) {
        if (rate != null) {
            return ResponseEntity.ok(rateDataService.saveRate(rate));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRate(@PathVariable String id) {
        rateDataService.deleteRate(Long.valueOf(id));
    }

}

