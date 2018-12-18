package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.TransactionBody;
import com.netcracker.edu.fapi.models.WalletViewModel;
import com.netcracker.edu.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletDataController {

    @Autowired
    private WalletDataService walletDataService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<WalletViewModel>> getAllWallets() {
//        return ResponseEntity.ok(walletDataService.getAll());
//    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<WalletViewModel>> getWalletsByUserId(@RequestParam(name = "user_id", required = false) Long id) {
        if(id == null)
            return ResponseEntity.ok(walletDataService.getAll());
        return ResponseEntity.ok(walletDataService.getWalletsByUserId(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<WalletViewModel>> getWalletsPageByUserId(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long id) {
//        if(id == null)
//            return ResponseEntity.ok(walletDataService.getAllPage());
        return ResponseEntity.ok(walletDataService.getWalletsPageByUserId(page, size, id));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "user_id", required = false) Long user_id) {
        return ResponseEntity.ok(walletDataService.getTotalPages(size, user_id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WalletViewModel> getWalletById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(walletDataService.getWalletById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<TransactionBody> transaction(@RequestBody TransactionBody body) {
        return ResponseEntity.ok(walletDataService.transaction(body));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WalletViewModel> saveWallet(@RequestBody WalletViewModel wallet) {
        if (wallet != null) {
            return ResponseEntity.ok(walletDataService.saveWallet(wallet));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteWallet(@PathVariable String id) {
        walletDataService.deleteWallet(Long.valueOf(id));
    }

}

