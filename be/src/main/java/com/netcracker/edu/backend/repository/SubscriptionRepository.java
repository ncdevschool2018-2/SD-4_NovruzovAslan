package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends
        CrudRepository<Subscription, Long>,
        PagingAndSortingRepository<Subscription, Long> {
//    Iterable<Subscription> findSubscriptionsByUserId(Long id);
    Iterable<Subscription> findSubscriptionsByUserWalletUserId(Long userId);

    @Transactional
    void deleteAllByUserWalletUserId(Long userId);
    Optional<Subscription> findSubscriptionsByUserWalletUserIdAndProductId(Long userId, Long productId);
    Page<Subscription> findSubscriptionsByUserWalletUserId(Pageable pageable, Long id);
//    Page<Subscription> findSubscriptionsByProductWalletUserId(Pageable pageable, Long id);
}
