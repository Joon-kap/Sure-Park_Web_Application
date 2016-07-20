package com.ajou.cmu.reservation;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import com.ajou.cmu.sensor.SensorController;
import com.ajou.cmu.sensor.SensorStatus;


@Controller
public class ReservationController {
	
	public static int identification = 0;
	
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
		
//		int num = Integer.parseInt((String) rp.get("testValue"));
		String str = (String)rp.get("SENSORUPDATE");
		
		//여기서 센서에서 저장하고 있을 값을 str에서 뽑아냄
		
		
		map.put("RETUPDATE", str);
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/rev/reservation.do")
	public ModelAndView saveReservation(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Reservation reservation = new Reservation();
		
		String phone = rp.get("pReserTelno").toString();
		String time = rp.get("pReserTime").toString();
		String id = createIdentifier(phone, time);
			
		rp.put("pIdentifier", id);
						
		revService.save(rp);
		
		map.put("pIdentifier", id);
		
		/*
		if(reservation !=null)	
			map.put("success", "Success to Registration");
		else 
			map.put("fail", "Fail to Registration");
		*/
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));

		return mv;
	}
	
	@RequestMapping("/rev/available.do")
	public ModelAndView retrieveAvailableSpot(HttpServletRequest req, HttpServletResponse res) throws Exception {

		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Reservation reservation = new Reservation();
		

		HashMap<String, Object> obj =  (HashMap)this.revService.getAvailableStatus();
		
		System.out.println(obj.get("TOTAL_QTY"));
		System.out.println(obj.get("AVABILE_QTY"));
		
		if(obj == null){
			map.put("fail", "obj is null");
		}else{
			System.out.println(obj.get("TOTAL_QTY"));
			System.out.println(obj.get("AVABILE_QTY"));
			
			map.put("TOTAL_QTY", obj.get("TOTAL_QTY"));
			map.put("AVABILE_QTY", obj.get("AVABILE_QTY"));
			
		}
		
		// 대한 - 2016.07.18 13:01 - getAvailableCout() 함수로 받은 Object를 다시 obj에 넣고 그 값을 String 으로 꺼내서 이 값을 mv에 넣어서 전송한다.
			
		
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));
		
		return mv;
		
	}
	
	@RequestMapping("/rev/currentstatus.do")
	public ModelAndView retrieveCurrentStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(rp);
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		/*
		if(SensorController.spot1IrStatus == 0){
			
		}
		*/
		int spot = SensorStatus.getChangedSpotSensor();
		if(spot == 0){
			map.put("result", "fail");
			mv.addObject("map", map);
			mv.addObject("callback", req.getParameter("callback"));
			return mv;
		}else{
			rp.put("pSpotNumber", spot);
			revService.updateSpot(rp);
		}
		
		retMap = (HashMap) revService.getCurrentStatusObject(rp);
		
		System.out.println(retMap.get("P_SPOT_NUMBER"));
		if(retMap.get("P_SPOT_NUMBER") == null){
			System.out.println("==========================");
			map.put("result", "fail");
			mv.addObject("map", map);
		}else{
			mv.addObject("map", retMap);
		}
		
		//mv.addObject(obj.getpSpotNumber());
		//mv.addObject(obj.getpEnterTime());
		mv.addObject("callback", req.getParameter("callback"));
		
		return mv;
	}
	
	//"pIdentifier=3434345ljkjl345"
	
	@RequestMapping("/rev/identify.do")
	public ModelAndView compareIdentifier(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		String id = (String) rp.get("pIdentifier");
	
		LocalDateTime now = LocalDateTime.now();
		
		int year = now.getYear();
		String stryear = Integer.toString(year);
		
		String strmonth = null;
		int month = now.getMonthValue();
		if(month < 10) {
			strmonth = "0" + Integer.toString(month);
		}else
			strmonth = Integer.toString(month);
		
		String strday = null;
		int day = now.getDayOfMonth();
		if(day < 10) {
			strday = "0" + Integer.toString(day);
		}else
			strday = Integer.toString(day);
		
		String strhour = null;
		int hour = now.getHour();
		if(hour < 10) {
			strhour = "0" + Integer.toString(hour);
		}else
			strhour = Integer.toString(hour);
		
		String strminute = null;
		int minute = now.getMinute();
		if(minute < 10) {
			strminute = "0" + Integer.toString(minute);
		}else
			strminute = Integer.toString(minute);
		
		String CurrentTime = year + strmonth + strday + strhour + strminute;
		
		
		retMap = (HashMap)revService.countIdentifierObject(rp);
		
		if(retMap != null){
			
			if(SensorStatus.getEntryGate() == 0 && retMap.get("STATUS").toString().equals("SUCCESS")){
				identification = 1;
				mv.addObject("map", retMap);
			}else{
				retMap.put("STATUS", "FAIL");
				mv.addObject("map", retMap);
			}
		}else{
			map.put("ret", "fail");
			mv.addObject("map", map);
		}

		mv.addObject("callback", req.getParameter("callback"));

		
//		String tmp = id.substring(10, id.length());
		
//		long now = System.currentTimeMillis();
//		Date date = new Date(now);
//		
//	/*	System.out.println(date.getHours() +","+date.getDate() +","+ date.getMinutes() +","+date.getDate() +","+date.getMonth()+1 +","+date.getTime());
//		System.out.println((int)date.getYear() +1900);
//		*/
//		
//		String str = "201607182030";
//		
//		String ymd = tmp.substring(0, 8);
//		int hour = Integer.parseInt(tmp.substring(8, 10));
//		int min = Integer.parseInt(tmp.substring(10, 12));
//		
//		Date date = new Date(now);
//		
//		System.out.println(date);
//		
//		Time time = new Time(System.currentTimeMillis());
//        Time time2 = new Time(System.currentTimeMillis());
//		
        /*07182000*/
		//		RT3234567ET07182000
		 
//        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
//        String strDT = dayTime.format(new Date(time)); 
//        System.out.println(strDT); 
//		
//		
//		
//		int retrieveResult = (int) revService.countIdentifierObject(userIdentifierNumber);
		
		//대한 - 2016.07.18 12:30 - 사용자로부터 받은 Identifier를 객체로 DAO에 전달 후 받은 카운트 값이 0이면 예약이 진행되지 않은 것 1이면 예약이 제대로 진행된 것, 2이상이면 문제가 있는 것으로 판단하는 로직
		// retrieveResult의 값에 따라서 mv에 담는 value를 다르게 한다.
		
//		if(retrieveResult == 0) {
//			//예약이 정상적으로 진행되지 않았음
//			mv.addObject(retrieveResult);
//		}else if(retrieveResult == 1) {
//			//성공
//			mv.addObject(retrieveResult);
//		}else {
//			//Dave 에게 알람을 준다.
//			mv.addObject(retrieveResult);
//		}
		
		return mv;
	}
	
	public String createIdentifier(String phone, String time){
		String id = null;
		
		StringBuilder sb = new StringBuilder("RT");
		sb.append(phone.substring(3, phone.length()));
		sb.append("ET");
		sb.append(time);
		
		
		id = sb.toString();
		return id; 
	}

}
