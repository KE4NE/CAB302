package com.example.assessment;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CryptographyHelper {

    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateSalt() {
        StringBuilder salt = new StringBuilder();
        int saltLength = 10;
        int availableChars = SALTCHARS.length();
        Random randVal = new Random();
        for (int i = 0; i < saltLength; i++) {
            int randIndex = randVal.nextInt(availableChars);
            salt.append(SALTCHARS.charAt(randIndex));
        }
        return salt.toString();
    }

    public static String hashPassword(String password, String salt) {
        String combinedSalt = password + salt;
        return Hashing.sha256().hashString(combinedSalt, StandardCharsets.UTF_8).toString();
    }

    public static boolean verifyPassword(String hashedPassword, String providedPassword, String salt) {
        String combinedPassword = providedPassword + salt;
        String hashedProvidedPassword = Hashing.sha256().hashString(combinedPassword, StandardCharsets.UTF_8).toString();
        return hashedPassword.equals(hashedProvidedPassword);
    }
}
