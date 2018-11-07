package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Wallet;

import java.util.Optional;

public interface WalletService {
    Wallet saveWallet(Wallet category);
    Optional<Wallet> getWalletById(Long id);
    Iterable<Wallet> getAllWallets();
    void deleteWallet(Long id);
}
