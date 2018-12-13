package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Wallet;

import java.util.Optional;

public interface WalletService {
    Wallet saveWallet(Wallet wallet);
    Optional<Wallet> getWalletById(Long id);
    Iterable<Wallet> getWalletsByUserId(Long id);
    Iterable<Wallet> getAllWallets();
    void increaseWallet(Long id, Double value);
    void decreaseWallet(Long id, Double value);
    void deleteWallet(Long id);
}
