package com.ajou.cmu.attandent;

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
public class AttendantController {
	
	@Resource(name = "attService")
	private AttendantServiceImpl atService;
	
		
	@RequestMapping("/att/test.do")
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(rp);
		
		List<HashMap> list = atService.getList(rp);
		map.put("TEST", list.get(0));

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}


	@RequestMapping("/attendant/selectAllConfigInfo.do")
	public ModelAndView updatefacil(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
	
		
		
		List<HashMap> list = atService.selectAllConfigInfo(rp);
		
		map.put("GETCONF",list.get(0));
		//atService.selectAllConfigInfo();
		//System.out.println(rp);

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}
}
