package model;

import java.util.ArrayList;

import additional.Field;
import additional.IFieldable;
import additional.Type;

public class Project implements IFieldable {
	private ArrayList<Field> _projectFields = new ArrayList<>();
	private ArrayList<FunctionRequirement> _projectFunctionRequirements = new ArrayList<>();
	
	public Project(String projectName){
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, ""));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, ""));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, ""));
		_projectFields.add(new Field("Summary", Type.Text, true, this, ""));		
	}

	@Override
	public ArrayList<Field> getFields() {
		return _projectFields;
	}
	
	public void addFunctionRequirement(String RequirementName){
		_projectFunctionRequirements.add(new FunctionRequirement(RequirementName));
	}
	
	//TODO Muss überprüft werden, ob die ANforderung noch in der Liste ist????
	public void deleteFunctionRequirement(Field field){
		if(field.getOwner() instanceof FunctionRequirement){
			 _projectFunctionRequirements.remove(field.getOwner());
		}
	}
	
	public ArrayList<ArrayList<Field>> getFunctionRequirements(){
		ArrayList<ArrayList<Field>> allFunctionRequirementFields = new ArrayList<>();
		for(FunctionRequirement _requirement : _projectFunctionRequirements){
			allFunctionRequirementFields.add(_requirement.getFields());
		}
		return allFunctionRequirementFields;
	}
	
	//TODO in Facade mit _currentProject ausführen???
	public ArrayList<Field> calculate(AbstractCalculationMethod calcMethod){
		return calcMethod.calculate(this);
	}


}
