package coms.EcommerceApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/page")
	public String DemoPages() 
	{
		return "DemoPage";
	}
}
