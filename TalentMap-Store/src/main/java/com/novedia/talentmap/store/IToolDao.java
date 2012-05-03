package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Tool;

public interface IToolDao {
	
	Tool getById(int id) throws Exception;
	
	List<Tool> selectAll() throws Exception;
}
