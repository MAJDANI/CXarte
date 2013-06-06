package com.novedia.talentmap.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.services.IAdminService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
public class CategoryController extends TalentMapRestHandlerException {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * @return categories
	 */
	@RequestMapping(value = "/categories/", method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getAll() {
		List<Category> categories = adminService.getAllCategories();
		return categories;
	}
	
	/**
	 * @param category_name
	 * @return category
	 */
	@RequestMapping(value = "/category/{category_name}/", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(@PathVariable String category_name) {
		Category category = Category.builder().name(category_name).build();
		Integer res = adminService.addCategory(category);
		category.setId(res);
		return category;
	}
	
	/**
	 * @param categoryId
	 * @param category_name
	 * @return category
	 */
	@RequestMapping(value = "/category/{categoryId}/{category_name}/", method = RequestMethod.PUT)
	@ResponseBody
	public Category saveCategory(@PathVariable Integer categoryId,@PathVariable String category_name) {
		Category category = Category.builder().id(categoryId).name(category_name).build();
		adminService.updateASkill(category, null, null);
		return category;
	}
	
	/**
	 * @param categoryId
	 */
	@RequestMapping(value = "/category/{categoryId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCategory(@PathVariable Integer categoryId) {
		try {
			adminService.deleteCategory(categoryId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
