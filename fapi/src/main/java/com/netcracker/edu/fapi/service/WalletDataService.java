package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.TransactionBody;
import com.netcracker.edu.fapi.models.WalletViewModel;

import java.util.List;

public interface WalletDataService {
    List<WalletViewModel> getAll();
    List<WalletViewModel> getWalletsByUserId(Long id);
    List<WalletViewModel> getWalletsPageByUserId(Integer page, Integer size, Long userId);
    Integer getTotalPages(Integer size, Long user_id);
    WalletViewModel getWalletById(Long id);
    TransactionBody transaction(TransactionBody body);
    WalletViewModel saveWallet(WalletViewModel wallet);
    void deleteWallet(Long id);
}
