/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author user
 */
public class Util {

    public static String encryptPassword(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5"); // Creating an instance of MessageDigest with MD5 algorithm

            md.update(password.getBytes()); // Updating the MessageDigest with the input password's bytes

            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder(); // Converting the resulting byte array into a hexadecimal string
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

}
