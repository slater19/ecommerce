package coms.EcommerceApp.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.EcommerceApp.model.Category;
import coms.EcommerceApp.repo.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

	 @Autowired
	 CategoryRepository categoryRepository;

	 
		public Category getCategoryById(long id) {
		 	return categoryRepository.findById(id).get();
		}
		
		
	 
	 public void updateCategory(Category category) {
		 categoryRepository.save(category);
		}
		

	 
	 public void deleteCategory(long id) {
		 categoryRepository.deleteById(id);
		}

	 
	 public List<Category> getAllCategories() {
		 List<Category> list=new ArrayList<>();
		 categoryRepository.findAll().forEach(category->list.add(category));
		     return list;
		}
		
	 
	 public String getCategoriesDropDown(long id) {
		 StringBuilder sb = new StringBuilder("");
		 List<Category> list = getAllCategories();
		 for(Category cat: list) {
			 if (cat.getID() == id)
				 sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
			 else
				 sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
				 
		 }
		 return sb.toString();
		}
		

		 
}



