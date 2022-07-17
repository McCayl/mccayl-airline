package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.UserNotFoundException;
import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.repository.UserRepository;
import com.mccayl.mccaylairlines.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updUser(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            return userRepository.saveAndFlush(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
