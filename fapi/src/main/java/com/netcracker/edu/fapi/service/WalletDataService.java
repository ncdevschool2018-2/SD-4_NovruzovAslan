package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.WalletViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletDataService {
    List<WalletViewModel> getAll();
    WalletViewModel getWalletById(Long id);
    WalletViewModel saveWallet(WalletViewModel wallet);
    void deleteWallet(Long id);
}
