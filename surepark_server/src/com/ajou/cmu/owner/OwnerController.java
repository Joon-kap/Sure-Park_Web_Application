package com.ajou.cmu.owner;

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

import com.ajou.cmu.common.RequestParameter;
import com.ajou.cmu.common.Utils;
import com.ajou.cmu.payment.Payment;
import com.ajou.cmu.sensor.SensorController;
import com.ajou.cmu.sensor.SensorStatus;


@Controller
public class OwnerController {
	
	@Resource(name = "ownerService")
	private OwnerServiceImpl owService;
	
		
	@RequestMapping("/owner/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		owService.update(rp);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/updateConf.do")
	public ModelAndView updateConf(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
	
		System.out.println(rp);
		
		owService.updateConf(rp);
	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	

	
	@RequestMapping("/owner/updatefacil.do")
	public ModelAndView updatefacil(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
	
		System.out.println(rp);
		
		owService.updatefacil(rp);
	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/select.do")
	public ModelAndView select(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		Owner ow = (Owner) owService.getObject(rp);
		
		map.put("object", ow);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	
	@RequestMapping("/owner/getReserInfo.do")
	public ModelAndView getReserInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		List<RequestParameter> list = owService.getList(rp);
		
		
		
		//map.put("object", ow);

		mnv.addObject("map", list);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		Object obj = owService.getObject(rp);
		if(obj != null){
			map.put("STATUS", "SUCCESS");
		}else{
			map.put("STATUS", "FAIL");
		}
		
//		owService.setgp(rp);
		
		//map.put("object", ow);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/setgp.do")
	public ModelAndView setgp(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		owService.setgp(rp);
		
		//map.put("object", ow);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/getConf.do")
	public ModelAndView getConf(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		RequestParameter rp1 = (RequestParameter) owService.getConfObject();
		//owService.setgp(rp);
		
		//map.put("object", ow);

		mnv.addObject("map", rp1);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
	
	@RequestMapping("/owner/setFee.do")
	public ModelAndView setFee(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		int fromStr = Integer.parseInt(rp.get("fromStr").toString());
		int toStr = Integer.parseInt(rp.get("toStr").toString());
		
		
		
		/*
		for(int i=0; i<24; i++){
			if(i>10){
				if(fromStr != 0)
					rp.put("P_F0"+i, fromStr);
				else
					rp.put("P_F0"+i, "0");
			}
			
		}
		*/
		/*
		params+="&P_F00="	+fee25min.value;
		params+="&P_F01="	+fee25min.value;
		params+="&P_F02="	+fee25min.value;
		params+="&P_F03="	+fee25min.value;
		params+="&P_F04="	+fee25min.value;
		params+="&P_F05="	+fee25min.value;
		params+="&P_F06="	+fee25min.value;
		params+="&P_F07="	+fee25min.value;
		params+="&P_F08="	+fee25min.value;
		params+="&P_F09="	+fee25min.value;
		params+="&P_F10="	+fee25min.value;
		params+="&P_F11="	+fee25min.value;
		params+="&P_F12="	+fee25min.value;
		params+="&P_F13="	+fee25min.value;
		params+="&P_F14="	+fee25min.value;
		params+="&P_F15="	+fee25min.value;
		params+="&P_F16="	+fee25min.value;
		params+="&P_F17="	+fee25min.value;
		params+="&P_F18="	+fee25min.value;
		params+="&P_F19="	+fee25min.value;
		params+="&P_F20="	+fee25min.value;
		params+="&P_F21="	+fee25min.value;
		params+="&P_F22="	+fee25min.value;
		params+="&P_F23="	+fee25min.value;
		*/
		
		
		owService.setFee(rp);
		
		//map.put("object", ow);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
		
}
