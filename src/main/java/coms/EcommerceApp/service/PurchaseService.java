package coms.EcommerceApp.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.Purchase;
import coms.EcommerceApp.repo.PurchaseRepository;

import java.util.List;

@Service
public class PurchaseService {

	 @Autowired
	 PurchaseRepository purchaseRepository;

		
		public Purchase getPurchaseById(long id) {
			return purchaseRepository.findById(id).get();
		}
		
		
		public List<Purchase> getAllItems() {
			List<Purchase> list=new ArrayList<>();
			purchaseRepository.findAll().forEach(purchase->list.add(purchase));
		     return list;
		}			
		
		
		public List<Purchase> getAllItemsByUserId(long userId) {
			
			List<Purchase> list=new ArrayList<>();
			purchaseRepository.findAllByUserId(userId).forEach(purchase->list.add(purchase));
		    return list;
		}	
		
		
		public long  updatePurchase(Purchase purchase) {
			purchaseRepository.saveAndFlush(purchase);
			return purchase.getID();
		}
		

		
		public void deletePurchase(long id) {
			purchaseRepository.deleteById(id);
		}

	 
}
