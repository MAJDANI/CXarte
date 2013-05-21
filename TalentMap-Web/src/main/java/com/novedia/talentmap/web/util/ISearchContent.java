package com.novedia.talentmap.web.util;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;

public interface ISearchContent {

	public void changeSearchResults(List<Colleague> listCollab,
			boolean clearState);
}
