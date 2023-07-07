package coms.EcommerceApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.EcommerceApp.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	

		
}
