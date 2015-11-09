package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.Config;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
/**
 * This class contains methods which returns either a hashed or
 * encrypted/decrypted string from a string argument
 * @author Team Depardieu
 * Usage: security.hashing(String), security.encrypt(String message, String key),
 * security.decrypt(String message, String key)
 */
public class Security {

    /**
     * This method returns a hashed (SHA-256) String
     * @param input
     * @return String
     */
    public static String hashing(String input) {

        MessageDigest digester = null;
        String salt = Config.getHashingSalt();
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

    /**
     * This method returns an encrypted (XOR) String
     * Source: http://stackoverflow.com/questions/13641563/xor-cipher-in-java-php-different-results
     * @param message
     * @param key
     * @return
     */
        public String encrypt(String message, String key){
            try {
                if (message==null || key==null ) return null;

                char[] keys=key.toCharArray();
                char[] mesg=message.toCharArray();
                BASE64Encoder encoder = new BASE64Encoder();

                int ml=mesg.length;
                int kl=keys.length;
                char[] newmsg=new char[ml];

                for (int i=0; i<ml; i++){
                    newmsg[i]=(char)(mesg[i]^keys[i%kl]);
                }
                mesg=null;
                keys=null;
                String temp = new String(newmsg);
                return new String(new BASE64Encoder().encodeBuffer(temp.getBytes()));
            }
            catch ( Exception e ) {
                return null;
            }
        }

    /**
     * This method returns a decrypted (XOR) String
     * Source: http://stackoverflow.com/questions/13641563/xor-cipher-in-java-php-different-results
     * @param message
     * @param key
     * @return
     */
    public String decrypt(String message, String key){
        try {
            if (message==null || key==null ) return null;
            BASE64Decoder decoder = new BASE64Decoder();
            char[] keys=key.toCharArray();
            message = new String(decoder.decodeBuffer(message));
            char[] mesg=message.toCharArray();

            int ml=mesg.length;
            int kl=keys.length;
            char[] newmsg=new char[ml];

            for (int i=0; i<ml; i++){
                newmsg[i]=(char)(mesg[i]^keys[i%kl]);
            }
            mesg=null; keys=null;
            return new String(newmsg);
        }
        catch ( Exception e ) {
            return null;
        }
    }

}

