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
	private Field _functionReq;
	private Field _dataReq;
	private Field _performanceReq;
	private Field _glossary;
	private Field _fpParameters;
	
	
	
	private CalculationEnum _calcMethodEnum;
	private AbstractCalculationMethod _calcMethodInstance;
	
	public Project(String projectName){
		//Standard project fields
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, "Ergänzungen"));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Summary", Type.Text, true, this, "Zusammenfassung"));	
		
		//Requirement Fields
		_functionReq = new Field("Function Requirements", Type.String, false, this, "Produktfunktionen");
		_projectFields.add(_functionReq);
		_dataReq = new Field("Data Requirements", Type.String, false, this, "Produktdaten");
		_projectFields.add(_dataReq);
		_performanceReq = new Field("Performance Requirements", Type.String, false, this, "Produktleistungen");
		_projectFields.add(_performanceReq);
		
		//Glossary
		_glossary = new Field("Glossary", Type.String, false, this, "Glossar");
		_projectFields.add(_glossary);
		
		//FP Parameters
		initFPParameters();
		
		//Calculation
		_calcMethodEnum = CalculationEnum.notSet;
		_calcMethodInstance = null;
	}

	@Override
	public ArrayList<Field> getFields() {
		return _projectFields;
	}
	
	/**
	 * @return Calculation method that is set for this project.
	 */
	public CalculationEnum getCalcMethod() {
		return _calcMethodEnum;
	}
	
	/**
	 * Adds a new, empty function requirement.
	 * @param requirementID Id, name of the requirement.
	 */
	public void addFunctionRequirement(String requirementID){
		Field tempField = new Field("FunctionReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("FR Name", Type.String, true, this, "new Function Requirement"));
		tempField.addChild(new Field("FR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_functionReq.addChild(tempField);
	}
	
	/**
	 * Deletes a function requirement.
	 * @param fReqToDelete The function requirement, which should be deleted.
	 */
	public void deleteFunctionRequirement(Field fReqToDelete){
		if(_functionReq.contains(fReqToDelete)){
			_functionReq.removeChild(fReqToDelete);
		}
	}
	
	/**
	 * @return function requirements in a field.
	 */
	public Field getFunctionRequirements(){
		return _functionReq;
	}
	
	/**
	 * * Adds a new, empty data requirement.
	 * @param requirementID Id, name of the requirement.
	 */
	public void addDataRequirement(String requirementID){
		Field tempField = new Field("DataReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("DR Name", Type.String, true, this, "new Data Requirement"));
		tempField.addChild(new Field("DR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("DR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("DR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_dataReq.addChild(tempField);		
	}
	
	/**
	 * Deletes a data requirement.
	 * @param dReqToDelete The data requirement, which should be deleted.
	 */
	public void deleteDataRequirement(Field dReqToDelete){
		if(_dataReq.contains(dReqToDelete)){
			_dataReq.removeChild(dReqToDelete);
		}
	}
	
	/**
	 * @return data requirements as a field.
	 */
	public Field getDataRequirements(){
		return _dataReq;
	}	
	
	/**
	 * Adds a new, empty performance requirement.
	 * @param requirementID Id, name of the requirement.
	 */
	public void addPerformanceRequirement(String requirementID){
		Field tempField = new Field("PerformanceReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("PR Name", Type.String, true, this, "new Performance Requirement"));
		tempField.addChild(new Field("PR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("PR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("PR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_performanceReq.addChild(tempField);		
	}
	
	/** * Deletes a performance requirement.
	 * @param pReqToDelete The performance requirement, which should be deleted.
	 */
	public void deletePerformanceRequirement(Field pReqToDelete){
		if(_performanceReq.contains(pReqToDelete)){
			_performanceReq.removeChild(pReqToDelete);
		}
	}
	
	/**
	 * @return performance requirements as a field.
	 */
	public Field getPerformanceRequirements(){
		return _performanceReq;
	}
	
	/**
	 * Adds a new glossary entry.
	 * @param keyword
	 * @param description
	 */
	public void addGlossaryEntry(String keyword, String description){
		Field tempField = new Field("Glossary Entry", Type.String, true, this, keyword);
		tempField.addChild(new Field("Description", Type.Text, true, this, description));
		_glossary.addChild(tempField);
	}
	
	/**
	 * Deletes a glossary entry.
	 * @param entryToDelete Field of the glossary entry to delete.
	 */
	public void deleteGlossaryEntry(Field entryToDelete){
		if(_glossary.contains(entryToDelete)) {
			_glossary.removeChild(entryToDelete);
		}
	}
	
	/**
	 * @return all function point parameters of the project as a field.
	 */
	public Field getFPParameters(){
		return _fpParameters;
	}
	
	/**
	 * Sets the calculation method of the project.
	 * @param calcMethodEnum CalculationEnum according to the method.
	 * @param calcMethodInstance Instance of the calculation method.
	 */
	public void setCalcMethod(CalculationEnum calcMethodEnum, AbstractCalculationMethod calcMethodInstance ) {
		this._calcMethodEnum = calcMethodEnum;
		this._calcMethodInstance = calcMethodInstance;
	}
	
	/**
	 * Returns the result of the set calculation method. CAUTION: result may very depending on calculation method.
	 * @return field with the results.
	 */
	public Field calculate(){
		if(_calcMethodInstance != null){
			return _calcMethodInstance.calculate(this);
		}
		else {
			return null;
		}
	}
	
	/**
	 * initializes the function point parameters of the project.
	 */
	private void initFPParameters(){
		_fpParameters = new Field("FP Parameters", Type.String, false, this, "Function Point Parameter");
		
		Field input = new Field("Input", Type.String, false, this, "Eingabedaten");
		input.addChild(new Field("Easy", Type.Float, true, this, 3));
		input.addChild(new Field("Medium", Type.Float, true, this, 4));
		input.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field query = new Field("Query", Type.String, false, this, "Abfragen");
		query.addChild(new Field("Easy", Type.Float, true, this, 3));
		query.addChild(new Field("Medium", Type.Float, true, this, 4));
		query.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field output = new Field("Output", Type.String, false, this, "Ausgaben");
		output.addChild(new Field("Easy", Type.Float, true, this, 3));
		output.addChild(new Field("Medium", Type.Float, true, this, 4));
		output.addChild(new Field("Complex", Type.Float, true, this, 6)); 
		
		Field data = new Field("Internal Data", Type.String, false, this, "Datenbestände");
		data.addChild(new Field("Easy", Type.Float, true, this, 3));
		data.addChild(new Field("Medium", Type.Float, true, this, 4));
		data.addChild(new Field("Complex", Type.Float, true, this, 6)); 
		
		Field reference = new Field("External Data", Type.String, false, this, "Referenzdaten");
		reference.addChild(new Field("Easy", Type.Float, true, this, 3));
		reference.addChild(new Field("Medium", Type.Float, true, this, 4));
		reference.addChild(new Field("Complex", Type.Float, true, this, 6)); 
		
		Field influences = new Field("Influences", Type.String, false, this, "Einflussfaktoren");
		influences.addChild(new Field("Verflechtungen", Type.Float, true, this, 0));
		influences.addChild(new Field("Dezentrale Daten", Type.Float, true, this, 0));
		influences.addChild(new Field("Transaktionsrate", Type.Float, true, this, 0));
		influences.addChild(new Field("Rechenoperationen", Type.Float, true, this, 0));
		influences.addChild(new Field("Kontrollverfahren", Type.Float, true, this, 0));
		influences.addChild(new Field("Ausnahmeregelungen", Type.Float, true, this, 0));
		influences.addChild(new Field("Logik", Type.Float, true, this, 0));
		influences.addChild(new Field("Wiederverwendbarkeit", Type.Float, true, this, 0));
		influences.addChild(new Field("Datenbestandskonvertierung", Type.Float, true, this, 0));
		influences.addChild(new Field("Anpassbarkeit", Type.Float, true, this, 0));
		
		_fpParameters.addChild(input);
		_fpParameters.addChild(query);
		_fpParameters.addChild(output);
		_fpParameters.addChild(data);
		_fpParameters.addChild(reference);
		_fpParameters.addChild(influences);
		
		
	}


}
