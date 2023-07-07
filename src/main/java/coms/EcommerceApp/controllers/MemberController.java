package coms.EcommerceApp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import coms.EcommerceApp.model.User;
import coms.EcommerceApp.service.UserService;

@Controller
public class MemberController {

	@Autowired
	private UserService userService; 
	
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView login() 
	    {
		  ModelAndView mv=new ModelAndView();
		  mv.addObject("pageTitle", "SPORTY SHOES - MEMBER LOGIN");
		  mv.setViewName("login1");
	        return mv; 
	    }	
	  
	  @RequestMapping(value = "/loginaction", method = RequestMethod.POST)
	    public ModelAndView loginAction(HttpSession session,
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd) 
	    {
		  ModelAndView mv=new ModelAndView();
		  User user = userService.authenticate(emailId, pwd);
		  if (user == null) { 
			  
			  mv.addObject("error", "Login failed");
			  mv.setViewName("register1");
			  return mv;
		  }
		  
		  session.setAttribute("user_id", user.getID());
		  mv.setViewName("redirect:/dashboard");
		  return mv; 
	    }		  
	  
	  	  
	  @RequestMapping(value = "/signup", method = RequestMethod.GET)
	    public ModelAndView signup() 
	    {
		  ModelAndView mv=new ModelAndView();
		  mv.addObject("pageTitle", "SPORTY SHOES - MEMBER REGISTRATION");
	        
	        mv.setViewName("register1");
	        return mv;
	    }	
	  
	  @RequestMapping(value = "/signupaction", method = RequestMethod.POST)
	    public ModelAndView signupAction(  HttpSession session,
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="fname", required=true) String fname,
	    		 @RequestParam(value="lname", required=true) String lname,
	    		 @RequestParam(value="age", required=true) String age,
	    		 @RequestParam(value="address", required=true) String address) 
	    {
		  ModelAndView mv=new ModelAndView();

		  if (emailId == null || emailId.equals("")) {
			  mv.addObject("error", "Email id is required.");
			  mv.setViewName("register1");
			  return mv;
		  }

		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  
			  
			  mv.addObject("error", "Error , Incomplete passwords submitted.");
			  mv.setViewName("register1");
			  return mv;
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  
			  mv.addObject("error", "Error , Passwords do not match.");
			  mv.setViewName("register1");
			  return mv;
			  
		  }
		  
		  if (fname == null || fname.equals("")) {
			  
			  
			  mv.addObject("error", "Error , First name is required.");
			  mv.setViewName("register1");
			  return mv;
		  }

		  if (lname == null || lname.equals("")) {
			  
			  
			  mv.addObject("error", "Error , Last name is required.");
			  mv.setViewName("register1");
			  return mv;
		  }
		  if (age == null || age.equals("")) {
			  age = "0";
		  }
		  
		  
		
		  User user1 = new User();
		  user1.setID(0);
		  user1.setEmailId(emailId);
		  user1.setFname(fname);
		  user1.setLname(lname);
		  user1.setAge(Integer.parseInt(age));
		  user1.setAddress(address);
		  user1.setPwd(pwd);
		  
		  
		  userService.updateUser(user1); 
		  
	
		  
		    mv.setViewName("register-confirm1");
	        return mv; 
	    }
	  
	  
	  @RequestMapping(value = "/registerconfirm", method = RequestMethod.GET)
	    public ModelAndView registerConfirm() 
	    {
		  ModelAndView mv=new ModelAndView();
		  mv.setViewName("register-confirm1");  
		  return mv; 
	    }		 		  
	  @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	    public ModelAndView editProfile(HttpSession session) 
	    {
		  ModelAndView mv=new ModelAndView();
		  
		  if (session.getAttribute("user_id") == null) {
			  mv.setViewName("login1");
			  return mv;
		  }
		  User user = userService.getUserById((Long) session.getAttribute("user_id"));

		  mv.addObject("pageTitle", "SPORTY SHOES - MEMBER EDIT PROFILE");
		  mv.addObject("user", user);
		  mv.setViewName("edit-profile1");
	        return mv; 
	    }		 	  


	  @RequestMapping(value = "/editprofileaction", method = RequestMethod.POST)
	    public ModelAndView  editProfileAction(
	    		HttpSession session, 
	    		@RequestParam(value="user_id", required=true) String userId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="fname", required=true) String fname,
	    		 @RequestParam(value="lname", required=true) String lname,
	    		 @RequestParam(value="age", required=true) String age,
	    		 @RequestParam(value="address", required=true) String address) 
	    {
		  
		  
		  ModelAndView mv=new ModelAndView();
		  if (session.getAttribute("user_id") == null) {
			  mv.setViewName("login1");
			  return mv;
		  }
		  
		  User user = userService.getUserById((Long) session.getAttribute("user_id"));
		  mv.addObject("user", user);
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  mv.addObject("error", "Error , Incomplete passwords submitted.");
			  mv.setViewName("edit-profile1");
			  return mv;
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  mv.addObject("error", "Error , Passwords do not match.");
			  mv.setViewName("edit-profile1");
			  return mv;
		  }
		  
		  if (fname == null || fname.equals("")) {
			  mv.addObject("error", "First name is required.");
			  mv.setViewName("edit-profile1");
			  return mv;
		  }

		  if (lname == null || lname.equals("")) {
			  mv.addObject("error", "Last name is required.");
			  mv.setViewName("edit-profile1");
			  return mv;
		  }
		  if (age == null || age.equals("")) {
			  age = "0";
		  }
		  
		  
		
		  user.setFname(fname);
		  user.setLname(lname);
		  user.setAge(Integer.parseInt(age));
		  user.setAddress(address);
		  user.setPwd(pwd);
		  
		  userService.updateUser(user);
		    mv.setViewName("redirect:/dashboard"); 
	        return mv; 
	    }

	  @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public ModelAndView logout(HttpSession session) 
	    {   
		    ModelAndView mv=new ModelAndView();
		  	
		  	session.invalidate();
		  	mv.setViewName("redirect:/home");   
	        return mv; 
	    }
}