package com.ajou.cmu.reservation;

import java.io.IOException;
import java.util.ArrayList;
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
		
		reservation.setpReserId(Integer.parseInt(rp.get("reservationID").toString()));
		reservation.setpIdentifier(rp.get("Identifier").toString());
		reservation.setpReserTelno(rp.get("TelephoneNumber").toString());
		reservation.setpReserTime(rp.get("ReservationTime").toString());
		
		reservation.setpCancelYn(rp.get("ReservationCancelYN").toString());
		
		reservation.setpCreateDt(rp.get("ReservationCreateTime").toString());
		reservation.setpUpdateDt(rp.get("ReservationUpdateTime").toString());
				
		revService.save(reservation);
		
		if(reservation !=null)	map.put("success", "Success to Registration");
		else map.put("fail", "Fail to Registration");
		
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
		
//		ArrayList<Reservation> reservationList = (ArrayList<Reservation>) this.revService.getList(rp);
		//Object obj = this.revService.getAvailableCout();
		
		Reservation obj = (Reservation) this.revService.getAvailableCout();
		
		// ´ëÇÑ - 2016.07.18 13:01 - getAvailableCout() ÇÔ¼ö·Î ¹ÞÀº Object¸¦ ´Ù½Ã obj¿¡ ³Ö°í ±× °ªÀ» String À¸·Î ²¨³»¼­ ÀÌ °ªÀ» mv¿¡ ³Ö¾î¼­ Àü¼ÛÇÑ´Ù.
		
		String AvailableParkingSpot = obj.toString();
		System.out.println(AvailableParkingSpot);
		
		mv.addObject(AvailableParkingSpot);
		
		
		//System.out.println(reservationList);
		/*
		if(reservationList == null){
			map.put("fail", "there is no available space for parking");
			mv.addObject("1", map);
			return mv;
		}
		
		if(reservationList.size() == 0) {
			map.put("fail", "there is no available space for parking");
		}else {
			map.put("success", reservationList);
		}
		*/
		return mv;
		
	}
	
	@RequestMapping("/rev/currentstatus.do")
	public ModelAndView retrieveCurrentStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		
		Reservation obj = (Reservation) revService.getCurrentStatusObject(rp);
		
		mv.addObject(obj.getpReserId());
		mv.addObject(obj.getpSpotNumber());
		mv.addObject(obj.getpPayment());
		mv.addObject(obj.getpPaymentYn());
		mv.addObject(obj.getpCancelYn());
		mv.addObject(obj.getpEnterTime());
		mv.addObject(obj.getpExitTime());
		
		return mv;
	}
	
	//"pIdentifier=3434345ljkjl345"
	
	@RequestMapping("/rev/identify.do")
	public ModelAndView compareIdentifier(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		String userIdentifierNumber = (String) rp.get("pIdentifier");
		
		int retrieveResult = (int) revService.countIdentifierObject(userIdentifierNumber);
		
		//´ëÇÑ - 2016.07.18 12:30 - »ç¿ëÀÚ·ÎºÎÅÍ ¹ÞÀº Identifier¸¦ °´Ã¼·Î DAO¿¡ Àü´Þ ÈÄ ¹ÞÀº Ä«¿îÆ® °ªÀÌ 0ÀÌ¸é ¿¹¾àÀÌ ÁøÇàµÇÁö ¾ÊÀº °Í 1ÀÌ¸é ¿¹¾àÀÌ Á¦´ë·Î ÁøÇàµÈ °Í, 2ÀÌ»óÀÌ¸é ¹®Á¦°¡ ÀÖ´Â °ÍÀ¸·Î ÆÇ´ÜÇÏ´Â ·ÎÁ÷
		// retrieveResultÀÇ °ª¿¡ µû¶ó¼­ mv¿¡ ´ã´Â value¸¦ ´Ù¸£°Ô ÇÑ´Ù.
		
		if(retrieveResult == 0) {
			//¿¹¾àÀÌ Á¤»óÀûÀ¸·Î ÁøÇàµÇÁö ¾Ê¾ÒÀ½
			mv.addObject(retrieveResult);
		}else if(retrieveResult == 1) {
			//¼º°ø
			mv.addObject(retrieveResult);
		}else {
			//Dave ¿¡°Ô ¾Ë¶÷À» ÁØ´Ù.
			mv.addObject(retrieveResult);
		}
		
		return mv;
	}

}
