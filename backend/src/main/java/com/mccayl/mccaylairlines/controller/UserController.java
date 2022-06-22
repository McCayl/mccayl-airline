package com.mccayl.mccaylairlines.controller;

import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("{id}")
    public void delUser(@PathVariable Long id) {
        userService.delById(id);
    }

    @PutMapping("{id}")
    public User updUser(@PathVariable Long id,
                        @RequestBody User user) {
        return userService.updUser(id, user);
    }
}
