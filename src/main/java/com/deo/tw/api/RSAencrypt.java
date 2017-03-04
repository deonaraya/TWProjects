package com.deo.tw.api;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by chandrad on 8/14/16.
 */
public class RSAencrypt {

        public RSAencrypt() {
        }

        public  String encrypt(String input, String keyFileName) throws NoSuchPaddingException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, this.getPublicKey(keyFileName));
            byte[] bytes = Base64.encodeBase64(cipher.doFinal(input.getBytes()));
            return new String(bytes);
        }

        private PublicKey getPublicKey(String publicKeyFileName) throws KeyStoreException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, CertificateException, InvalidKeySpecException {
            PemReader pemReader = new PemReader(new FileReader("/Users/chandrad/IdeaProjects/apiSuite/src/main/resources/public.pem"));
            PemObject pemObject = pemReader.readPemObject();
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pemObject.getContent());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(spec);
            return publicKey;
        }

        public void getTokens(){

        }
    }


