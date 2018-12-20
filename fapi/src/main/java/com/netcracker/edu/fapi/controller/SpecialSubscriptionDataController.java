package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.SpecialSubscriptionViewModel;
import com.netcracker.edu.fapi.service.SpecialSubscriptionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-subscriptions")
public class SpecialSubscriptionDataController {

    @Autowired
    private SpecialSubscriptionDataService specialSubscriptionDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SpecialSubscriptionViewModel>> getAllSpecialSubscriptions(@RequestParam(name = "user_id") Long userId) {
        return ResponseEntity.ok(specialSubscriptionDataService.getAll(userId));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<SpecialSubscriptionViewModel>> getProductsByUserId(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id") Long userId) {
        return ResponseEntity.ok(specialSubscriptionDataService.getSpecialSubscriptionsPageByUserId(page, size,userId));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id") Long user_id) {
        return ResponseEntity.ok(specialSubscriptionDataService.getTotalPages(size, user_id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialSubscriptionViewModel> getSpecialSubscriptionById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(specialSubscriptionDataService.getSpecialSubscriptionById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpecialSubscriptionViewModel> saveSpecialSubscription(@RequestBody SpecialSubscriptionViewModel specialSubscription /*todo server validation*/) {
        if (specialSubscription != null) {
            return ResponseEntity.ok(specialSubscriptionDataService.saveSpecialSubscription(specialSubscription));
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void unsubscribe(@RequestParam(name = "product_id") String prodId, @RequestParam(name = "user_id") String userId) {
        specialSubscriptionDataService.unsubscribe(prodId, userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSpecialSubscription(@PathVariable String id) {
        specialSubscriptionDataService.deleteSpecialSubscription(Long.valueOf(id));
    }

}

