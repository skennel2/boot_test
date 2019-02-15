package com.example.demo.domain;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Converter
@Component
public class PasswordConverter implements AttributeConverter<String, String> {

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

	@Value("${custom.secret.key}")
	private String _secretKey;
	
	@Override
	public String convertToDatabaseColumn(String password) {
		Key key = new SecretKeySpec(_secretKey.getBytes(), "AES");
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
		Key key = new SecretKeySpec(_secretKey.getBytes(), "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(valueInDB)));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
