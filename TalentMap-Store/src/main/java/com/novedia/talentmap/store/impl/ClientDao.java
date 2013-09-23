package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The Colleague DAO.
 * 
 * @author j.collet
 */
public class ClientDao extends SqlMapClientDaoSupport implements IDao<Client> {

	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(ClientDao.class);

	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public ClientDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Client get(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_CLIENTS);
	}

	@Override
	public int save(Client element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Client element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Client element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client check(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}