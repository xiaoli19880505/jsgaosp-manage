/** 
 * 项目名称:91营销云
 * 文件名：SignatureHelper.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class SignatureHelper {
	
	//public static final String API_KEY = "RS00001";
	//public static String PUBLIC_KEY = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1_U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq_xfW6MPbLm1Vs14E7gB00b_JmYLdrmVClpJ-f6AR7ECLCT7up1_63xhv4O1fnxqimFQ8E-4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC_BYHPUCgYEA9-GghdabPd7LvKtcNrhXuXmUr7v6OuqC-VdMCz0HgmdRWVeOutRZT-ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN_C_ohNWLx-2J6ASQ7zKTxvqhRkImog9_hWuWfBpKLZl6Ae1UlZAFMO_7PSSoDgYUAAoGBAOxeolJTgbyKEgYaC_ur2_Q85Z1DDpmal-MCNineN1ysZOoD1LRGNkeA8DaLjPRBAoDZL7GGcoddSBq1z2Jg_7nNclODhM0OLkWiTn4d-N-4z6eAUUhV0sCuTtHz9EuGMd_UJIMkTJ0OIbjkPmZEp3ZlCIa0uGjsdAvxY83rbq7J";
	//public static String PRIVATE_KEY = "MIIBTAIBADCCASwGByqGSM44BAEwggEfAoGBAP1_U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq_xfW6MPbLm1Vs14E7gB00b_JmYLdrmVClpJ-f6AR7ECLCT7up1_63xhv4O1fnxqimFQ8E-4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC_BYHPUCgYEA9-GghdabPd7LvKtcNrhXuXmUr7v6OuqC-VdMCz0HgmdRWVeOutRZT-ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN_C_ohNWLx-2J6ASQ7zKTxvqhRkImog9_hWuWfBpKLZl6Ae1UlZAFMO_7PSSoEFwIVAI804Mu_sKHgWi2o3KyHiwnj0WP1";
	
	public static final String APIKEY_HEADER = "apikey";
	public static final String APISECRET_HEADER = "apisecret";
	public static final String TIMESTAMP_HEADER = "timestamp";
	public static final String SIGNATURE_HEADER = "signature";
	public static final List<String> SIGNATURE_KEYWORDS = Arrays.asList(APIKEY_HEADER, TIMESTAMP_HEADER);

	public static final String ALGORITHM = "DSA";
	

	public static String createSignature(HttpHeaders headers, String url, String privateKey) throws Exception {
		
		TreeMap<String, String> sortedHeaders = new TreeMap<String, String>();
		for (String key : headers.keySet()) {
			if (SIGNATURE_KEYWORDS.contains(key)) {
				sortedHeaders.put(key, headers.get(key).get(0));
			}
		}
		
		String sortedUrl = createSortedUrl(url, sortedHeaders);
		
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		
		Signature sig = Signature.getInstance(ALGORITHM);		
		sig.initSign(keyFactory.generatePrivate(privateKeySpec));
		sig.update(sortedUrl.getBytes());
		
		return Base64.encodeBase64URLSafeString(sig.sign());
	}

	private static PublicKey decodePublicKey(String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		byte[] publicKeyBytes = Base64.decodeBase64(publicKey);
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		return keyFactory.generatePublic(publicKeySpec);
	}

	public static boolean validateSignature(String publicKey,String url, String signatureString) throws InvalidKeyException, Exception {
		
		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initVerify(decodePublicKey(publicKey));
		signature.update(url.getBytes());
		try {
			return signature.verify(Base64.decodeBase64(signatureString));
		} catch (SignatureException e) {
			return false;
		}
	}

	public static String createSortedUrl(HttpServletRequest request) {
		
		// use a TreeMap to sort the headers and parameters
		TreeMap<String, String> headersAndParams = new TreeMap<String, String>();	
		
		// load header values we care about
		Enumeration e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (SIGNATURE_KEYWORDS.contains(key)) {
				headersAndParams.put(key, request.getHeader(key));
			}
		}
		
		// load parameters
		for (Object key : request.getParameterMap().keySet()) {
			String[] o = (String[]) request.getParameterMap().get(key);
			headersAndParams.put((String) key, o[0]);
		}
	
		if (request.getPathInfo()==null){
			return createSortedUrl(
					request.getContextPath() + request.getServletPath(),
					headersAndParams);
		}
		return createSortedUrl(
				request.getContextPath() + request.getServletPath() + request.getPathInfo(),
				headersAndParams);
		
	}

	public static String createSortedUrl(String url, TreeMap<String, String> headersAndParams) {
		// build the url with headers and parms sorted
		String params = "";
		for (String key : headersAndParams.keySet()) {
			if (params.length() > 0) {
				params += "@";
			}
			params += key + "=" + headersAndParams.get(key).toString();
		}
		if (!url.endsWith("?")) url += "?";
		return url + params;	
	}
	
//	public static void main(String[] args) {
//		KeyPairGenerator keyGen;
//		try {
//			keyGen = KeyPairGenerator.getInstance(SignatureHelper.ALGORITHM);
//			keyGen.initialize(512);
//			KeyPair keypair = keyGen.genKeyPair();
//
//			// Get the bytes of the public and private keys (these go in the
//			// database with API Key)
//			byte[] privateKeyEncoded = keypair.getPrivate().getEncoded();
//			byte[] publicKeyEncoded = keypair.getPublic().getEncoded();
//
//			String publicKey = Base64.encodeBase64URLSafeString(publicKeyEncoded);
//			String privateKey = Base64.encodeBase64URLSafeString(privateKeyEncoded);
//			
//			System.out.println(privateKey);
//			
//		}catch(Exception e){
//			
//		}
//	}

}