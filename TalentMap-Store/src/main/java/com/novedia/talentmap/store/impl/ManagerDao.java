package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IGenericDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

public class ManagerDao  extends SqlMapClientDaoSupport implements IGenericDao<Manager>{
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ManagerDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Builder of a dummy Manager if the database is down
	 * @class ManagerDao.java
	 * @param id
	 * @return
	 */
	private Manager buildDummyManager(int id){
		
		Manager m = new Manager();
		m.setId(id);
		m.setBusiness_engineer("Ingénieur d'affaire");
		m.setEmail("consultant.mamanger@noveadi-solutions.com");
		m.setEmployment_date(new Date());
		m.setExperience(10);
		m.setFirst_name("Consultant");
		m.setLast_name("Manager");
		m.setManager_id(id);
		m.setPhone(0000000000);
		m.setProfile_id(1);
		
		return m;
	}

	/**
	 * Get one Manager By ID
	 */
	@Override
	public Manager getById(int id) throws DataAccessException{
		
		try {
			
			return (Manager) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_MANAGER, id);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyManager(id);
		}
	}

//	/**
//	 * Select all Collaborators by managerId
//	 * @param managerId : the id of the manager
//	 * @return all collaborators who depend on the manager managerId
//	 * @author v.guillemain
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId) throws DataAccessException {
//		
//		return sqlMapClient.queryForList("manager.getAllCollaboratorsByManagerId", managerId);
//		
//	}

	@Override
	public List<Manager> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Manager object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Manager checkCategory(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Manager object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int object_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Manager> getAllCollaboratorsByLastName(String lastName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> getAllCollaboratorsByListId(List<Integer> listId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> selectAllByCategoryId(int categoryId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager checkConcept(String name, int category_id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> getAllCollaboratorsByManagerId(Integer managerId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Manager object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Manager> getByCollabId(int collabId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Manager object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Manager getByType(String type) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> selectAllByConceptId(int conceptId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tool checkTool(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}