package coms.EcommerceApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import coms.EcommerceApp.model.Product;
import coms.EcommerceApp.model.Purchase;
import coms.EcommerceApp.model.PurchaseItem;
import coms.EcommerceApp.service.ProductService;
import coms.EcommerceApp.service.PurchaseItemService;
import coms.EcommerceApp.service.PurchaseService;



@Controller
public class PurchaseController {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseItemService purchaseItemService;

	  @RequestMapping(value = "/memberpurchases", method = RequestMethod.GET)
	    public ModelAndView memberpurchases(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  
		  if (session.getAttribute("user_id") == null) {
			  mv.setViewName("login1");
			  return mv;
		  }
		  long userId = (Long) session.getAttribute("user_id");
		  
		  List<Purchase> list = purchaseService.getAllItemsByUserId(userId);
		  
		  BigDecimal total = new BigDecimal(0.0);
		  // map purchase items to each purchase for display
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
			  
			  List<PurchaseItem> itemList = purchaseItemService.getAllItemsByPurchaseId(purchase.getID());
			  StringBuilder sb = new StringBuilder("");
			  for(PurchaseItem item: itemList) {
				  Product product = productService.getProductById(item.getProductId());
				  if (product != null) {
					  sb.append(product.getName() + ", " + 
						  	item.getQty() + " units @" + item.getRate() + " = " 
						  	+ item.getPrice() + "<br>");
				  }
			  }
			  mapItems.put(purchase.getID(), sb.toString());
		  }
		  
		  mv.addObject("totalAmount", total);
		  mv.addObject("list", list);
		  mv.addObject("mapItems", mapItems);
		  mv.addObject("pageTitle", "SPORTY SHOES - YOUR ORDERS");
		  mv.setViewName("purchases1");
	      return mv; 
	    }		  
}
