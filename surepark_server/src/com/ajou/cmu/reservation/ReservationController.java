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
		
		// 대한 - 2016.07.18 13:01 - getAvailableCout() 함수로 받은 Object를 다시 obj에 넣고 그 값을 String 으로 꺼내서 이 값을 mv에 넣어서 전송한다.
		
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
		String userIdentifierNumber = (String) rp.get("pIdentifier");
		
		int retrieveResult = (int) revService.countIdentifierObject(userIdentifierNumber);
		
		//대한 - 2016.07.18 12:30 - 사용자로부터 받은 Identifier를 객체로 DAO에 전달 후 받은 카운트 값이 0이면 예약이 진행되지 않은 것 1이면 예약이 제대로 진행된 것, 2이상이면 문제가 있는 것으로 판단하는 로직
		// retrieveResult의 값에 따라서 mv에 담는 value를 다르게 한다.
		
		if(retrieveResult == 0) {
			//예약이 정상적으로 진행되지 않았음
			mv.addObject(retrieveResult);
		}else if(retrieveResult == 1) {
			//성공
			mv.addObject(retrieveResult);
		}else {
			//Dave 에게 알람을 준다.
			mv.addObject(retrieveResult);
		}
		
		return mv;
	}

}
