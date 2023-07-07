package coms.EcommerceApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

import coms.EcommerceApp.model.Purchase;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	

	List<Purchase> findAllByUserId(long userId);

}
