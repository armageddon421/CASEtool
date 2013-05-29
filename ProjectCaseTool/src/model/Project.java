package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;
import additional.IFieldable;
import additional.Type;

/**
 * @author Patrick
 *
 */
public class Project implements IFieldable {
	private ArrayList<Field> _projectFields = new ArrayList<>();
	private ArrayList<FunctionRequirement> _projectFunctionRequirements = new ArrayList<>();
	private CalculationEnum _calcMethod;
	
	public Project(String projectName){
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, ""));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, ""));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, ""));
		_projectFields.add(new Field("Summary", Type.Text, true, this, ""));	
		_projectFields.add(new Field("Function Requirements", Type.Null, false, this, null));
		//Requirements
		_calcMethod = CalculationEnum.FunctionPoint;
	}

	@Override
	public ArrayList<Field> getFields() {
		return _projectFields;
	}
	
	public CalculationEnum getcalcMethod() {
		return _calcMethod;
	}

	/**
	 * Sets the calculation method to be used by this project.
	 * @param calcMethod
	 */
	public void setcalcMethod(CalculationEnum calcMethod) {
		this._calcMethod = calcMethod;
	}
	
	/**
	 * @param RequirementName
	 */
	public void addFunctionRequirement(String RequirementName){
//		_projectFields.get(_projectFields.indexOf(o))
	}
	
	public void deleteFunctionRequirement(Field field){
		if(field.getOwner() instanceof FunctionRequirement){
			if(_projectFunctionRequirements.contains(field.getOwner())){
				 _projectFunctionRequirements.remove(field.getOwner());
			}
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
