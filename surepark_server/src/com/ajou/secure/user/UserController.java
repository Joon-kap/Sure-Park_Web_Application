package com.ajou.secure.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;
import com.ajou.secure.user.User;
import com.ajou.secure.user.UserServiceImpl;

@Controller
public class UserController {
	
	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	/**
	 * 濡쒓렇�씤
	 * */
	@RequestMapping("/user/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		
		User user = (User) this.userService.getObject(rp);
		
		if(user != null){
			userMap.put("userSeq", user.getUserSeq());
			userMap.put("userEmail", user.getUserEmail());
			userMap.put("userName", user.getUserName());
			
			//�꽭�뀡泥섎━ - html5 sessionStorage �씠�슜

			map.put("1", userMap);
		}else{
			map.put("-1", "濡쒓렇�씤 �떎�뙣");
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	/**
	 * 濡쒓렇�븘�썐 - html5 sessionStorage Clear
	 * */
	
	
	
	@RequestMapping("/user/getUser.do")
	public ModelAndView getUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> userMap = new HashMap<String, Object>();
		System.out.println(rp);
		User user = (User) this.userService.getObject(rp);
		
		if(user != null){
//			userMap.put("userSeq", user.getUserSeq());
//			userMap.put("userEmail", user.getUserEmail());
//			userMap.put("userName", user.getUserName());
			
			//�꽭�뀡泥섎━ - html5 sessionStorage �씠�슜

			map.put("1", user);
		}else{
			map.put("-1", "�궗�슜�옄 遺덈윭�삤湲� �떎�뙣");
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/user/test.do")
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> userMap = new HashMap<String, Object>();
		System.out.println(rp);
		int num = Integer.parseInt((String) rp.get("testValue"));
		System.out.println("num : "+ num);
		/*
		User user = (User) this.userService.getObject(rp);
		
		if(user != null){
//			userMap.put("userSeq", user.getUserSeq());
//			userMap.put("userEmail", user.getUserEmail());
//			userMap.put("userName", user.getUserName());
			
			//�꽭�뀡泥섎━ - html5 sessionStorage �씠�슜

			map.put("1", user);
		}else{
			map.put("-1", "�궗�슜�옄 遺덈윭�삤湲� �떎�뙣");
		}
		mnv.addObject("map", map);
		
		mnv.addObject("callback", req.getParameter("callback"));
		*/
		
		return mnv;
	}
	
	

	

}
