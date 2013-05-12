package model;

import java.util.ArrayList;

public interface IModelFacade {
	public void openProject(String filepath);
	public void saveProject();
	public void exportProject();
	//getCalcMethods
	//public Field calculate(String / Enum CalculationMethod);
	public ArrayList<Project> listAllProject();
	public openProjectNum ()
}
