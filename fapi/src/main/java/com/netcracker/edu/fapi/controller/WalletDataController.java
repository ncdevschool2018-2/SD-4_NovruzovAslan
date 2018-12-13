package com.netcracker.edu.fapi.controller;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<WalletViewModel>> getWalletsByUserId(@RequestParam(name = "user_id", required = false) Long id) {
        if(id == null)
            return ResponseEntity.ok(walletDataService.getAll());
        return ResponseEntity.ok(walletDataService.getWalletsByUserId(id));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WalletViewModel> getWalletById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(walletDataService.getWalletById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WalletViewModel> saveWallet(@RequestBody WalletViewModel wallet /*todo server validation*/) {
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

