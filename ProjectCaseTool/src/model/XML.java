package model;

import java.util.ArrayList;

import additional.Field;

public class XML implements IImport, IExport {
	
	@Override
	public void exportProject(final String filename, final Project project) {
		ArrayList<Field> fields = project.getFields();
		
		
	}
	
	@Override
	public Project importProject(final String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
