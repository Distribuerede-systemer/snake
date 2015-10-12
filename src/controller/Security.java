package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class contains methods which returns either a hashed or
 * encrypted/decrypted string from a string argument
 * @author Team Depardieu
 */
public class Security {

    /**
     * This method returns a hashed (SHA-256) String
     * @param input
     * @return String
     */
    public String hashing(String input) {

        MessageDigest digester = null;
        //TODO: Replace with Config.
        String salt = "123456";
        String inputhash = input+salt;
        try {
            digester = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(inputhash.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}

