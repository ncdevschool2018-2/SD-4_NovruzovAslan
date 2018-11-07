package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CommentViewModel;
import com.netcracker.edu.fapi.service.CommentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentDataController {

    @Autowired
    private CommentDataService billingAccountDataService;

    @RequestMapping
    public ResponseEntity<List<CommentViewModel>> getAllComments() {
        return ResponseEntity.ok(billingAccountDataService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentViewModel> saveComment(@RequestBody CommentViewModel billingAccount /*todo server validation*/) {
        if (billingAccount != null) {
            return ResponseEntity.ok(billingAccountDataService.saveComment(billingAccount));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable String id) {
        billingAccountDataService.deleteComment(Long.valueOf(id));
    }

}
