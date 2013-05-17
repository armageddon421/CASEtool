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
	public static Data getData(){
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
	 * Creates a new empty Project and adds it to the list of all projects held by Data.
	 * @param _projectName Name of the new Project.
	 */
	public Project addProject(String projectName){
		Project additionalProject = new Project(projectName);
		_allProjects.add(additionalProject);
		return additionalProject;
	}
}
