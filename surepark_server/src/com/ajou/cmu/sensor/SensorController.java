package com.ajou.cmu.sensor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;
import com.ajou.cmu.reservation.ReservationServiceImpl;

@Controller
public class SensorController {
	
	@Resource(name = "sensorService")
	private SensorServiceImpl sensorService;
	
	public static int gateIrStatus = 0;
	
		
	@RequestMapping("/sensor/changeStatus.do")
	public ModelAndView changeStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 /*git test2*/
		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> userMap = new HashMap<String, Object>();

		System.out.println(rp);
		
		//int num = Integer.parseInt((String) rp.get("testValue"));
		
		//System.out.println("num : "+ num);
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
		System.out.println("change status");
		gateIrStatus = 1;
		
		map.put("1", "AAAA");
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

}
