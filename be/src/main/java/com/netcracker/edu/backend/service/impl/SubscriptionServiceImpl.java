package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository repository;
//    private UserRepository userRepository;

    @Autowired
    public WalletService walletService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Subscription> getSubscriptionsByUserId(Long id) {
        return repository.findSubscriptionsByUserWalletUserId(id);
    }

    @Override
    public Page<Subscription> getSubscriptionsPageByUserId(Integer page, Integer size, Long userId) {
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        if(userId==null)
            return repository.findAll(pageable);
        return repository.findSubscriptionsByUserWalletUserId(pageable, userId);
    }

//    @Override
//    public Subscription getSubscriptionByUserAndProductId(Long userId, Long productId) {
//        Iterable<Subscription> subscriptions = repository.findSubscriptionsByUserWalletUserId(userId);
//        for(Subscription subscription: subscriptions) {
//            if(subscription.getProduct().getId().equals(productId))
//                return subscription;
//        }
//        return null;
//    }

    @Override
    public Optional<Subscription> getSubscriptionByUserAndProductId(Long userId, Long productId) {
        return repository.findSubscriptionsByUserWalletUserIdAndProductId(userId, productId);
    }



    @Override
    public Iterable<Subscription> getAllSubscriptions(Long userId) {
        if(userId == null)
            return repository.findAll();
        return repository.findSubscriptionsByUserWalletUserId(userId);
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
    public Subscription saveSubscription(Subscription subscription) {
        Long userId = walletService.getWalletById(subscription.getUserWallet().getId()).get().getUser().getId();
        Optional<Subscription> sub = repository.findSubscriptionsByUserWalletUserIdAndProductId(userId, subscription.getProduct().getId());
        if(subscription.getId()==null && sub.isPresent())
            return null;
        return repository.save(subscription);
    }

    @Override
    public void unsubscribe(Long prodId, Long userId) {
        Optional<Subscription> subscription = repository.findSubscriptionsByUserWalletUserIdAndProductId(userId, prodId);
        if(subscription.isPresent())
            repository.deleteById(subscription.get().getId());
//        Iterable<Subscription> subscriptions = repository.findSubscriptionsByProductWalletUserId(userId);
//        for(Subscription subscription: subscriptions) {
//            if(subscription.getProduct().getId().equals(prodId))
//                repository.deleteById(subscription.getId());
//        }
    }

    @Override
    public void deleteSubscription(Long id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        repository.deleteAllByUserWalletUserId(userId);
    }
}
