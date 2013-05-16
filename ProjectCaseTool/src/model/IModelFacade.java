package model;

import java.util.ArrayList;

public interface IModelFacade {
	public void loadProject(String _filepath);
	public void saveProject(String _filepath);
	public void setCurrentProject(Field _fieldOfProject);
	public ArrayList<Field> getCurrentProjectFields();
	public ArrayList<ArrayList<Field>> getAllProjectFields();
	public ArrayList<ArrayList<Field>> getFunctionRequirementFields();
	public ArrayList<ArrayList<Field>> getDataRequirementFields();
	public ArrayList<ArrayList<Field>> getPerformanceRequirementFields();
	public void setCalculationMethod(CalculationEnum _calcMethod);
	public CalculationEnum getCalculationMethod();
	public ArrayList<Field> calculate();
} 
