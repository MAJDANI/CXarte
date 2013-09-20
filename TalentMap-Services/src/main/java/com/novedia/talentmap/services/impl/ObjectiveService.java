package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.services.IObjectiveService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ObjectiveDao;

public class ObjectiveService implements IObjectiveService {
	
	/**
	 * The Objective DAO.
	 */
	private IDao<Objective> objectiveDao;

	@Override
	public Objective getObjectiveById(Integer id) {
		return objectiveDao.get(id);
	}

	@Override
	public int addObjective(Objective objective) {
		return objectiveDao.add(objective);
	}

	@Override
	public int saveObjective(Objective objective) {
		return objectiveDao.save(objective);
	}

	@Override
	public int deleteObjective(Objective objective) {
		return objectiveDao.delete(objective);
	}

	@Override
	public List<Objective> getObjectivesByEAEId(Integer idEAE) {
		ObjectiveDao objDao = (ObjectiveDao)this.objectiveDao;
		return objDao.getObjectivesByEAEId(idEAE);
	}

	public IDao<Objective> getObjectiveDao() {
		return objectiveDao;
	}

	public void setObjectiveDao(IDao<Objective> objectiveDao) {
		this.objectiveDao = objectiveDao;
	}

}
