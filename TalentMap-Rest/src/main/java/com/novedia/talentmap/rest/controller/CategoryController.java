package com.novedia.talentmap.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.JsonException;
import com.novedia.talentmap.rest.exception.IBadRequestException;
import com.novedia.talentmap.rest.exception.TalentMapRestHandlerException;
import com.novedia.talentmap.rest.utiils.ConstantsValue;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
public class CategoryController extends TalentMapRestHandlerException implements IBadRequestException {
	
	@Autowired
	IAdminService adminService;
	
	/**
	 * Get all categories
	 * @return categories
	 */
	@RequestMapping(value = "/categories/", method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getAll() {
		List<Category> categories = adminService.getAllCategories();
		return categories;
	}
	
	/**
	 * Add a category
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
	 * Update a category
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
	 * Delete a category
	 * @param categoryId
	 * @return response
	 */
	@RequestMapping(value = "/category/{categoryId}/", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteCategory(@PathVariable Integer categoryId) {
		Response response = new Response();
		response.setMessage(ConstantsValue.UNSUCCESSFUL_DELETE_MSG);
		try {
			Map<String, Object> result = adminService.deleteCategory(categoryId);
			
			if(result.get(Constants.MSG_ERROR).equals((String)Constants.SUCCESSFUL_DELETE)){
				response.setMessage(ConstantsValue.SUCCESSFUL_DELETE_MSG);
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	
	@Override
	@ExceptionHandler({TypeMismatchException.class})
	public JsonException handlerBadRequestException(Exception ex) {
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.BAD_REQUEST.value());
		jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase() + " : " +ex.getMessage());
		return jsonError;
	}

	
}
