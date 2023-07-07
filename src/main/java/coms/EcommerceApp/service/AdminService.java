package coms.EcommerceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.Admin;
import coms.EcommerceApp.repo.AdminRepository;

import java.util.List;

@Service
public class AdminService {

	 @Autowired
	 AdminRepository adminRepository;

	 public Admin authenticate(String adminId, String pwd) {
			return adminRepository.findByAdminIdAndPwd(adminId, pwd);
	}
		
		public Admin getAdminById(long id) {
			return adminRepository.findById(id).get();
		}		
		
		public void updatePwd(Admin admin) {
			adminRepository.save(admin);
		}
		}



