package model.daten;

import java.util.ArrayList;

public class Data {

	static Data instance = null;
	private ArrayList<Project> Projectlist = null;
	
	private Data() {
		
	}
	
	public Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}
	
	
	public void createNewProject(String projectName){
		
	}
	
	public void deleteProject(String projectName){
		
	}
	
	public Project getProject(String projectName){
		return null;
	}
	
	
}
