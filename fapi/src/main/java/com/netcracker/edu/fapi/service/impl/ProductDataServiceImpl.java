package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.ProductViewModel;
//import com.netcracker.edu.fapi.models.RestPageImpl;
import com.netcracker.edu.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductDataServiceImpl implements ProductDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<ProductViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ProductViewModel[] productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products", ProductViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
    }

    @Override
    public ProductViewModel getProductById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ProductViewModel productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products/" + String.valueOf(id), ProductViewModel.class);
        return productViewModelResponse;
    }

//    @Override
//    public List<ProductViewModel> getProductsByCategoryId(Long category_id) {
//        RestTemplate restTemplate = new RestTemplate();
//        ProductViewModel[] productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products?category_id={category_id}", ProductViewModel[].class, String.valueOf(category_id));
//        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
//    }

    @Override
    public ProductViewModel saveProduct(ProductViewModel product) {
        String category_id = String.valueOf(product.getCategory().getId());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/products/" + category_id, product, ProductViewModel.class).getBody();
    }

    @Override
    public void deleteProduct(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/products/" + id);
    }

    @Override
    public List<ProductViewModel> getPage(Integer page, Integer size, Long category_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl + "/api/products?page=" + page + "&size=" + size;
        if(category_id!=null)
            url+="&category_id="+category_id;
        ProductViewModel[] productViewModelResponse = restTemplate.getForObject(url, ProductViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
    }

    @Override
    public Integer getTotalPages(Integer size, Long category_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl+"/api/products/total-pages?size=" + size;
        if(category_id!=null)
            url+="&category_id="+category_id;
        return restTemplate.getForObject(url, Integer.class);
    }

}
