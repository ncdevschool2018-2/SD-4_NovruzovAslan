package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserInfoViewModel;
import com.netcracker.edu.fapi.service.UserInfoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userinfos")
public class UserInfoDataController {

    @Autowired
    private UserInfoDataService userInfoDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserInfoViewModel>> getAllUserInfos() {
        return ResponseEntity.ok(userInfoDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserInfoViewModel> getUserInfoById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(userInfoDataService.getUserInfoById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserInfoViewModel> saveUserInfo(@RequestBody UserInfoViewModel userInfo /*todo server validation*/) {
        if (userInfo != null) {
            return ResponseEntity.ok(userInfoDataService.saveUserInfo(userInfo));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserInfo(@PathVariable String id) {
        userInfoDataService.deleteUserInfo(Long.valueOf(id));
    }

}

