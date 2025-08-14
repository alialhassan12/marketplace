package app;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class hashPassword {
    
    public String hashPasswords(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] byteHash= md.digest(password.getBytes()); 
            return Base64.getEncoder().encodeToString(byteHash);//convert to Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
