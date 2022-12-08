package Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordHasher {
    public static String HashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        /*SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return hash;*/

        int hash = 7;
        for (int i = 0; i < password.length(); i++) {
            hash = hash*31 + password.charAt(i);
        }
        return Integer.toString(hash);
    }
}
