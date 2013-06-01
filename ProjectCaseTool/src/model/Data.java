package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;

//Singleton
public class Data {
	private static Data _dataInstance;
	private ArrayList<Project> _allProjects;
	private Project _currentProject;
	
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
	 * Sets the current project to the owner of the given field. 
	 * @param fieldOfProject
	 */
	public void setCurrentProject(Field fieldOfProject) {
		if(fieldOfProject.getOwner() instanceof Project){
			_currentProject = (Project) fieldOfProject.getOwner();
		}
	}
	
	public Project getCurrentProject(){
		return _currentProject;
	}

	/**
	 * @return All fields of all the projects held by Data.
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
	 * Creates a new project, adds it to the project list held by Data and makes it the currentProject.
	 * @param projectName Name of the project to be created.
	 */
	public void createProject(String projectName){
		_currentProject = new Project(projectName);
		addProject(_currentProject);
	}	


	/**
	 * Adds the given Project to the project list held by Data. 
	 * Does nothing, if project already exists in the list.
	 * @param additionalProject Project to be added.
	 */
	private void addProject(Project additionalProject){
		if(!_allProjects.contains(additionalProject)){
			_allProjects.add(additionalProject);
		}
	}

	public void deleteCurrentProject() {
		// TODO Auto-generated method stub
		
	}

	public void deleteProject(Field fieldOfProject) {
		// TODO Auto-generated method stub
		
	}
}
