package model;

import java.util.ArrayList;

public interface IModelFacade {
	public void openProject(String filepath);
	public void saveProject();
	public void exportProject();
	//getCalcMethods
	public ArrayList<Project> listAllProject();
	public void openProjectNum(int index);
	public ArrayList<Field> getProjectFields();
	public ArrayList<Field> getRequirementFields();
	public void setField(Field field);
	public ArrayList<String> getCalculationMethods();
	public void setCalculationMethod(String calculationMethod);
	public Field calculate();
}
