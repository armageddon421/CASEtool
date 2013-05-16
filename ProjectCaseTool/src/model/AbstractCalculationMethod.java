package model;

import java.util.ArrayList;

public abstract class AbstractCalculationMethod implements IFieldable {

	@Override
	public abstract ArrayList<Field> getFields();

	public abstract ArrayList<Field> calculate(Project project);

}
