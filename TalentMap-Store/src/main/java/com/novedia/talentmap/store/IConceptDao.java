package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Concept;

public interface IConceptDao {
	
	Concept getById(int id) throws Exception;
	
	List<Concept> selectAll() throws Exception;
}
