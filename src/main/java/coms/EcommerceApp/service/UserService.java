package coms.EcommerceApp.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.User;
import coms.EcommerceApp.repo.UserRepository;

import java.util.List;

@Service
public class UserService {

	 @Autowired
	 UserRepository userRepository;
	 
	 
	
	 public User authenticate(String emailId, String pwd) {
			return userRepository.findByEmailIdAndPwd(emailId, pwd);
	}
			
	
	
	 
		public User getUserById(long id) {
		 	return userRepository.findById(id).get();
		}
	 
	 
		public User getUserByEmailId(String emailId) {
			return userRepository.findByEmailId(emailId).get();
		}
		
	 
		public void updateUser(User user) {
			userRepository.save(user);			
		}

	 
		public List<User> getAllUsers() {
			 List<User> list=new ArrayList<>();
			 userRepository.findAll().forEach(user->list.add(user));
		     return list;
		}	 
}
