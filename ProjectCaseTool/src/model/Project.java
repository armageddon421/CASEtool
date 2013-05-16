package model;

import java.util.ArrayList;

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
	
	public void addFunctionRequirement(String _RequirementName){
		_projectFunctionRequirements.add(new FunctionRequirement(_RequirementName));
	}
	
	//TODO Muss überprüft werden, ob die ANforderung noch in der Liste ist????
	public void deleteFunctionRequirement(Field _field){
		if(_field.getOwner() instanceof FunctionRequirement){
			 _projectFunctionRequirements.remove(_field.getOwner());
		}
	}
	
	public ArrayList<ArrayList<Field>> getFunctionRequirements(){
		ArrayList<ArrayList<Field>> _allFunctionRequirementFields = new ArrayList<>();
		for(FunctionRequirement _requirement : _projectFunctionRequirements){
			_allFunctionRequirementFields.add(_requirement.getFields());
		}
		return _allFunctionRequirementFields;
	}
	
	//TODO in Facade mit _currentProject ausführen???
	public ArrayList<Field> calculate(AbstractCalculationMethod calcMethod){
		return calcMethod.calculate(this);
	}


}
