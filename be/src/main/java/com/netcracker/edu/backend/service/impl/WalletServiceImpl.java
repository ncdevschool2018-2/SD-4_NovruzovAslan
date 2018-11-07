package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletServiceImpl implements WalletService {

    private WalletRepository repository;

    @Autowired
    public WalletServiceImpl(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return repository.save(wallet);
    }

    @Override
    public Optional<Wallet> getWalletById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Wallet> getAllWallets() {
        return repository.findAll();
    }

    @Override
    public void deleteWallet(Long id) {
        repository.deleteById(id);
    }
}
