package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.SpecialSubscription;
import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialSubscriptionRepository extends CrudRepository<SpecialSubscription, Long> {
//    Iterable<Subscription> findSpecialSubscriptionsByUserId(Long id);
    Iterable<SpecialSubscription> findSubscriptionsByUserWalletId(Long id);
}
