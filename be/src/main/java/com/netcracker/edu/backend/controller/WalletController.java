package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.TransBody;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Iterable<Wallet> getAllWallets() {
//        return walletService.getAllWallets();
//    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Wallet> getWalletsByUserId(@RequestParam(name = "user_id", required = false) Long id) {
        if (id == null)
            return walletService.getAllWallets();
        return walletService.getWalletsByUserId(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Wallet> getWalletsPageByUserId(
            @RequestParam(name = "page") Integer pageNumber,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long user_id) {
        Page page = walletService.getPage(pageNumber, size, user_id);
        return page.getContent();
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public Integer getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long user_id) {
        Page page = walletService.getPage(1, size,user_id);
        return page.getTotalPages();
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public TransBody transaction(@RequestBody TransBody body) {
        return walletService.transaction(body);
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
