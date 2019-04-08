package com.britecloud.marketingcloud.api.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.britecloud.marketingcloud.api.service.AccessKeyService;
import com.britecloud.marketingcloud.model.BcAccessKey;
import com.britecloud.marketingcloud.utils.SignatureHelper;

public class RestSignatureFilter extends OncePerRequestFilter {
	
	@Autowired
	private AccessKeyService accessKeyService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String url = SignatureHelper.createSortedUrl(request);
		//String signature = request.getHeader(SignatureHelper.SIGNATURE_HEADER);
		String apiKey = request.getHeader(SignatureHelper.APIKEY_HEADER);
		String apiSecret = request.getHeader(SignatureHelper.APISECRET_HEADER);
		BcAccessKey accessKey = accessKeyService.findByApiKey(apiKey);
		
//		try {
//			if (accessKey==null || apiSecret==null){
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "REST apiKey or apiSecret failed validation.");
//				return;
//			}
//			
//			if (!StringUtils.equals(accessKey.getPrivateKey(),apiSecret) ){
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "REST apiKey or apiSecret failed validation.");
//				return;
//			}
//			
//			//if (!SignatureHelper.validateSignature(accessKey.getPublicKey(), url, signature)) {
//			//	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "REST signature failed validation.");
//			//	return;
//			//}
//		} catch (Exception e) {
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "The REST Security Server experienced an internal error.");
//			return;
//		}

		filterChain.doFilter(request, response);
	}
	
}