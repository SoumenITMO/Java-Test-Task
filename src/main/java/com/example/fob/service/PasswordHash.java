package com.example.fob.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHash {

    public String hashPassword(String plainTextPassword) {
        String hashText = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(plainTextPassword.getBytes());
            BigInteger hasedPassword = new BigInteger(1, messageDigest);
            hashText = hasedPassword.toString(16);
        } catch (NoSuchAlgorithmException algorithmDoesNotExists) {
            System.out.println(algorithmDoesNotExists.getMessage());
        }
        return hashText;
    }
}
