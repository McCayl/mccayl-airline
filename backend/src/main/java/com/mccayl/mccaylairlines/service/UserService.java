package com.mccayl.mccaylairlines.service;

import com.mccayl.mccaylairlines.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User addUser(User user);
    void delById(Long id);
    User updUser(Long id, User newUser);
}
