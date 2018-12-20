package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.SpecialSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialSubscriptionRepository extends
        CrudRepository<SpecialSubscription, Long>,
        PagingAndSortingRepository<SpecialSubscription, Long> {
//    Iterable<SpecialSubscription> findSpecialSubscriptionsBySpecialProductProductWalletUserId(Long id);
//    Page<SpecialSubscription> findSpecialSubscriptionsByUserWalletUserId(Pageable pageable, Long id);
}
