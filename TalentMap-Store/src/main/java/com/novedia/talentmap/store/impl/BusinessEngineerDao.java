package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

public class BusinessEngineerDao extends SqlMapClientDaoSupport implements
		IDao<BusinessEngineer> {

	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(BusinessEngineerDao.class);
	
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public BusinessEngineerDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	
	@Override
	public BusinessEngineer get(Integer id) throws DataAccessException {
//		return (BusinessEngineer) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_BUSINESS_ENGINEER_BY_ID);
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<BusinessEngineer> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_BUSINESS_ENGINEER);
	}

	@Override
	public int save(BusinessEngineer element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(BusinessEngineer element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BusinessEngineer element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessEngineer getByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessEngineer check(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
