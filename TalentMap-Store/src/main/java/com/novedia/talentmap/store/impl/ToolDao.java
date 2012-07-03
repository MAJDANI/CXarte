package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IToolDao;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ToolDao implements IToolDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	/**
	 * Builder of a dummy Tool if the database is down
	 * @class ToolDao.java
	 * @param id
	 * @param name
	 * @param concept_id
	 * @return
	 */
	private Tool buildDummyTool(int id, String name, int concept_id){
		
		Tool t = new Tool();
		t.setId(id);
		t.setConcept_id(concept_id);
		t.setName(name);
		
		return t;
	}

	/**
	 * Get One Tool By Id
	 */
	@Override
	public Tool getById(int id){
		
		try {

			return (Tool)sqlMapClient.queryForObject("tool.getToolById",id);
			
//			return buildDummyTool(id, "Spring", 1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildDummyTool(id, "Spring", 1);
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyTool(id, "Spring", 1);
		}
	}
	
	/**
	 * Get One Tool By Name
	 */
	public Tool getByName(String name) {
		
		try {
			
			return (Tool) sqlMapClient.queryForObject("tool.getToolByName", name);
			
//			return buildDummyTool(1, name, 1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return buildDummyTool(1, name, 1);
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyTool(1, name, 1);
		}
	}
	
	/**
	 * Select All Tools 
	 */
	@Override
	public List<Tool> selectAll() {
		
		try {
			
			return sqlMapClient.queryForList("tool.getAllTool");
//			return buildListDummyTool(1);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildListDummyTool(1);
			
		} catch(NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildListDummyTool(1);
		}
	}
	
	/**
	 * Builder of a list of dummy Tool if the database is down
	 * @class ToolDao.java
	 * @param concept_id
	 * @return
	 */
	private List<Tool> buildListDummyTool(int concept_id){
		
		List<Tool> lTool = new ArrayList<Tool>();
		
		Tool t1 = buildDummyTool(1, "Spring", concept_id);
		Tool t2 = buildDummyTool(2, "tool2", concept_id);
		
		lTool.add(t1);
		lTool.add(t2);
		
		return lTool;
	}
	
	/**
	 * Select All Tools By Concept_ID
	 */
	public List<Tool> selectAllByConceptId(int conceptId) {
		
		try {
			
			return sqlMapClient.queryForList("tool.getAllToolByConceptId", conceptId);
//			return buildListDummyTool(conceptId);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database down !");
			
			return buildListDummyTool(conceptId);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildListDummyTool(conceptId);
		}
	}

	@Override
	public int save(Tool tool) throws Exception {
		
		this.sqlMapClient.startTransaction();
		
		int tool_id = (Integer) this.sqlMapClient.insert("tool.insertTool", tool);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return tool_id;
	}

	@Override
	public Tool checkTool(String name) throws Exception {
		
		return (Tool) this.sqlMapClient.queryForObject("tool.checkTool", name);
	}

	@Override
	public int update(Tool tool) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int tool_id = (Integer) this.sqlMapClient.update("tool.updateTool", tool);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return tool_id;
	}

	@Override
	public int delete(int tool_id) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int id = (Integer) this.sqlMapClient.delete("tool.deleteTool", tool_id);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return id;
	}

}
