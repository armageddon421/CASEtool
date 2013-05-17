package model;

import java.util.ArrayList;

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
		for(Project _project : _allProjects){
			ArrayList<Field> _projectFields = _project.getFields();
			_allProjectFields.add(_projectFields);
		}
		return _allProjectFields;
	}
	
	
	/**
	 * Creates a new empty Project and adds it to the list of all projects held by Data.
	 * @param _projectName Name of the new Project.h
	 */
	public void addProject(String _projectName){
		Project _additionalProject = new Project(_projectName);
		_allProjects.add(_additionalProject);
	}
}
