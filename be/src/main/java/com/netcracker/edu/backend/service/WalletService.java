package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.TransBody;
import com.netcracker.edu.backend.entity.Wallet;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WalletService {
    Wallet saveWallet(Wallet wallet);
    Optional<Wallet> getWalletById(Long id);
    Iterable<Wallet> getWalletsByUserId(Long id);
    Iterable<Wallet> getAllWallets();
    Page<Wallet> getPage(Integer page, Integer size, Long user_id);
    TransBody transaction(TransBody body);
    void increaseWallet(Long id, Double value);
    void decreaseWallet(Long id, Double value);
    void deleteWallet(Long id);
}
