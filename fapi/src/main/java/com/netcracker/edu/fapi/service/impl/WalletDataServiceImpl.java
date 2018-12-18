package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.TransactionBody;
import com.netcracker.edu.fapi.models.WalletViewModel;
import com.netcracker.edu.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WalletDataServiceImpl implements WalletDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<WalletViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        WalletViewModel[] walletViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/wallets/all", WalletViewModel[].class);
        return walletViewModelResponse == null ? Collections.emptyList() : Arrays.asList(walletViewModelResponse);
    }

    @Override
    public List<WalletViewModel> getWalletsByUserId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        WalletViewModel[] walletViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/wallets/all?user_id={user_id}", WalletViewModel[].class, String.valueOf(id));
        return walletViewModelResponse == null ? Collections.emptyList() : Arrays.asList(walletViewModelResponse);
    }

    @Override
    public WalletViewModel getWalletById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        WalletViewModel walletViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/wallets/all" + id, WalletViewModel.class);
        return walletViewModelResponse;
    }

    @Override
    public List<WalletViewModel> getWalletsPageByUserId(Integer page, Integer size, Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl + "/api/wallets?page=" + page + "&size=" + size;
        if(user_id!=null)
            url+="&user_id="+user_id;
        WalletViewModel[] productViewModelResponse = restTemplate.getForObject(url, WalletViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
    }

    @Override
    public Integer getTotalPages(Integer size, Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl+"/api/wallets/total-pages?size=" + size;
        if(user_id!=null)
            url+="&user_id="+user_id;
        return restTemplate.getForObject(url, Integer.class);
    }

    @Override
    public TransactionBody transaction(TransactionBody body) {
//        RestTemplate restTemplate = new RestTemplate();
        return new RestTemplate().postForEntity(backendServerUrl + "/api/wallets/transaction", body, TransactionBody.class).getBody();
    }

    @Override
    public WalletViewModel saveWallet(WalletViewModel wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/wallets", wallet, WalletViewModel.class).getBody();
    }

    @Override
    public void deleteWallet(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/wallets/" + id);
    }
}

