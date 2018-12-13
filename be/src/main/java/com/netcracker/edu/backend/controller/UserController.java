package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.scheduler.ScheduleTask;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    private ScheduleTask task;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/testcron", method = RequestMethod.POST)
    public void testCron() {
        task.cronSchedule();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/u/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "username") String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
