package coms.EcommerceApp.controllers;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import coms.EcommerceApp.model.Admin;
import coms.EcommerceApp.model.Category;
import coms.EcommerceApp.model.Product;
import coms.EcommerceApp.model.Purchase;
import coms.EcommerceApp.model.PurchaseItem;
import coms.EcommerceApp.model.User;
import coms.EcommerceApp.service.AdminService;
import coms.EcommerceApp.service.CategoryService;
import coms.EcommerceApp.service.ProductService;
import coms.EcommerceApp.service.PurchaseItemService;
import coms.EcommerceApp.service.PurchaseService;
import coms.EcommerceApp.service.UserService;



@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ProductService productService; 

	@Autowired
	private PurchaseService purchaseService; 

	@Autowired
	private PurchaseItemService purchaseItemService; 
	
	@Autowired
	private UserService userService; 
	
	
	  @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	    public ModelAndView login(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  mv.addObject("pageTitle", "ADMIN LOGIN");
		  mv.setViewName("admin/login1");
	        
	        return mv; 
	    }
	  
	  @RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
	    public ModelAndView loginAction(HttpSession session, 
	    		 @RequestParam(value="admin_id", required=true) String adminId,
	    		 @RequestParam(value="admin_pwd", required=true) String adminPwd) 
	    {
		  ModelAndView mv=new ModelAndView();
		  Admin admin = adminService.authenticate(adminId, adminPwd);
		  if (admin == null) { 
			  mv.addObject("error", "Admin login failed");
			  
			  mv.setViewName("admin/login1");
			  return mv; 
		  }
		  // store admin id in session
		  
		  session.setAttribute("admin_id", admin.getID());
		  mv.setViewName("admin/dashboard1");
	        
	        return mv; 
	    }	  
	    
	  @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	    public ModelAndView dashboard(HttpSession session) 
	    {
		  // check if session is still alive
		  ModelAndView mv=new ModelAndView();
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  
			  return mv; 
		  }
		   
		  mv.addObject("pageTitle", "ADMIN DASHBOARD");
		  mv.setViewName("admin/dashboard1");
	        
	        return mv; 
	    }
	  
	   
	  @RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
	    public ModelAndView changePwd(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
			  
		  }
		  
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id"));
		  
		  mv.addObject("admin", admin);
		  mv.addObject("pageTitle", "ADMIN CHANGE PASSWORD");
		  mv.setViewName("admin/change-password1");
	        
		  return mv; 
	    }

	  
	  @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public ModelAndView updatePassword(HttpSession session,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2)
	    		 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  mv.addObject("error", "Error , Incomplete passwords submitted.");
			  mv.setViewName("admin/change-password1");
			  return mv;
		  }
		  if (!pwd.equals(pwd2)) {
			  mv.addObject("error", "Error , Passwords do not match.");
			  mv.setViewName("admin/change-password1");
			  return mv;
		  }
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id")); 
		  admin.setPwd(pwd);
		  adminService.updatePwd(admin);
		  mv.setViewName("admin/dashboard1");
	        return mv;  
	    }		  

	  
	  @RequestMapping(value = "/adminproducts", method = RequestMethod.GET)
	    public ModelAndView products(HttpSession session, javax.servlet.http.HttpServletRequest request) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  List<Product> list = productService.getAllProducts();

		  // use a MAP to link category names to each product in list  
		  HashMap<Long, String> mapCats = new HashMap<Long, String>();
		  
		  for(Product product: list) {
			  Category category = categoryService.getCategoryById(product.getCategoryId());
			  if (category != null)
				  mapCats.put(product.getID(), category.getName());
		  }
		  mv.addObject("list", list);
		  mv.addObject("mapCats", mapCats);

		  mv.addObject("pageTitle", "ADMIN SETUP PRODUCTS");
		  mv.setViewName("admin/products1");
		  return mv;
	    }
	  
	  @RequestMapping(value = "/admincategories", method = RequestMethod.GET)
	    public ModelAndView categories(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  } 
		  
		  List<Category> list = categoryService.getAllCategories();
		  mv.addObject("list", list);
		  mv.addObject("pageTitle", "ADMIN SETUP PRODUCT CATEGORIES");
		  mv.setViewName("admin/categories1");
		  return mv;
	    }
	  
	  
	  @RequestMapping(value = "/adminmembers", method = RequestMethod.GET)
	    public ModelAndView members(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		
		  List<User> list = userService.getAllUsers();
		  
		  mv.addObject("list", list);
		  mv.addObject("pageTitle", "ADMIN BROWSE MEMBERS");
		  mv.setViewName("admin/members1");
		  return mv;
	    }
	  @RequestMapping(value = "/adminpurchases", method = RequestMethod.GET)
	    public ModelAndView purchases(HttpSession session, javax.servlet.http.HttpServletRequest request) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  
		  List<Purchase> list = purchaseService.getAllItems();
		  
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
		  }
		  
		  // use MAPs to mape users to each purchase and item names to each purchase item row
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  HashMap<Long, String> mapUsers = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
			  User user = userService.getUserById(purchase.getUserId());
			  if (user != null)
				  mapUsers.put(purchase.getID(), user.getFname() + " " + user.getLname());
			  
			  List<PurchaseItem> itemList = purchaseItemService.getAllItemsByPurchaseId(purchase.getID());
			  StringBuilder sb = new StringBuilder(""); 
			  for(PurchaseItem item: itemList) {
				  Product product = productService.getProductById(item.getProductId());
				  if (product != null)
					  sb.append(product.getName() + ", " + 
						  	item.getQty() + " units @" + item.getRate() + " = " 
						  	+ item.getPrice() + "<br>");
			  }
			  mapItems.put(purchase.getID(), sb.toString());
		  }		  
		  mv.addObject("totalAmount", total); 
		  mv.addObject("list", list);
		  mv.addObject("mapItems", mapItems);
		  mv.addObject("mapUsers", mapUsers);
		  mv.addObject("pageTitle", "ADMIN PURCHASES REPORT");
		  mv.setViewName("admin/purchases1");
	        return mv;
	    }	  

	  @RequestMapping(value = "/admindeletecat",  method = RequestMethod.GET)
	    public ModelAndView deleteCategory(HttpSession session,  @RequestParam(value="id", required=true) String id
	    		) 
	    {
		  ModelAndView mv=new ModelAndView();
		  // check if session is still alive
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  categoryService.deleteCategory(idValue);
		  }
		  mv.setViewName("redirect:/admincategories");
		  return mv;
		  
	    }	
	  
	  
	  @RequestMapping(value = "/admineditcat",  method = RequestMethod.GET)
	    public ModelAndView editCategory(HttpSession session,  @RequestParam(value="id", required=true) String id
	    		) 
	    {
		  // check if session is still alive
		  ModelAndView mv=new ModelAndView();
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  category = categoryService.getCategoryById(idValue);
		  } else {
			  category.setID(0);
		  }
		  mv.addObject("category", category);
		  mv.addObject("pageTitle", "ADMIN EDIT PRODUCT CATEGORY");
		  mv.setViewName("admin/edit-category1");
		  return mv;
	        
	    }		  

	  
	  @RequestMapping(value = "/admineditcataction", method = RequestMethod.POST)
	    public ModelAndView updateCategory(HttpSession session,  @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name) 
	    		 
	    {
		  long idValue = Long.parseLong(id); 
		  ModelAndView mv=new ModelAndView();
		  if (name == null || name.equals("") ) { 
			  mv.addObject("error", "Error, A category name must be specified");
			  mv.setViewName("redirect:/admineditcat?id="+id);
			  
			  return mv;
		  }
		  	Category category = new Category();
		  	category.setID(idValue); 
		  	category.setName(name);
		  	
		  	categoryService.updateCategory(category); 
		  	mv.setViewName("redirect:/admincategories");
	        
	        return mv;
	    }
	  
	  @RequestMapping(value = "/admindeleteproduct",  method = RequestMethod.GET)
	    public ModelAndView deleteProduct(HttpSession session,  @RequestParam(value="id", required=true) String id
	    		) 
	    {
		  // check if session is still alive
		  ModelAndView mv=new ModelAndView();
		  
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  long idValue = Long.parseLong(id);
		  Product product = new Product();
		  if (idValue > 0) {
			  productService.deleteProduct(idValue);
		  }
		  mv.setViewName("redirect:/adminproducts");
		  
		  return mv;
	    }	
	  

	  
	  @RequestMapping(value = "/admineditproduct",  method = RequestMethod.GET)
	    public ModelAndView editProduct(HttpSession session,  @RequestParam(value="id", required=true) String id
	    		) 
	    {
		  // check if session is still alive
		  ModelAndView mv=new ModelAndView();
		  if (session.getAttribute("admin_id") == null) {
			  mv.setViewName("admin/login1");
			  return mv;
		  }
		  
		  long idValue = Long.parseLong(id);
		  Product product = new Product();
		  if (idValue > 0) {
			  product = productService.getProductById(idValue);
		  } else {
			  product.setID(0);
			  product.setCategoryId(0);
		  }
		  String dropDown = categoryService.getCategoriesDropDown(product.getCategoryId());
		  mv.addObject("product", product);
		  mv.addObject("catDropdown", dropDown);
		  mv.addObject("pageTitle", "ADMIN EDIT PRODUCT");
		  mv.setViewName("admin/edit-product1");
	        return mv; 
	    }		  
	  @RequestMapping(value = "/admineditproductaction", method = RequestMethod.POST)
	    public ModelAndView updateProduct(HttpSession session,
	    		 @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="price", required=true) String price,
	    		 @RequestParam(value="category", required=true) String categoryId) 
	    {
		  ModelAndView mv=new ModelAndView();
		  long idValue = Long.parseLong(id); 
		  long categoryIdValue = Long.parseLong(categoryId);
		  BigDecimal priceValue = new BigDecimal(0.0);
		  
		  if (name == null || name.equals("") ) { 
			  mv.addObject("error", "Error, A product name must be specified");
			  mv.setViewName("redirect:/admineditproduct?id="+id);
			  
			  
			  return mv; 
		  }
		  try {
			  priceValue = new BigDecimal(price);
			  
		  } catch (Exception ex) {
			  mv.addObject("error", "Error, Price is invalid");
			  mv.setViewName("redirect:/admineditproduct?id="+id);
			  
			  
			  return mv; 
		  }
		  
		  	Product product = new Product();
		  	product.setID(idValue); 
		  	product.setCategoryId(Long.parseLong(categoryId));
		  	product.setName(name);
		  	product.setPrice(priceValue);
		  	
		  	productService.updateProduct(product); 
		  	mv.setViewName("redirect:/adminproducts");
		  	return mv; 
	        
	    }	
	   
	  
	  
	  @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String logout(HttpSession session) 
	    {
		  	
		  	session.invalidate();
		  	
	        return "admin/login1"; 
	    }
}