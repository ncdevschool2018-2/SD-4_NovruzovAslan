package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CommentViewModel;
import com.netcracker.edu.fapi.service.CommentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentDataController {

    @Autowired
    private CommentDataService commentDataService;

    @RequestMapping
    public ResponseEntity<List<CommentViewModel>> getAllComments() {
        return ResponseEntity.ok(commentDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentViewModel> getCommentById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(commentDataService.getCommentById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentViewModel> saveComment(@RequestBody CommentViewModel comment /*todo server validation*/) {
        if (comment != null) {
            return ResponseEntity.ok(commentDataService.saveComment(comment));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteComment(@PathVariable String id) {
        commentDataService.deleteComment(Long.valueOf(id));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('user')")
    public void deleteOwnComment(@RequestParam(name = "user_id") String userId) {

    }

}
