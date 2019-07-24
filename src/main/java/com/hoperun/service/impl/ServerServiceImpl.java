package com.hoperun.service.impl;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.hoperun.service.ServerService;
@Service
public class ServerServiceImpl implements ServerService {
//	String clientId = ReadProperties.GetValueByKey("config.properties", "clientId");
//	String clientSecret = ReadProperties.GetValueByKey("config.properties", "clientSecret");
    String accessTokenUrl = "responseCode";
    String userInfoUrl = "userInfoUrl";
    String redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/callbackCode";
    String response_type = "code";
    String code= null;

public String requestServerCode(String clientId,String clientSecret) throws OAuthProblemException{
        
		accessTokenUrl = "responseCode";                                                    
		userInfoUrl = "userInfoUrl";                                                        
		redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/callbackCode";        
		response_type = "code";                                                             
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		String requestUrl = null;
		try {
			//构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
			OAuthClientRequest accessTokenRequest = null;
			accessTokenRequest = OAuthClientRequest
								.authorizationLocation(accessTokenUrl)
						        .setResponseType(response_type)
						        .setClientId(clientId)
						        .setRedirectURI(redirectUrl)
						        .buildQueryMessage();
			requestUrl = accessTokenRequest.getLocationUri();
			System.out.println("请求coderequestUrl:"+requestUrl);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}
		return "redirect:http://10.50.130.239:8080/oauthserver/"+requestUrl ;
	}

public Object toLogin(String clientId,String clientSecret,String code) throws OAuthProblemException{
	System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//	clientId = "admin";
//	clientSecret = "1234567";
	accessTokenUrl="http://10.50.130.239:8080/oauthserver/responseAccessToken";
    userInfoUrl = "userInfoUrl";
    redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/accessToken";
    System.out.println("CODE码："+code);
    OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    try {
		OAuthClientRequest accessTokenRequest = OAuthClientRequest
				.tokenLocation(accessTokenUrl)
		        .setGrantType(GrantType.AUTHORIZATION_CODE)
		        .setClientId(clientId)
		        .setClientSecret(clientSecret)
		        .setCode(code)
		        .setRedirectURI(redirectUrl)
		        .buildQueryMessage();
		//去服务端请求access token，并返回响应
		OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
		//获取服务端返回过来的access token 
		String accessToken = oAuthResponse.getAccessToken();
		//查看access token是否过期
        Long expiresIn = oAuthResponse.getExpiresIn();
        System.out.println("客户端/callbackCode方法的token：：："+accessToken);
        System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
        return "redirect:http://10.50.130.239:8080/oauthclient01/server/accessToken?accessToken="+accessToken;
	} catch (OAuthSystemException e) {
		System.out.println("这里的异常1111111");
		e.printStackTrace();
	}
    return null;
}

public ModelAndView accessToken(String accessToken) {
	System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
	userInfoUrl = "http://10.50.130.239:8080/oauthserver/resultInfo";
	System.out.println("accessToken:"+accessToken);
	OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
	
	try {
		
        OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
        .setAccessToken(accessToken).buildQueryMessage();
        OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
        String mess = resourceResponse.getBody();
        System.out.println(mess);
        ModelAndView modelAndView = new ModelAndView("showResult");
        modelAndView.addObject("mess", mess);
        System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
        return modelAndView;
	} catch (OAuthSystemException e) {
		System.out.println("这里的异常2222222");
		e.printStackTrace();
	} catch (OAuthProblemException e) {
		System.out.println("这里的异常3333333");
		e.printStackTrace();
	}
	System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
	return null;
}

}
