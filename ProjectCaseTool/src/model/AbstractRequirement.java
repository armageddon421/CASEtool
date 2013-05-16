package model;

import java.util.ArrayList;

public abstract class AbstractRequirement implements IFieldable {
	protected ArrayList<Field> requirementFields = new ArrayList<>();
	
	@Override
	public ArrayList<Field> getFields() {
		return requirementFields;
	}

}
