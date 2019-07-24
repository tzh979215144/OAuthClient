package com.hoperun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hoperun.service.impl.ServerServiceImpl;
import com.hoperun.utils.ReadProperties;

/**
 * 
 * @ClassName: ServerController   
 * @Description: oauth服务端Controller
 * @Description:
 */

@RequestMapping("/server")
@Controller
public class ServerController{
	
	@Autowired
	ServerServiceImpl serverServiceImpl;
	
	
	String clientId = ReadProperties.GetValueByKey("config.properties", "clientId");
	String clientSecret = ReadProperties.GetValueByKey("config.properties", "clientSecret");
    String accessTokenUrl = "responseCode";
    String userInfoUrl = "userInfoUrl";
    String redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/callbackCode";
    String response_type = "code";
    String code= null;
	
  //测试
  	@RequestMapping("/test")
  	public ModelAndView test(){
		
		ModelAndView mv = new ModelAndView("test");
		
		return mv;
	}
    
	/**
	 * 向服务器申请CODE码
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/requestServerCode")
	public String requestServerFirst(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws OAuthProblemException{
		accessTokenUrl = "responseCode";                                                    
		userInfoUrl = "userInfoUrl";                                                        
		redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/callbackCode";        
		response_type = "code";                                                             
		if(request.getParameter("id")!=null) {
			clientId = request.getParameter("id");
		}
		System.out.println("我的clientId是:"+clientId);
		if(request.getParameter("password")!=null) {
			clientSecret = request.getParameter("password");
		}
		return serverServiceImpl.requestServerCode(clientId,clientSecret);
		}
	
	/**
	 * 接受客户端返回的code，提交申请access token的请求
	 * @param request
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/callbackCode")
	public Object toLogin(HttpServletRequest request) throws OAuthProblemException{
		System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
		System.out.println(clientId);
		accessTokenUrl="http://10.50.130.239:8080/oauthserver/responseAccessToken";
	    userInfoUrl = "userInfoUrl";
	    redirectUrl = "http://10.50.130.239:8080/oauthclient01/server/accessToken";
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    code = httpRequest.getParameter("code"); 
	    return serverServiceImpl.toLogin(clientId,clientSecret,code);
	}
	
	//接受服务端传回来的access token，由此token去请求服务端的资源（用户信息等）
	@RequestMapping("/accessToken")
	public ModelAndView accessToken(String accessToken) {
		System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
		return serverServiceImpl.accessToken(accessToken);
	}
	
}