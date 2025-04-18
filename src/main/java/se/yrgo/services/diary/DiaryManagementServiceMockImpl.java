package se.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.yrgo.domain.Action;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	
	private Set<Action>allActions= new HashSet<Action>();

	@Override
	public void recordAction(Action newAction) {
		allActions.add(newAction);

	}
 
	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> result = new ArrayList<>();
		for(Action a : allActions) {
			if(a.getOwningUser().equals(requiredUser) && !a.isComplete()) {
				result.add(a);
			}
		}
		return result;
	}

}
