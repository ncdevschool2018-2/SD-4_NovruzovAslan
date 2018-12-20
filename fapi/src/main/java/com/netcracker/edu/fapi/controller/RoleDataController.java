package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.service.RoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleDataController {

    @Autowired
    private RoleDataService roleDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<RoleViewModel>> getRoles() {
        return ResponseEntity.ok(roleDataService.getAll());
    }
}
