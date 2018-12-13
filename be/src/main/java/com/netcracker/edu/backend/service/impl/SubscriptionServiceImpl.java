package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subscr;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository repository;
//    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Subscription> getSubscriptionsByUserId(Long id) {
        return repository.findSubscriptionsByProductWalletUserId(id);
    }

    @Override
    public Page<Subscription> getProductsByUserId(Integer page, Long userId) {
        Pageable pageable = new PageRequest(page-1, 6, new Sort(Sort.Direction.ASC, "id"));
        User user = userService.getUserById(userId).get();
        return repository.findSubscriptionsByUserWalletUserId(pageable, userId);
    }


    @Override
    public Iterable<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public Double calculatePrice(Subscription subscription) {
        // todo: convertation from one valute to another
        Double fullPrice = subscription.getProduct().getCost();
        // todo: make discount depending from
        Double pricePerDay = fullPrice/30;
        return pricePerDay;
    }

    @Override
    public void unsubscribe(Long prodId, Long userId) {
        Iterable<Subscription> subscriptions = repository.findSubscriptionsByProductWalletUserId(userId);
        for(Subscription subscription: subscriptions) {
            if(subscription.getProduct().getId().equals(prodId))
                repository.deleteById(subscription.getId());
        }
    }

    @Override
    public void deleteSubscription(Long id) {
        repository.deleteById(id);
    }
}
