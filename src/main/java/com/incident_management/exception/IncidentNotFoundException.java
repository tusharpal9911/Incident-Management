/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.exception;

/**
 *
 * @author user
 */
public class IncidentNotFoundException extends Exception {

    public IncidentNotFoundException(String message) {
        super(message);
    }
}
