import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;


public class Encryption
{
    public static void main (String ... args)
    {
        KeyGenerator keyGenerator_DES;
        KeyGenerator keyGenerator_AES;
        SecureRandom secRandom = new SecureRandom();

        try {
            keyGenerator_DES = KeyGenerator.getInstance("DES");
            keyGenerator_AES = KeyGenerator.getInstance("AES");
            keyGenerator_AES.init(secRandom);
            keyGenerator_DES.init(secRandom);
            Key key_AES = keyGenerator_AES.generateKey();
            Key key_DES = keyGenerator_DES.generateKey();

            Cipher cipher_DES_CBC = Cipher.getInstance("DES/CBC/PKCS5Padding");
            Cipher cipher_DES_OFB = Cipher.getInstance("DES/OFB/PKCS5Padding");
            Cipher cipher_AES_CBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Cipher cipher_AES_OFB = Cipher.getInstance("AES/OFB/PKCS5Padding");

            cipher_DES_CBC.init(Cipher.ENCRYPT_MODE, key_DES);
            cipher_DES_OFB.init(Cipher.ENCRYPT_MODE, key_DES);
            cipher_AES_CBC.init(Cipher.ENCRYPT_MODE, key_AES);
            cipher_AES_OFB.init(Cipher.ENCRYPT_MODE, key_AES);


            String message = "Encryption";

            byte[] bytes_DES_CBC = cipher_DES_CBC.doFinal(message.getBytes());
            byte[] bytes_DES_OFB = cipher_DES_OFB.doFinal(message.getBytes());
            byte[] bytes_AES_CBC = cipher_AES_CBC.doFinal(message.getBytes());
            byte[] bytes_AES_OFB = cipher_AES_OFB.doFinal(message.getBytes());

            byte[] encoded_DES_CBC = Base64.getEncoder().encode(bytes_DES_CBC);
            byte[] encoded_DES_OFB = Base64.getEncoder().encode(bytes_DES_OFB);
            byte[] encoded_AES_CBC = Base64.getEncoder().encode(bytes_AES_CBC);
            byte[] encoded_AES_OFB = Base64.getEncoder().encode(bytes_AES_OFB);




            byte[] bytes = cipher_AES_CBC.getIV();
            byte[] bytes_iv = Base64.getEncoder().encode(bytes);


            System.out.println(new String(encoded_AES_OFB));


            byte[] decoded = Base64.getDecoder().decode(encoded_AES_OFB);
            Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key_AES);
            byte[] sdecoded = cipher.doFinal(decoded);
            String aes_decoded = new String(sdecoded, StandardCharsets.UTF_8);
            System.out.println(aes_decoded);



        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}