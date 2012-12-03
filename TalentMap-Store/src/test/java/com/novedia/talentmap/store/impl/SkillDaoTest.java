package com.novedia.talentmap.store.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;


/**
 * Test SkillDao
 * @author moumbe
 *
 */
@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
@SpringApplicationContext("test-store-spring-context.xml")
public class SkillDaoTest extends UnitilsJUnit4 {

	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBean("skillDao")
	private SkillDao skillDao;
	
	@Before
	public void setUp() {
		skillDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test de récupération de toutes les notes attribuées par un collaborateur aux différents
	 * outilss
	 */
	@Test
	public void testGetAllSkillsByCollaborator() {
		Collaborator collab = new Collaborator();
		collab.setId(1);
		List<Skill> skills = skillDao.getAllSkillsByCollaborator(collab);
		assertNotNull(skills);
	}
}
