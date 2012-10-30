package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IToolDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ToolDao extends SqlMapClientDaoSupport implements IToolDao {
	
	private SqlMapClient sqlMapClient;
	private int tool_id; 
	private int id ;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void ToolDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
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
	public Tool getById(int id) throws SQLException{
		
		try {

			return (Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_TOOL_BY_ID_REQUEST,id);

		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyTool(id, "Spring", 1);
		}
	}
	
	/**
	 * Get One Tool By Name
	 */
	public Tool getByName(String name) throws SQLException {
		
		try {
			
			//return (Tool) sqlMapClient.queryForObject("tool.getToolByName", name);
			return(Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_TOOL_BY_NAME_REQUEST,name);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyTool(1, name, 1);
		}
	}
	
	/**
	 * Select All Tools 
	 */
	@Override
	public List<Tool> selectAll() throws SQLException {
		
		try {		
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_TOOL_REQUEST);			
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
	public List<Tool> selectAllByConceptId(int conceptId) throws SQLException {
		
		try {

			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_TOOL_BYCONCEPTID_REQUEST,conceptId);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildListDummyTool(conceptId);
		}
	}

	@Override
	public int save(Tool tool) throws Exception {
		
		this.sqlMapClient.startTransaction();
		
		tool_id = (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.TOOL_INSERT_REQUEST, tool);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return tool_id;
	}

	@Override
	public Tool checkTool(String name) throws Exception {
		
		return (Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.TOOL_REQUEST_CHECK, name);
	}

	@Override
	public int update(Tool tool) throws Exception {
		
		this.sqlMapClient.startTransaction();
		 tool_id = (Integer) this.getSqlMapClientTemplate().update(DBRequestsConstants.TOOL_UPDATE_REQUEST, tool);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return tool_id;
	}

	@Override
	public int delete(int tool_id) throws Exception {
		
		this.sqlMapClient.startTransaction();
		id = (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.TOOL_DELETE_REQUEST, tool_id);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return id;
	}
}