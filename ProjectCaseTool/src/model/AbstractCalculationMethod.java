package model;

import java.util.ArrayList;

import additional.Field;
import additional.IFieldable;

public abstract class AbstractCalculationMethod implements IFieldable {

	@Override
	public abstract ArrayList<Field> getFields();

	public abstract ArrayList<Field> calculate(Project project);

}
