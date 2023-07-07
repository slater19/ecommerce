package coms.EcommerceApp.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.EcommerceApp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByAdminIdAndPwd(String adminId, String pwd);
	
	
	

}
