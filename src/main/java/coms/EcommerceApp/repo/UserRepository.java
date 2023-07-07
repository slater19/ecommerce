package coms.EcommerceApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.EcommerceApp.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
		public Optional<User> findByEmailId(String emailId);
		public User findByEmailIdAndPwd(String emailId, String pwd);		
}

