package com.ajou.cmu.sensor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.cmu.common.Log;
import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;
import com.ajou.cmu.common.WebSocketModule;
import com.ajou.cmu.reservation.ReservationServiceImpl;

@Controller
public class SensorController {
	
	@Resource(name = "sensorService")
	private SensorServiceImpl sensorService;
	
	//public static int entrygateIrStatus = 1;
	//public static int spot1IrStatus[] = new int[5];
	
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
			  System.out.println("str = " + str);

		      SensorStatus.setSensors(str);
		      
		      System.out.println("entrygateIr = " + SensorStatus.getEntryGate(1));
			  System.out.println("exitIr = " + SensorStatus.getExitGate(1));
			  System.out.println("spot1Ir = " + SensorStatus.getSpot(1));
			  System.out.println("spot2Ir = " + SensorStatus.getSpot(2));
			  System.out.println("spot3Ir = " + SensorStatus.getSpot(3));
			  System.out.println("spot4Ir = " + SensorStatus.getSpot(4));
			  
			  map.put("RETUPDATE", str);
			  
			  sendMesseageToAttendant();
		
	      //���⼭ �������� �����ϰ� ���� ���� str���� �̾Ƴ�
	      
	      /*
	      if(SensorStatus.getEntryGate()==0) {
	    	  System.out.println("entrygateIrStatus = " + SensorStatus.getEntryGate());
	      }
	      
	      if(str.charAt(1)=='0') {
	    //	  spot1IrStatus = 0;
	    	  System.out.println("entrygateIrStatus = " + entrygateIrStatus);
	      }
	      */

	      //SensorController.gateIrStatus;
			  
	      mnv.addObject("map", map);
	      mnv.addObject("callback", req.getParameter("callback"));
	      
	      return mnv;
	   }
	
	
	@RequestMapping("/sensor/getStatus.do")
	   public ModelAndView getStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
	      RequestParameter rp = Utils.extractRequestParameters(req);   
	      ModelAndView mnv = new ModelAndView("/common/json_result");
	      Map<String, Object> map = new HashMap<String, Object>();

		  System.out.println(rp);
		  
		  System.out.println("SENSORREQUEST>>");
		     
		  map.put("ENTRY",  SensorStatus.getEntryGate(1));
		  map.put("EXIT",  SensorStatus.getExitGate(1));
		  map.put("LED3",  SensorStatus.getSpot(3));
		  map.put("LED4",  SensorStatus.getSpot(4));
		  map.put("LED1",  SensorStatus.getSpot(1));
		  map.put("LED2",  SensorStatus.getSpot(2));

		  


	      //���⼭ �������� �����ϰ� ���� ���� str���� �̾Ƴ�
	      
	      /*
	      if(SensorStatus.getEntryGate()==0) {
	    	  System.out.println("entrygateIrStatus = " + SensorStatus.getEntryGate());
	      }
	      
	      if(str.charAt(1)=='0') {
	    //	  spot1IrStatus = 0;
	    	  System.out.println("entrygateIrStatus = " + entrygateIrStatus);
	      }
	      */

	      //SensorController.gateIrStatus;
	      
	      mnv.addObject("map", map);
	      mnv.addObject("callback", req.getParameter("callback"));
	      
	      return mnv;
	   }
	
	
		
	private void sendMesseageToAttendant() {
			try {
				if(WebSocketModule.thisSession != null)
					WebSocketModule.thisSession.getBasicRemote().sendText(Log.SENSOR_CHANGED, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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
			
			//�꽭��?�泥?��?�� - html5 sessionStorage �씠�슜

			map.put("1", user);
		}else{
			map.put("-1", "�궗�슜�옄 ?��?��?���삤湲� �떎�뙣");
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
