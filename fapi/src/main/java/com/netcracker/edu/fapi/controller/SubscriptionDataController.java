package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.SubscriptionViewModel;
import com.netcracker.edu.fapi.service.SubscriptionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionDataController {

    @Autowired
    private SubscriptionDataService subscriptionDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SubscriptionViewModel>> getAllSubscriptions(
            @RequestParam(name = "user_id") Long userId) {
        return ResponseEntity.ok(subscriptionDataService.getAll(userId));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<SubscriptionViewModel>> getProductsByUserId(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long userId) {
        return ResponseEntity.ok(subscriptionDataService.getSubscriptionsPageByUserId(page, size,userId));
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionViewModel> getProductByUserId(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "product_id") Long productId) {
        return ResponseEntity.ok(subscriptionDataService.getSubscriptionByUserAndProductId(userId, productId));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long user_id) {
        return ResponseEntity.ok(subscriptionDataService.getTotalPages(size, user_id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionViewModel> getSubscriptionById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(subscriptionDataService.getSubscriptionById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SubscriptionViewModel> saveSubscription(@RequestBody SubscriptionViewModel subscription /*todo server validation*/) {
        if (subscription != null) {
            return ResponseEntity.ok(subscriptionDataService.saveSubscription(subscription));
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void unsubscribe(@RequestParam(name = "product_id") String prodId, @RequestParam(name = "user_id") String userId) {
        subscriptionDataService.unsubscribe(prodId, userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteSubscription(@PathVariable String id) {
        subscriptionDataService.deleteSubscription(Long.valueOf(id));
    }

}

