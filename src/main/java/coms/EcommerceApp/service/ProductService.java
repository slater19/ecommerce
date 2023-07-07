package coms.EcommerceApp.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.Product;
import coms.EcommerceApp.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {

	 @Autowired
	 ProductRepository productRepository;


		
		public Product getProductById(long id) {
			return productRepository.findById(id).get();
		}
		
		
		
		public void updateProduct(Product product) {
			productRepository.save(product);
		}
		

		
		public void deleteProduct(long id) {
			productRepository.deleteById(id);
		}

		
		public List<Product> getAllProducts() {
			List<Product> list=new ArrayList<>();
			productRepository.findAll().forEach(product->list.add(product));
		     return list;
		}
	 
		
		
			
		
	 		
}
