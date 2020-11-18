import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Crypt {

    private final IvParameterSpec IvSpecDES;
    private final IvParameterSpec IvSpecAES;
    private final Key keyDES;
    private final Key keyAES;
    private Cipher CIPHER_DES_CBC;
    private Cipher CIPHER_DES_OFB;
    private Cipher CIPHER_AES_CBC;
    private Cipher CIPHER_AES_OFB;

    public Crypt(String IvAES_enc, String IvDES_enc, String keyAES_enc, String keyDES_enc) {
        this.keyAES = new SecretKeySpec(Base64.getDecoder().decode(keyAES_enc), 0,
                Base64.getDecoder().decode(keyAES_enc).length, "AES");
        this.keyDES = new SecretKeySpec(Base64.getDecoder().decode(keyDES_enc), 0,
                Base64.getDecoder().decode(keyDES_enc).length, "DES");
        this.IvSpecAES = new IvParameterSpec(Base64.getDecoder().decode(IvAES_enc));
        this.IvSpecDES = new IvParameterSpec(Base64.getDecoder().decode(IvDES_enc));

        init();
    }

    private void init() {
        try {

            CIPHER_DES_CBC = Cipher.getInstance("DES/CBC/PKCS5Padding");
            CIPHER_DES_OFB = Cipher.getInstance("DES/OFB/PKCS5Padding");
            CIPHER_AES_CBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            CIPHER_AES_OFB = Cipher.getInstance("AES/OFB/PKCS5Padding");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String AES_CBC(String msg, boolean isEnc) {
        try {
            if(isEnc) {
                CIPHER_AES_CBC.init(Cipher.ENCRYPT_MODE, keyAES, IvSpecAES);
                byte[] bytesMSG;
                bytesMSG = CIPHER_AES_CBC.doFinal(msg.getBytes());
                byte[] encodedMSG64 = Base64.getEncoder().encode(bytesMSG);
                return new String(encodedMSG64);
            }
            byte[] bytesMSG64 = Base64.getDecoder().decode(msg);
            CIPHER_AES_CBC.init(Cipher.DECRYPT_MODE, keyAES, IvSpecAES);
            byte[] decodedMSG = CIPHER_AES_CBC.doFinal(bytesMSG64);
            return new String(decodedMSG, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String DES_CBC(String msg, boolean isEnc) {
        try {
            if(isEnc) {
                CIPHER_DES_CBC.init(Cipher.ENCRYPT_MODE, keyDES, IvSpecDES);
                byte[] bytesMSG = CIPHER_DES_CBC.doFinal(msg.getBytes());
                byte[] encodedMSG64 = Base64.getEncoder().encode(bytesMSG);
                return new String(encodedMSG64);
            }

            byte[] bytesMSG64 = Base64.getDecoder().decode(msg);
            CIPHER_DES_CBC.init(Cipher.DECRYPT_MODE, keyDES, IvSpecDES);
            byte[] decodedMSG = CIPHER_DES_CBC.doFinal(bytesMSG64);
            return new String(decodedMSG, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String AES_OFB(String msg, boolean isEnc) {
        try {
            if(isEnc) {
                CIPHER_AES_OFB.init(Cipher.ENCRYPT_MODE, keyAES, IvSpecAES);
                byte[] bytesMSG = CIPHER_AES_OFB.doFinal(msg.getBytes());
                byte[] encodedMSG64 = Base64.getEncoder().encode(bytesMSG);
                return new String(encodedMSG64);
            }
            byte[] bytesMSG64 = Base64.getDecoder().decode(msg);
            CIPHER_AES_OFB.init(Cipher.DECRYPT_MODE, keyAES, IvSpecAES);
            byte[] decodedMSG = CIPHER_AES_OFB.doFinal(bytesMSG64);
            return new String(decodedMSG, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String DES_OFB(String msg, boolean isEnc) {
        try {
            if(isEnc) {
                CIPHER_DES_OFB.init(Cipher.ENCRYPT_MODE, keyDES, IvSpecDES);
                byte[] bytesMSG = CIPHER_DES_OFB.doFinal(msg.getBytes());
                byte[] encodedMSG64 = Base64.getEncoder().encode(bytesMSG);
                return new String(encodedMSG64);
            }

            byte[] bytesMSG64 = Base64.getDecoder().decode(msg);
            CIPHER_DES_OFB.init(Cipher.DECRYPT_MODE, keyDES, IvSpecDES);
            byte[] decodedMSG = CIPHER_DES_OFB.doFinal(bytesMSG64);
            return new String(decodedMSG, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
