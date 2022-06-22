package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.UserNotFoundException;
import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.repository.UserRepository;
import com.mccayl.mccaylairlines.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User addUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public void delById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User updUser(Long id, User newUser) {
        return userRepo.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhone(newUser.getPhone());
            return userRepo.saveAndFlush(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
