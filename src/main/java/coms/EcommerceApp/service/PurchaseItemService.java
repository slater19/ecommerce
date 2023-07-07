package coms.EcommerceApp.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.PurchaseItem;
import coms.EcommerceApp.repo.PurchaseItemRepository;

import java.util.List;

@Service
public class PurchaseItemService {

	 @Autowired
	 PurchaseItemRepository purchaseItemRepository;

	
		public PurchaseItem getItemById(long id) {
		 return purchaseItemRepository.findById(id).get();
		
		}
		
	 
		public List<PurchaseItem> getAllItemsByPurchaseId(long purchaseId) {
			List<PurchaseItem> list=new ArrayList<>();
			purchaseItemRepository.findAll().forEach(item->list.add(item));
		     return list;
		}	
		
	 
		public void updateItem(PurchaseItem item) {
			purchaseItemRepository.save(item);
		}
		

	
		public void deleteItem(long id) {
			purchaseItemRepository.deleteById(id);
		}

	 
		public void deleteAllItemsForPurchaseId(long purchaseId) {
			purchaseItemRepository.deleteById(purchaseId);
		}


	 
}