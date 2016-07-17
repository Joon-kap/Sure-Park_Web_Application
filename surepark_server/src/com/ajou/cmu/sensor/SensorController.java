package com.ajou.cmu.sensor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.cmu.reservation.ReservationServiceImpl;

@Controller
public class SensorController {
	
	@Resource(name = "revService")
	private ReservationServiceImpl revService;
	
	@RequestMapping("sensor/changeStatus.do")
	public ModelAndView getChangedSensorInfo() throws Exception {
		ModelAndView mv = new ModelAndView("");
		
		revService.getObject(mv);
		
		return mv;
	}

}
