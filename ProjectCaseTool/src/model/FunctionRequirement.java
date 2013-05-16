package model;

public class FunctionRequirement extends AbstractRequirement {
	public FunctionRequirement(String RequirementName){
		_requirementFields.add(new Field("FR name", Type.String, true, this, RequirementName));
		_requirementFields.add(new Field("FR Description", Type.Text, true, this, ""));
		_requirementFields.add(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		_requirementFields.add(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
	}
}
