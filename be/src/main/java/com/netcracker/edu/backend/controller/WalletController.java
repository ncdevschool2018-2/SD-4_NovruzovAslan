package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Wallet> getWalletById(@PathVariable(name = "id") Long id) {
        Optional<Wallet> wallet = walletService.getWalletById(id);
        if (wallet.isPresent()) {
            return ResponseEntity.ok(wallet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Wallet saveWallet(@RequestBody Wallet account) {
        return walletService.saveWallet(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteWallet(@PathVariable(name = "id") Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

}
