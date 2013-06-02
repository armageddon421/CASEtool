package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;
import additional.IFieldable;
import additional.Type;

/**
 * @author Patrick
 *
 */
public class Project implements IFieldable {
	/**
	 * Contains all fields associated with the project
	 */
	private ArrayList<Field> _projectFields = new ArrayList<>();
	//Requirement Fields of the project
	private Field _pFunctionReq;
	private Field _pDataReq;
	private Field _pGlossary;
	
	
	private CalculationEnum _calcMethodEnum;
	private AbstractCalculationMethod _calcMethodInstance;
	
	public Project(String projectName){
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, "Ergänzungen"));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Summary", Type.Text, true, this, "Zusammenfassung"));	
		
		_pFunctionReq = new Field("Function Requirements", Type.Null, false, this, null);
		_projectFields.add(_pFunctionReq);
		
		_calcMethodEnum = CalculationEnum.notSet;
		_calcMethodInstance = null;
	}

	@Override
	public ArrayList<Field> getFields() {
		return _projectFields;
	}
	
	public CalculationEnum getCalcMethod() {
		return _calcMethodEnum;
	}
	
	public void addFunctionRequirement(String requirementID){
		Field tempField = new Field("FunctionReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("FR Name", Type.String, true, this, "new Function Requirement"));
		tempField.addChild(new Field("FR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_pFunctionReq.addChild(tempField);
	}
	
	public void deleteFunctionRequirement(Field fReqToDelete){
		if(_pFunctionReq.contains(fReqToDelete)){
			_pFunctionReq.removeChild(fReqToDelete);
		}
	}
	
	public Field getFunctionRequirements(){
		return _pFunctionReq;
	}
	
	public void setCalcMethod(CalculationEnum calcMethodEnum, AbstractCalculationMethod calcMethodInstance ) {
		this._calcMethodEnum = calcMethodEnum;
		this._calcMethodInstance = calcMethodInstance;
	}
	
	public Field calculate(){
		if(_calcMethodInstance != null){
			return _calcMethodInstance.calculate(this);
		}
		else {
			return null;
		}
	}


}
