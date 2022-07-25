package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.UserNotFoundException;
import com.mccayl.mccaylairlines.model.Role;
import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.repository.RoleRepository;
import com.mccayl.mccaylairlines.repository.UserRepository;
import com.mccayl.mccaylairlines.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
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
        // Default role for everyone
        user.addRole(roleRepository.findByName("ROLE_USER"));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.addRole(role);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User delRoleFromUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.delRole(role);
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
