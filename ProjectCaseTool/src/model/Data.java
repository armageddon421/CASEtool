package model;

import java.util.ArrayList;

import additional.Field;

//Singleton
public class Data {
	private static Data _dataInstance;
	private ArrayList<Project> _allProjects;
	
	private Data() {
		_allProjects = new ArrayList<>();		
	}
	
	/**
	 * @return Instance of singleton Data.
	 */
	public static Data getInstance(){
		if (_dataInstance == null){
			_dataInstance = new Data();
		}
		return _dataInstance;
	}

	/**
	 * @return all fields of the projects held by Data.
	 */
	public ArrayList<ArrayList<Field>> getAllProjectFields() {
		ArrayList<ArrayList<Field>> _allProjectFields = new ArrayList<>();
		for(Project project : _allProjects){
			ArrayList<Field> projectFields = project.getFields();
			_allProjectFields.add(projectFields);
		}
		return _allProjectFields;
	}
	
	/**
	 * Creates a new project and add it to the project list held by Data.
	 * @param projectName Name of the project to be created.
	 * @return Created Project.
	 */
	public Project createProject(String projectName){
		Project additionalProject = new Project(projectName);
		addProject(additionalProject);
		return additionalProject;
	}
	
	
	/**
	 * Adds the given Project to the project list held by Data. 
	 * Does nothing, if project already exists in the list.
	 * @param additionalProject Project to be added.
	 */
	public void addProject(Project additionalProject){
		if(!_allProjects.contains(additionalProject)){
			_allProjects.add(additionalProject);
		}
	}
}
