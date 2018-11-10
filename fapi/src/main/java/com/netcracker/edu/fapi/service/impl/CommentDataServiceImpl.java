package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.CommentViewModel;
import com.netcracker.edu.fapi.service.CommentDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CommentDataServiceImpl implements CommentDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<CommentViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        CommentViewModel[] commentViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/comments", CommentViewModel[].class);
        return commentViewModelResponse == null ? Collections.emptyList() : Arrays.asList(commentViewModelResponse);
    }

    @Override
    public CommentViewModel getCommentById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        CommentViewModel commentViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/comments/" + String.valueOf(id), CommentViewModel.class);
        return commentViewModelResponse;
    }

    @Override
    public CommentViewModel saveComment(CommentViewModel comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/comments", comment, CommentViewModel.class).getBody();
    }

    @Override
    public void deleteComment(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/comments/" + id);
    }
}
