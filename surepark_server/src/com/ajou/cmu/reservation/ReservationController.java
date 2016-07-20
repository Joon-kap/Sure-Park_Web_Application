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
		
//		int num = Integer.parseInt((String) rp.get("testValue"));
		String str = (String)rp.get("SENSORUPDATE");
		
//		System.out.println("num : "+ num);
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
		
		// ´ëÇÑ - 2016.07.18 13:01 - getAvailableCout() ÇÔ¼ö·Î ¹ÞÀº Object¸¦ ´Ù½Ã obj¿¡ ³Ö°í ±× °ªÀ» String À¸·Î ²¨³»¼­ ÀÌ °ªÀ» mv¿¡ ³Ö¾î¼­ Àü¼ÛÇÑ´Ù.
			
		
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));
		
		return mv;
		
	}
	
	@RequestMapping("/rev/currentstatus.do")
	public ModelAndView retrieveCurrentStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		
		Reservation obj = (Reservation) revService.getCurrentStatusObject(rp);
		
		mv.addObject(obj.getpSpotNumber());
		mv.addObject(obj.getpEnterTime());
		
		return mv;
	}
	
	//"pIdentifier=3434345ljkjl345"
	
	@RequestMapping("/rev/identify.do")
	public ModelAndView compareIdentifier(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
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
		
		//´ëÇÑ - 2016.07.18 12:30 - »ç¿ëÀÚ·ÎºÎÅÍ ¹ÞÀº Identifier¸¦ °´Ã¼·Î DAO¿¡ Àü´Þ ÈÄ ¹ÞÀº Ä«¿îÆ® °ªÀÌ 0ÀÌ¸é ¿¹¾àÀÌ ÁøÇàµÇÁö ¾ÊÀº °Í 1ÀÌ¸é ¿¹¾àÀÌ Á¦´ë·Î ÁøÇàµÈ °Í, 2ÀÌ»óÀÌ¸é ¹®Á¦°¡ ÀÖ´Â °ÍÀ¸·Î ÆÇ´ÜÇÏ´Â ·ÎÁ÷
		// retrieveResultÀÇ °ª¿¡ µû¶ó¼­ mv¿¡ ´ã´Â value¸¦ ´Ù¸£°Ô ÇÑ´Ù.
		
//		if(retrieveResult == 0) {
//			//¿¹¾àÀÌ Á¤»óÀûÀ¸·Î ÁøÇàµÇÁö ¾Ê¾ÒÀ½
//			mv.addObject(retrieveResult);
//		}else if(retrieveResult == 1) {
//			//¼º°ø
//			mv.addObject(retrieveResult);
//		}else {
//			//Dave ¿¡°Ô ¾Ë¶÷À» ÁØ´Ù.
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
