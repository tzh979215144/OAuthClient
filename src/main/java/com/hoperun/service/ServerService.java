package com.hoperun.service;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.springframework.web.servlet.ModelAndView;

public interface ServerService {

	String requestServerCode(String clientId,String clientSecret) throws OAuthProblemException;
	Object toLogin(String clientId,String clientSecret,String code) throws OAuthProblemException;
	ModelAndView accessToken(String accessToken);
}
