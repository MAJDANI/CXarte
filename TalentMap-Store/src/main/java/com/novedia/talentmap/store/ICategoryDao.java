package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Category;

public interface ICategoryDao {
	
	Category getById(int id) throws Exception;
	
	List<Category> selectAll() throws Exception;
	
}
