package com.novedia.talentmap.store.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Skill;


/**
 * Test SkillDao
 * @author moumbe
 *
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class SkillDaoTest {

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
	 * outils
	 */
	@Test
	@DataSet("SkillDaoTest.xml")
	public void testGetAllSkillsByCollaborator() {
		Colleague colleague = Colleague.builder().id(1).build();
		List<Skill> skills = skillDao.getAllCollaboratorSkill(colleague.getId());
        Assert.assertNotNull(skills);
	}
}
