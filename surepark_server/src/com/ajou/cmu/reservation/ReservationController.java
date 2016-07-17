package com.ajou.cmu.reservation;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;


@Controller
public class ReservationController {
	
	@Resource(name = "revService")
	private ReservationServiceImpl revService;
	
		
	@RequestMapping("/rev/test.do")
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 /*git test2*/
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
			
			//�꽭��?�泥?��?�� - html5 sessionStorage �씠�슜

			map.put("1", user);
		}else{
			map.put("-1", "�궗�슜�옄 ?��?��?���삤湲� �떎�뙣");
		}
		mnv.addObject("map", map);
		
		mnv.addObject("callback", req.getParameter("callback"));
		*/
		
		map.put("1", "AAAA");
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/rev/reservation.do")
	public ModelAndView saveReservation(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		Reser	
		
			revService.save(mv);
			*/
			return mv;
	}
	
	@RequestMapping("/rev/available.do")
	public ModelAndView retrieveAvailableSpot() throws Exception {
		ModelAndView mv = new ModelAndView("Send This to Android");
		
		revService.getObject(mv);
		
		return mv;
		
	}
	
	@RequestMapping("/rev/currentstatus.do")
	public ModelAndView retrieveCurrentStatus() throws Exception {
		ModelAndView mv = new ModelAndView("Send This to Android");
		
		revService.getObject(mv);
		
		return mv;
	}
	
	@RequestMapping("/rev/identify.do")
	public ModelAndView compareIdentifier() throws Exception {
		ModelAndView mv = new ModelAndView("");
		
		revService.getObject(mv);
		
		return mv;
	}

}
