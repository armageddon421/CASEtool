package model;

public class FunctionRequirement extends AbstractRequirement {
	public FunctionRequirement(String RequirementName){
		requirementFields.add(new Field("FR name", Type.String, true, this, RequirementName));
		requirementFields.add(new Field("FR Description", Type.Text, true, this, ""));
		requirementFields.add(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		requirementFields.add(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
	}
}
