package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.store.IManagerDao;

public class ManagerDao implements IManagerDao{
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
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
	public Manager getById(int id){
		
		try {
			
			return (Manager) sqlMapClient.queryForObject("manager.getManager", id);
//			return buildDummyManager(id);
			
		} catch (SQLException e) {
		
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildDummyManager(id);
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyManager(id);
		}
	}

	/**
	 * Select all Collaborators by managerId
	 * @param managerId : the id of the manager
	 * @return all collaborators who depend on the manager managerId
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId) throws Exception {
		
		return sqlMapClient.queryForList("manager.getAllCollaboratorsByManagerId", managerId);
		
	}

}
