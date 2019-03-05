package com.example.demo.domain;

import java.security.Key;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Converter
@Component
public class PasswordConverter implements AttributeConverter<String, String> {

	@Value("${custom.secret.algorithm}")
	private static String ALGORITHM;

	@Value("${custom.secret.key}")
	private String secretKey;
	
	private byte[] secretKeyByte;
	
	@PostConstruct
	public void init() {
		secretKeyByte = secretKey.getBytes();
	}
	
	@Override
	public String convertToDatabaseColumn(String password) {
		Key key = new SecretKeySpec(secretKeyByte, "AES");
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String valueInDB) {
		Key key = new SecretKeySpec(secretKeyByte, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(valueInDB)));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
