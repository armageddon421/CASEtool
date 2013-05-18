package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;

public interface IModelFacade {
	/**
	 * Loads a project from a given file path and makes it the current project.
	 * @param filepath 
	 */
	public void loadProject(String filepath);
		
	/**
	 * Creates a new project and makes it the current project.
	 * @param projectName Name of the new project.
	 */
	public void createProject(String projectName);
	
	/**
	 * Saves the current project at given file path.
	 * @param filepath
	 */
	public void saveProject(String filepath);
		
	/**
	 * Sets the current project.
	 * @param fieldOfProject Field of the project which is to be the current project. 
	 * The owner reference of this field will be used to determine the project.
	 */
	public void setCurrentProject(Field fieldOfProject);
	
	/**
	 * @return All fields of the current Project.
	 */
	public ArrayList<Field> getCurrentProjectFields();
	
	/**
	 * @return All fields of all projects. 
	 * Every project with all its fields is one entry in the outer array list. 
	 * The inner array list consists of the fields each project holds.
	 */
	public ArrayList<ArrayList<Field>> getAllProjectFields();
	
	/**
	 * @return All fields of all function requirements of the current project. 
	 * Every requirement with all its fields is one entry in the outer array list. 
	 * The inner array list consists of the fields each requirement holds.
	 */
	public ArrayList<ArrayList<Field>> getFunctionRequirementFields();
	
	/**
	 * @return All fields of all data requirements of the current project. 
	 * Every requirement with all its fields is one entry in the outer array list. 
	 * The inner array list consists of the fields each requirement holds.
	 */
	public ArrayList<ArrayList<Field>> getDataRequirementFields();
	
	/**
	 * @return All fields of all performance requirements of the current project. 
	 * Every requirement with all its fields is one entry in the outer array list. 
	 * The inner array list consists of the fields each requirement holds.
	 */
	public ArrayList<ArrayList<Field>> getPerformanceRequirementFields();
	
	/**
	 * Sets the calculation method of the current project.
	 * @param calcMethod  
	 */
	public void setCalculationMethod(CalculationEnum calcMethod);
	
	/**
	 * @return The calculation method of the current project.
	 */
	public CalculationEnum getCalculationMethod();
	
	/**
	 * Calculates the estimated effort of the current project. 
	 * @return List of fields containing the results.
	 */
	public ArrayList<Field> calculate();
} 
