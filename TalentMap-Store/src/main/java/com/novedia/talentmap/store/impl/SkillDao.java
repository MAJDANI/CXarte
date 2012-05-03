package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.store.ISkillDao;

public class SkillDao implements ISkillDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	

	@Override
	public List<Skill> getAllCollaboratorSkill(int collaborator_id)
			throws Exception {
		
		return sqlMapClient.queryForList("skill.getAllSkill",collaborator_id);
	}

	@Override
	public Skill getOneCollaboratorSkill(int collaborator_id, int tool_id)
			throws Exception {
		
		return (Skill)sqlMapClient.queryForObject("skill.getSkill", collaborator_id, tool_id);
	}

}
