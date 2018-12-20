package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subscr;
import com.netcracker.edu.backend.entity.SpecialSubscription;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.SpecialSubscriptionRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpecialSubscriptionServiceImpl implements SpecialSubscriptionService {

    private SpecialSubscriptionRepository repository;
//    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public SpecialSubscriptionServiceImpl(SpecialSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpecialSubscription saveSpecialSubscription(SpecialSubscription specialSubscription) {
        return repository.save(specialSubscription);
    }

    @Override
    public Optional<SpecialSubscription> getSpecialSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<SpecialSubscription> getSpecialSubscriptionsByUserId(Long id) {
        return null;// repository.findSpecialSubscriptionsBySpecialProductProductWalletUserId(id);
    }

    @Override
    public Page<SpecialSubscription> getSpecialSubscriptionsPageByUserId(Integer page, Integer size, Long userId) {
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        User user = userService.getUserById(userId).get();
        return null;// repository.findSpecialSubscriptionsByUserWalletUserId(pageable, userId);
    }


    @Override
    public Iterable<SpecialSubscription> getAllSpecialSubscriptions(Long userId) {
        if(userId == null)
            return repository.findAll();
        return null;// repository.findSpecialSubscriptionsBySpecialProductProductWalletUserId(userId);
    }

    @Override
    public Double calculatePrice(SpecialSubscription specialSubscription) {
        // todo: convertation from one valute to another
        Double fullPrice = specialSubscription.getProduct().getCost();
        // todo: make discount depending from
        Double pricePerDay = fullPrice/30;
        return pricePerDay;
    }

    @Override
    public void unsubscribe(Long prodId, Long userId) {
        return;
//        Iterable<SpecialSubscription> specialSubscriptions = repository.findSpecialSubscriptionsBySpecialProductProductWalletUserId(userId);
//        for(SpecialSubscription specialSubscription: specialSubscriptions) {
//            if(specialSubscription.getProduct().getId().equals(prodId))
//                repository.deleteById(specialSubscription.getId());
//        }
    }

    @Override
    public void deleteSpecialSubscription(Long id) {
        repository.deleteById(id);
    }
}
