package coms.EcommerceApp.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import coms.EcommerceApp.model.Category;
import coms.EcommerceApp.model.Product;
import coms.EcommerceApp.service.CategoryService;
import coms.EcommerceApp.service.ProductService;


@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ProductService productService; 
	
	  @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	    public ModelAndView home() 
	    {
		    
			List<Product> list = productService.getAllProducts();
			
			// use MAP to map the category names to product rows
			 HashMap<Long, String> mapCats = new HashMap<Long, String>();
			  for(Product product: list) {
				  Category category = categoryService.getCategoryById(product.getCategoryId());
				  if (category != null)
					  mapCats.put(product.getID(), category.getName());
			  }
			ModelAndView mv=new ModelAndView();
			
			mv.addObject("list", list);
			mv.addObject("mapCats", mapCats);
		    mv.addObject("pageTitle", "SPORTY SHOES - HOMEPAGE"); 
	        mv.setViewName("index1");
		    return mv; 
		    
	    }	
	  
	  @GetMapping("/test")
	  public String testpage()
	  {
		 return "test"; 
	  }
}
