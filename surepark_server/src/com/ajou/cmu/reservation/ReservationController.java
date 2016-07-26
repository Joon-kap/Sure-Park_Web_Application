package com.ajou.cmu.reservation;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.ajou.cmu.common.Log;
import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;
import com.ajou.cmu.common.WebSocketModule;
import com.ajou.cmu.payment.Payment;
import com.ajou.cmu.sensor.SensorController;
import com.ajou.cmu.sensor.SensorStatus;


@Controller
public class ReservationController {
	
	public static int identification = 0;
	public static int gp = 30;
	public static int spot = 0;
	private String spots[] = {"1","2","3","4"};
	public static int releaseStatus = 0;
	
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
		
		int inSpot = 0;
		
		List<HashMap> spotList = (List<HashMap>) revService.getAvailableSpot();
		if(spotList.isEmpty())
			inSpot = 1;
		else
			inSpot = selectSpot(spotList);
		
		System.out.println("SPOT : " + inSpot);
			
		rp.put("pIdentifier", id);
		rp.put("pSpotNumber", inSpot);
						
		revService.save(rp);
		
		map.put("pIdentifier", id);
		map.put("pSpotNumber", inSpot);
		
		/*
		if(reservation !=null)	
			map.put("success", "Success to Registration");
		else 
			map.put("fail", "Fail to Registration");
		*/
		
		if(WebSocketModule.thisSession != null)
			WebSocketModule.thisSession.getBasicRemote().sendText(Log.SPOT_REFRESH, true);
		
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
		Reservation rev = (Reservation) revService.getObject(rp);
		int revSpot = Integer.parseInt(rev.getpSpotNumber());
		
		
		
		System.out.println("===============" + spot);
		if(spot == 0){
			map.put("result", "fail");
			mv.addObject("map", map);
			mv.addObject("callback", req.getParameter("callback"));
			return mv;
		}else if(SensorStatus.getExitGate(1) == 0){
			map.put("result", "fail");
			map.put("EXIT_GATE", "Detected");
			mv.addObject("map", map);
			
			mv.addObject("callback", req.getParameter("callback"));
			return mv;
		}else{
			String ctime = getCurrentTime();
			/*
			long now = System.currentTimeMillis();
			Date date = new Date(now);
			System.out.println((int)date.getYear() +1900+ "," + ((int)date.getMonth()+1) +","+date.getDate() +","+ date.getHours()+","+ date.getMinutes());
			String ctime = ""+((int)date.getYear() +1900);
			
			int month = (int)date.getMonth()+1;
			if(month <10)
				ctime += "0";
			ctime += month;
			
			int day = date.getDate();
			if(day <10)
				ctime += "0";
			ctime += day;
			
			int hour = (int)date.getHours();
			if(hour <10)
				ctime += "0";
			ctime += hour;
			
			int min = (int)date.getMinutes();
			if(min <10)
				ctime += "0";
			ctime += min;
			
			System.out.println(ctime);
			*/
			
			rp.put("cTime", ctime);
			rp.put("pSpotNumber", spot);
			
			if(spot != revSpot){
				/*reallocation*/
				System.out.println("============REALLOCATION=============");
				System.out.println("============REALLOCATION=============");
				System.out.println("============REALLOCATION=============");
				System.out.println("============REALLOCATION=============");
				revService.reallocation(spot,revSpot);
				if(WebSocketModule.thisSession != null)
					WebSocketModule.thisSession.getBasicRemote().sendText(Log.REALLOCATION, true);
			}
			
			revService.updateSpot(rp);
			if(WebSocketModule.thisSession != null)
				WebSocketModule.thisSession.getBasicRemote().sendText(Log.SPOT_REFRESH, true);
			
			if(WebSocketModule.thisSession != null)
				WebSocketModule.thisSession.getBasicRemote().sendText(Log.PARKED+"#"+spot, true);
		}
		
		retMap = (HashMap) revService.getCurrentStatusObject(rp);
		
		System.out.println(retMap.get("P_SPOT_NUMBER"));
		if(retMap.get("P_SPOT_NUMBER") == null){
			System.out.println("==========================");
			map.put("result", "fail");
			mv.addObject("map", map);
		}else{
			mv.addObject("map", retMap);
			
			releaseStatus = 1;
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
		
		//timeTest(id);
		
		retMap = (HashMap)revService.countIdentifierObject(rp);
		
	//	SensorStatus.setEntryGate(0);
		
		if(retMap != null){
			System.out.println("===========SensorStatus.getEntryGate() : " + SensorStatus.getEntryGate(1));
			System.out.println("===========retMap.get('STATUS').toString() : " + retMap.get("STATUS").toString());
			if(SensorStatus.getEntryGate(1) == 0 && retMap.get("STATUS").toString().equals("SUCCESS")){
				identification = 1;
				spot = Integer.parseInt(retMap.get("SPOT").toString());
				mv.addObject("map", retMap);
				System.out.println("===========SUCCESS======================");
			}else{
				retMap.put("STATUS", "FAIL");
				mv.addObject("map", retMap);
			}
		}else{
			map.put("STATUS", "FAIL");
			mv.addObject("map", map);
		}

		mv.addObject("callback", req.getParameter("callback"));
		return mv;
	}
	
	@RequestMapping("/rev/exitProceed.do")
	public ModelAndView exitProceed(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		//String id = (String) rp.get("pIdentifier");
		
		//timeTest(id);
		
		
		Reservation rev = (Reservation)revService.getObject(rp);
		System.out.println("===============" + rev.getpSpotNumber());
		int spotNum = Integer.parseInt(rev.getpSpotNumber());
	
		
		//SensorStatus.setSpots(1, 3);
		
		int spotStatus = SensorStatus.getSpot(spotNum);

		int pay = 0;
		
		if(spotStatus == 1){
			String cTime = getCurrentTime();
			Payment.setPayment(rev.getpEnterTime(), cTime);
			pay = Payment.getPayment();
			rp.put("pPayment", pay);
			rp.put("pExitTime", cTime);
			revService.setPayNexitTime(rp);
			map.put("STATUS", "SUCCESS");
			map.put("pPayment",pay );
			map.put("pExitTime", cTime);
			if(WebSocketModule.thisSession != null)
				WebSocketModule.thisSession.getBasicRemote().sendText(Log.SPOT_REFRESH, true);
		}else{
			map.put("STATUS", "FAIL");
		}
		
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));
		
		return mv;
	}
	
	
	@RequestMapping("/rev/exitIdentify.do")
	public ModelAndView exitIdentifier(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		//String id = (String) rp.get("pIdentifier");
		
		//timeTest(id);
		
		if(SensorStatus.getExitGate(1) == 1){
			retMap.put("STATUS", "FAIL");
			
			mv.addObject("map", retMap);
			mv.addObject("callback", req.getParameter("callback"));
			System.out.println("SensorStatus.getExitGate(1) == 0");
			return mv;
		}
		
		
		Reservation rev = (Reservation) revService.getObject(rp);
		if(rev == null){
			retMap.put("STATUS", "FAIL");
			
			mv.addObject("map", retMap);
			mv.addObject("callback", req.getParameter("callback"));
			System.out.println("rev == null");
			return mv;
		}
		
		boolean isPaid = Payment.doPayment(rev.getpPayment(),rp.get("pCredit"));
		
		if(isPaid){
			System.out.println("===========SensorStatus.getEntryGate() : " + SensorStatus.getExitGate(1));
			identification = 1;
			rp.put("pPaymentYn", "Y");
			revService.updatePayYn(rp);
			
			retMap.put("STATUS", "SUCCESS");
			mv.addObject("map", retMap);
			
			if(WebSocketModule.thisSession != null){
				WebSocketModule.thisSession.getBasicRemote().sendText(Log.SPOT_REFRESH, true);
				System.out.println("===========Log.SPOT_REFRESH=============");
				System.out.println("===========Log.SPOT_REFRESH=============");
				System.out.println("===========Log.SPOT_REFRESH=============");
				System.out.println("===========Log.SPOT_REFRESH=============");
				System.out.println("===========Log.SPOT_REFRESH=============");
				System.out.println("===========Log.SPOT_REFRESH=============");
			}
			
			if(WebSocketModule.thisSession != null)
				WebSocketModule.thisSession.getBasicRemote().sendText(Log.EXITED+"#"+rev.getpSpotNumber(), true);
			
		}else{
			retMap.put("STATUS", "FAIL");
			mv.addObject("map", retMap);
			System.out.println("isPaid");
		}
		
		mv.addObject("callback", req.getParameter("callback"));
		return mv;
	}
	
	@RequestMapping("/rev/updateGP.do")
	public ModelAndView updateGracePeriod(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		List<RequestParameter> list = (List<RequestParameter>)revService.getListGp();
		
		int cTime = Integer.parseInt(getCurrentTime().substring(4, 12));
		
		
		for(int i=0; i<list.size(); i++){
			int rTime = Integer.parseInt((list.get(i).get("P_RESER_TIME").toString()).substring(4, 12));
			System.out.println("cTime : " + cTime);
			System.out.println("rTime : " + rTime);
			if((rTime + gp) < cTime){
				rp.put("pCancelYn", "Y");
				rp.put("pIdentifier", list.get(i).get("P_IDENTIFIER"));
				revService.updateCancelYn(rp);
			}
		}
		
		map.put("STATUS", "SUCCESS");
		
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));
		return mv;
	}
	
	
	@RequestMapping("/rev/currentParkedStatus.do")
	public ModelAndView currentParkedStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);
		ModelAndView mv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		List<RequestParameter> list = (List<RequestParameter>)revService.getListCurrentStatus();
		map.put("LIST", list);
		/*
		for(int i=0; i<list.size(); i++){
			int rTime = Integer.parseInt((list.get(i).get("P_RESER_TIME").toString()).substring(4, 12));
			System.out.println("cTime : " + cTime);
			System.out.println("rTime : " + rTime);
			if((rTime + gp) < cTime){
				rp.put("pCancelYn", "Y");
				rp.put("pIdentifier", list.get(i).get("P_IDENTIFIER"));
				revService.updateCancelYn(rp);
			}
		}
		map.put("STATUS", "SUCCESS");
		
		*/
		
		mv.addObject("map", map);
		mv.addObject("callback", req.getParameter("callback"));
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

	
	
	public boolean checkReservationTime(String id){
		
		
		
		//RT4567890ET201607201200
	    //yyyy-MM-dd HH:mm:ss
	    //11~
		
		String idstr = id;
		String convstr = null;
		
		if(idstr.length()>22) {
			convstr = String.format("%c%c%c%c-%c%c-%c%c %c%c:%c%c:00", 
	                        idstr.charAt(11),idstr.charAt(12),idstr.charAt(13),idstr.charAt(14),   //YYYY
	                                             idstr.charAt(15),idstr.charAt(16),      //MM
	                                             idstr.charAt(17),idstr.charAt(18),      //DD
	                                             idstr.charAt(19),idstr.charAt(20),      //HH
	                                             idstr.charAt(11),idstr.charAt(22));      //MM
	        System.out.println(convstr);
	    }
		
		DateFormat iddf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	    Date iddate = null;
	    
	    try {
	    	iddate = iddf.parse(convstr);
	    	
	    } catch (ParseException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	    }
	    
	    long dateInLong = iddate.getTime();
	    long today = System.currentTimeMillis();
	    int diffmin = (int) ((today - dateInLong)/1000/60);
	    if(diffmin > gp)
	    	return false;
	    else
	    	return true;
	}
	
	/*return : 201607212127*/
	public String getCurrentTime(){
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		System.out.println((int)date.getYear() +1900+ "," + ((int)date.getMonth()+1) +","+date.getDate() +","+ date.getHours()+","+ date.getMinutes());
		String ctime = ""+((int)date.getYear() +1900);
		
		int month = (int)date.getMonth()+1;
		if(month <10)
			ctime += "0";
		ctime += month;
		
		int day = date.getDate();
		if(day <10)
			ctime += "0";
		ctime += day;
		
		int hour = (int)date.getHours();
		if(hour <10)
			ctime += "0";
		ctime += hour;
		
		int min = (int)date.getMinutes();
		if(min <10)
			ctime += "0";
		ctime += min;
		
		System.out.println(ctime);
		return ctime;
	}
	
	private int selectSpot(List<HashMap> spotList) {
		String tmpSpotList[] = new String[spots.length];
		
		for(int i=0; i<spots.length; i++){
			tmpSpotList[i] = spots[i];
		}

		
		int retSpot = 0;
		for(int i=0; i<tmpSpotList.length; i++){
			for(int j=0; j<spotList.size(); j++){
				if(tmpSpotList[i].equals(spotList.get(j).get("P_SPOT_NUMBER"))){
					tmpSpotList[i] = "OCC";
				}
			}

		}
		
		
		
		System.out.println("=========" + spotList.get(0).get("P_SPOT_NUMBER"));
//		System.out.println("=========" + spotList.get(0).toString());
		
		for(int i=0; i<tmpSpotList.length; i++){
			System.out.println("=========" + tmpSpotList[i]);

		}
		
		int k=0;
		for(k=0; k<tmpSpotList.length; k++){
			System.out.println(tmpSpotList[k]);
			if(!tmpSpotList[k].equals("OCC"))
				break;
		}
		
		retSpot = k+1;
		
		/*
		if(k == tmpSpotList.length)
			return 0;
*/
		return retSpot;
	}
}
