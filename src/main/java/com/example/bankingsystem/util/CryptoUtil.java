package com.example.bankingsystem.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CryptoUtil {
    private static final String salt = "user@email.com";
    private static final int iterations = 32;

    public static String hash(String plainText) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec keySpec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations, 512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHMacSHA256");
        byte[] bytes = skf.generateSecret(keySpec).getEncoded();
        String password = Base64.getEncoder().encodeToString(bytes);

        return password;
    }

    public static boolean check(String password, String cipherText) throws NoSuchAlgorithmException, InvalidKeySpecException {
        password = hash(password);
        return password.equals(cipherText);
    }
}
