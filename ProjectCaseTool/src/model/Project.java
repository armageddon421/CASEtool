package model;

import java.util.ArrayList;

public class Project implements IFieldable {
	ArrayList<Field> projectFields = new ArrayList<>();
	
	public Project(String projectName){
		projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		projectFields.add(new Field("Project Additions", Type.Text, true, this, ""));
		projectFields.add(new Field("Project Objectives", Type.Text, true, this, ""));
		projectFields.add(new Field("Project Use", Type.Text, true, this, ""));
		projectFields.add(new Field("Summary", Type.Text, true, this, ""));
		projectFields.add(e)
		
	}

	@Override
	public ArrayList<Field> getFields() {
		return projectFields;
	}


}
