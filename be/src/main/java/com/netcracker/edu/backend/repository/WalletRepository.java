package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    List<Wallet> findWalletsByUserId(Long id);

    @Modifying
    @Transactional
    @Query("update Wallet w set w.value = w.value + :amount where w.id = :id")
    void increase(@Param("id") Long id, @Param("amount") Double amount);

    @Modifying
    @Transactional
    @Query("update Wallet w set w.value = w.value - :amount where w.id = :id")
    void decrease(@Param("id") Long id, @Param("amount") Double amount);
}
