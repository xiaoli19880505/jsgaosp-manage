package com.britecloud.marketingcloud.api.service.Impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.api.dao.AccessKeyDao;
import com.britecloud.marketingcloud.api.service.AccessKeyService;
import com.britecloud.marketingcloud.model.BcAccessKey;
import com.britecloud.marketingcloud.utils.IDUtils;
import com.britecloud.marketingcloud.utils.SignatureHelper;

@Service("apiAccessKeyService")
public class AccessKeyServiceImpl implements AccessKeyService {

	@Autowired
	private AccessKeyDao apiAccessKeyDao;

	@Autowired
	RedisTemplate<String, ?> redisTemplate;

	public AccessKeyServiceImpl() {

	}

	public void createAccessKey(BcAccessKey accessKey) {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance(SignatureHelper.ALGORITHM);
			keyGen.initialize(512);
			KeyPair keypair = keyGen.genKeyPair();

			// Get the bytes of the public and private keys (these go in the
			// database with API Key)
			byte[] privateKeyEncoded = keypair.getPrivate().getEncoded();
			byte[] publicKeyEncoded = keypair.getPublic().getEncoded();

			String publicKey = Base64.encodeBase64URLSafeString(publicKeyEncoded);
			String privateKey = Base64.encodeBase64URLSafeString(privateKeyEncoded);

			accessKey.setApiKey(IDUtils.getId());
			accessKey.setPublicKey(publicKey);
			accessKey.setPrivateKey(privateKey);

			apiAccessKeyDao.createAccessKey(accessKey);

		} catch (NoSuchAlgorithmException e) {
		}
	}

	public BcAccessKey findByApiKey(String apiKey) {
		
		return null;
//		BcAccessKey accessKey = null;
//		String value = (String) redisTemplate.opsForHash().get("apiKey", apiKey);
//		if (StringUtils.isNotEmpty(value)) {
//			ObjectMapper mapper = new ObjectMapper();
//
//			try {
//				accessKey = (BcAccessKey) mapper.readValue(value, BcAccessKey.class);
//			} catch (JsonParseException e) {
//			} catch (JsonMappingException e) {
//			} catch (IOException e) {
//			}
//			if (accessKey != null) {
//				return accessKey;
//			}
//		}
//
//		accessKey = apiAccessKeyDao.findByApiKey(apiKey);
//
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonString;
//		try {
//			jsonString = mapper.writeValueAsString(accessKey);
//			redisTemplate.opsForHash().put("apiKey", apiKey, jsonString);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//
//		return accessKey;
	}

	@Override
	public void deleteAccessKey(BcAccessKey accessKey) {
		apiAccessKeyDao.deleteAccessKey(accessKey);
	}

}
