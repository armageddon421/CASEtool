package model;

import java.util.ArrayList;

//Singleton
public class Data {
	private static Data _dataInstance;
	private ArrayList<Project> _allProjects;
	
	private Data() {
		_allProjects = new ArrayList<>();		
	}
	
	public static Data getData(){
		if (_dataInstance == null){
			_dataInstance = new Data();
		}
		return _dataInstance;
	}

	public ArrayList<Project> get_allProjects() {
		return _allProjects;
	}
	
	
	
//	public void addProject(String _projectName){
//		_allProjects.add(_additionalProject);
//	}
}
