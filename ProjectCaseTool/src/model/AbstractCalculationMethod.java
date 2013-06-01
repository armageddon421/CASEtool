package model;

import java.util.ArrayList;

import additional.Field;
import additional.IFieldable;

public abstract class AbstractCalculationMethod implements IFieldable {

	@Override
	public abstract ArrayList<Field> getFields();

	public abstract Field calculate(Project project);

}
