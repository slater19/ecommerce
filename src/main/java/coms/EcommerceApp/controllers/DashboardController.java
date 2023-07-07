package coms.EcommerceApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

	  @GetMapping("/dashboard")
	    public ModelAndView dashboard() 
	    {
		  ModelAndView mv=new ModelAndView(); 	
		  
		  mv.addObject("pageTitle", "SPORTY SHOES - DASHBOARD");
		  mv.setViewName("dashboard1");
	        return mv;
	    }		  
}
