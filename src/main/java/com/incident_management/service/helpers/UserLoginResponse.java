/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.service.helpers;

/**
 *
 * @author user
 */
public class UserLoginResponse {

    private String username;
    private String sessionId; // JWT or session token
    private String message;
    private Long userId;

    public UserLoginResponse(String username, Long userId, String sessionId, String message) {
        this.username = username;
        this.userId = userId;
        this.sessionId = sessionId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
