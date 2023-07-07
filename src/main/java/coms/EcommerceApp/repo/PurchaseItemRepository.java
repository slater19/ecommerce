package coms.EcommerceApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

import coms.EcommerceApp.model.PurchaseItem;
@Repository

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long>{ 

	
	

	
	
	
}
