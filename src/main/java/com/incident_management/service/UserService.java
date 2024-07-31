/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.service;

import java.util.List;

import com.incident_management.entity.User;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
public interface UserService {

    public void save(User user);

    public void update(User user);

    public void deleteByID(long id);

    public List<User> findAll();

    public User findById(long Id);

    User findUserByUsernameAndPassword(String username, String password);

}
