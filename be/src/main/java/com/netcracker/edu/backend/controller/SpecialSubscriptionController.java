package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.SpecialSubscription;
//import com.netcracker.edu.backend.scheduler.ScheduleTask;
//import com.netcracker.edu.backend.scheduler.QrtzScheduler;
import com.netcracker.edu.backend.service.SpecialSubscriptionService;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/special-subscriptions")
public class SpecialSubscriptionController {

    private SpecialSubscriptionService specialSubscriptionService;

    @Autowired
    public SpecialSubscriptionController(SpecialSubscriptionService specialSubscriptionService) {
        this.specialSubscriptionService = specialSubscriptionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialSubscription> getSpecialSubscriptionById(@PathVariable(name = "id") Long id) {
        Optional<SpecialSubscription> specialSubscription = specialSubscriptionService.getSpecialSubscriptionById(id);
        if (specialSubscription.isPresent()) {
            return ResponseEntity.ok(specialSubscription.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<SpecialSubscription> getAllSpecialSubscriptions(
            @RequestParam(name = "user_id") Long userId
    ) {
        return specialSubscriptionService.getAllSpecialSubscriptions(userId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Iterable<SpecialSubscription> getProductsByUserId(
            @RequestParam(name = "page") Integer pageNumber,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id") Long userId) {
        Page page = specialSubscriptionService.getSpecialSubscriptionsPageByUserId(pageNumber, size, userId);
        return page.getContent();
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public Integer getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id") Long user_id) {
        Page page = specialSubscriptionService.getSpecialSubscriptionsPageByUserId(1, size,user_id);
        return page.getTotalPages();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public SpecialSubscription saveSpecialSubscription(@RequestBody SpecialSubscription account) {
        return specialSubscriptionService.saveSpecialSubscription(account);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity unsubscribe(@RequestParam(name = "product_id") Long prodId, @RequestParam(name = "user_id") Long userId) {
        specialSubscriptionService.unsubscribe(prodId, userId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSpecialSubscription(@PathVariable(name = "id") Long id) {
        specialSubscriptionService.deleteSpecialSubscription(id);
        return ResponseEntity.noContent().build();
    }

}
