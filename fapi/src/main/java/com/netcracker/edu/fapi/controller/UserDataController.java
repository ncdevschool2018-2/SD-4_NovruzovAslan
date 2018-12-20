package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<UserViewModel>> getAllUsers(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {
        return ResponseEntity.ok(userDataService.getAll(page, size));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(userDataService.getTotalPages(size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UserViewModel> getUserById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(userDataService.getUserById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/change-role", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UserViewModel> changeRole(
            @RequestBody UserViewModel user,
            @RequestParam(name = "new") Integer newRole
    ) {
        if (user != null) {
            return ResponseEntity.ok(userDataService.changeRole(user, newRole));
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody UserViewModel user) {
        if (user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> registerUser(@RequestBody UserViewModel user /*todo server validation*/) {
        if (user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    @RequestMapping(value = "/get-current-user", method = RequestMethod.GET)
    public ResponseEntity<UserViewModel> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        if(bearerToken==null){
            UserViewModel guest = new UserViewModel();
            guest.setRole(new RoleViewModel(4L));
            return ResponseEntity.ok(guest);
        }
        String login = userDataService.getUsername(bearerToken);
        return ResponseEntity.ok(userDataService.getUserByUsername(login));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteUser(@PathVariable String id) {
        userDataService.deleteUser(Long.valueOf(id));
    }

}

