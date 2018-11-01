package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UserInfo;
import com.netcracker.edu.backend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/userinfos")
public class UserInfoController {

    private UserInfoService userService;

    @Autowired
    public UserInfoController(UserInfoService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserInfo> getUserInfoById(@PathVariable(name = "id") Long id) {
        Optional<UserInfo> user = userService.getUserInfoById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<UserInfo> getAllUserInfos() {
        return userService.getAllUserInfos();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserInfo saveUserInfo(@RequestBody UserInfo account) {
        return userService.saveUserInfo(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserInfo(@PathVariable(name = "id") Long id) {
        userService.deleteUserInfo(id);
        return ResponseEntity.noContent().build();
    }

}
