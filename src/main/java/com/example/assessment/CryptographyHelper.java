package com.example.assessment;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Utility class for cryptographic operations such as generating salt,
 * hashing passwords, and verifying hashed passwords.
 */
public class CryptographyHelper {

    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /**
     * Generates a random salt value consisting of uppercase letters and digits.
     *
     * @return A randomly generated salt value as a String.
     */
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

    /**
     * Hashes a password using SHA-256 along with a provided salt.
     *
     * @param password The password to be hashed.
     * @param salt The salt to be combined with the password.
     * @return The hashed password as a String.
     */
    public static String hashPassword(String password, String salt) {
        String combinedSalt = password + salt;
        return Hashing.sha256().hashString(combinedSalt, StandardCharsets.UTF_8).toString();
    }

    /**
     * Verifies if a provided password matches the hashed password when combined with the given salt.
     *
     * @param hashedPassword The previously hashed password.
     * @param providedPassword The password provided for verification.
     * @param salt The salt used during the hashing process.
     * @return True if the provided password, when hashed with the salt, matches the hashed password; false otherwise.
     */
    public static boolean verifyPassword(String hashedPassword, String providedPassword, String salt) {
        String combinedPassword = providedPassword + salt;
        String hashedProvidedPassword = Hashing.sha256().hashString(combinedPassword, StandardCharsets.UTF_8).toString();
        return hashedPassword.equals(hashedProvidedPassword);
    }
}
