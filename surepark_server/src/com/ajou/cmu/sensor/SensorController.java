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
	
	public static int entrygateIrStatus = 1;
	
//	@RequestMapping("/rev/arduinoupdate.do")
	@RequestMapping("/sensor/changeStatus.do")
	   public ModelAndView test(HttpServletRequest req, HttpServletResponse res) throws Exception {
	      RequestParameter rp = Utils.extractRequestParameters(req);   
	      ModelAndView mnv = new ModelAndView("/common/json_result");
	       /*git test2*/
	      Map<String, Object> map = new HashMap<String, Object>();
//	      Map<String, Object> userMap = new HashMap<String, Object>();

	      System.out.println(rp);
	      
//	      int num = Integer.parseInt((String) rp.get("testValue"));
	      String str = (String)rp.get("SENSORUPDATE");
	      
	      //¿©±â¼­ ¼¾¼­¿¡¼­ ÀúÀåÇÏ°í ÀÖÀ» °ªÀ» str¿¡¼­ »Ì¾Æ³¿
	      if(str.charAt(0)=='0') {
	    	  entrygateIrStatus = 0;
	    	  System.out.println("entrygateIrStatus = " + entrygateIrStatus);

	      }

	      //SensorController.gateIrStatus;
	      map.put("RETUPDATE", str);
	      mnv.addObject("map", map);
	      mnv.addObject("callback", req.getParameter("callback"));
	      
	      return mnv;
	   }
		
	@RequestMapping("/sensor/changeStatusTest.do")
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
			
			//ï¿½ê½­ï¿½ï¿½?ï¿½ï§£?ï¿½ï¿½?ï¿½ï¿½ - html5 sessionStorage ï¿½ì” ï¿½ìŠœ

			map.put("1", user);
		}else{
			map.put("-1", "ï¿½ê¶—ï¿½ìŠœï¿½ì˜„ ?ï¿½ï¿½?ï¿½ï¿½?ï¿½ï¿½ï¿½ì‚¤æ¹²ï¿½ ï¿½ë–Žï¿½ë™£");
		}
		mnv.addObject("map", map);
		
		mnv.addObject("callback", req.getParameter("callback"));
		*/
		System.out.println("change status");
		//OpenEntryMessage = 0;
		
		map.put("1", "AAAA");
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

}
