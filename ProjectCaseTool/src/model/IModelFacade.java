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
	 * Saves the current project at given file path.
	 * @param filepath
	 */
	public void saveProject(String filepath);
		
	/**
	 * Creates a new project and makes it the current project.
	 * @param projectName Name of the new project.
	 */
	public void createProject(String projectName);

	//TODO: what happens to current project
	public void deleteCurrentProject();
	
	public void deleteProject(Field fieldOfProject);
		
	/**
	 * Sets the current project.
	 * @param fieldOfProject Field of the project which is to be the current project. 
	 * The owner reference of this field will be used to determine the project.
	 */
	public void setCurrentProject(Field fieldOfProject);
	
	//TODO check what really happens if currentproject == null and change accordingly
	/**
	 * Does nothing if current project is not set.
	 * @return ArrayList of all fields held by current project.
	 */
	public ArrayList<Field> getCurrentProjectFields();
	
	/**
	 * @return All fields of all projects. 
	 * Every project with all its fields is one entry in the outer array list. 
	 * The inner array list consists of the fields each project holds.
	 */
	public ArrayList<ArrayList<Field>> getAllProjectFields();
	
	/**
	 * @return The function requirement field of the current project. 
	 */
	public Field getFunctionRequirements();
	
	/**
	 * @return The data requirement field of the current project.
	 */
	public Field getDataRequirements();
	
	/**
	 * @return The performance requirement field of the current project.
	 */
	public Field getPerformanceRequirements();
	
	public void addFunctionRequirement(String id);
	
	public void addDataRequirement(String id);
	
	public void addPerformanceRequirement(String id);
	
	public void deleteFunctionRequirement(Field fReqToDelete);
	
	public void deleteDataRequirement(Field dReqToDelete);
	
	public void deletePerformanceRequirement(Field pReqToDelete);
	
	/**
	 * Sets the calculation method of the current project. There must be a current project.
	 * @param calcMethod  
	 */
	public void setCalculationMethod(CalculationEnum calcMethod);
	
	/**
	 * @return The calculation method of the current project. Null, if there is no current project.
	 */
	public CalculationEnum getCalculationMethod();
	
	/**
	 * Calculates the estimated effort of the current project. 
	 * @return Returns the calculation field of the current project.
	 */
	public Field calculate();
} 
