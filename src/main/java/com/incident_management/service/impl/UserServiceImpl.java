/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incident_management.entity.User;
import com.incident_management.exception.UserNotFoundException;
import com.incident_management.repository.UserRepository;
import com.incident_management.service.UserService;
import javax.persistence.EntityNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
    @Override
    public void save(User user) {
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        System.out.println("hashedPassword--------------> " + hashedPassword);
//        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User with ID " + user.getId() + " not found.");
        }
    }

    @Override
    public void deleteByID(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

}
