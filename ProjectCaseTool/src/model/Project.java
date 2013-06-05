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
	private ArrayList<Field>			_projectFields	= new ArrayList<>();
	// Requirement Fields of the project
	private Field						_functionReq;
	private Field						_dataReq;
	private Field						_performanceReq;
	private Field						_glossary;
	private Field						_fpParameters;
	
	
	private CalculationEnum				_calcMethodEnum;
	private AbstractCalculationMethod	_calcMethodInstance;
	
	public Project() {
		
	}
	
	public Project(final String projectName) {
		// Standard project fields
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, "Ergänzungen"));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, "Projektziele"));
		_projectFields.add(new Field("Summary", Type.Text, true, this, "Zusammenfassung"));
		
		// Requirement Fields
		_functionReq = new Field("Function Requirements", Type.String, false, this, "Produktfunktionen");
		_projectFields.add(_functionReq);
		_dataReq = new Field("Data Requirements", Type.String, false, this, "Produktdaten");
		_projectFields.add(_dataReq);
		_performanceReq = new Field("Performance Requirements", Type.String, false, this, "Produktleistungen");
		_projectFields.add(_performanceReq);
		
		// Glossary
		_glossary = new Field("Glossary", Type.String, false, this, "Glossar");
		_projectFields.add(_glossary);
		
		// FP Parameters
		_fpParameters = new Field("FP Parameters", Type.String, false, this, "Function Point Parameter");
		Field input = new Field("Input", Type.String, false, this, "Eingabedaten");
		input.addChild(new Field("Easy", Type.Integer, true, this, 3));
		input.addChild(new Field("Medium", Type.Integer, true, this, 4));
		input.addChild(new Field("Complex", Type.Integer, true, this, 6));
		Field query = new Field("Query", Type.String, false, this, "Abfragen");
		query.addChild(new Field("Easy", Type.Integer, true, this, 3));
		query.addChild(new Field("Medium", Type.Integer, true, this, 4));
		query.addChild(new Field("Complex", Type.Integer, true, this, 6));
		Field output = new Field("Output", Type.String, false, this, "Ausgaben");
		output.addChild(new Field("Easy", Type.Integer, true, this, 3));
		output.addChild(new Field("Medium", Type.Integer, true, this, 4));
		output.addChild(new Field("Complex", Type.Integer, true, this, 6));
		Field data = new Field("Data", Type.String, false, this, "Datenbestände");
		data.addChild(new Field("Easy", Type.Integer, true, this, 3));
		data.addChild(new Field("Medium", Type.Integer, true, this, 4));
		data.addChild(new Field("Complex", Type.Integer, true, this, 6));
		Field reference = new Field("Reference Data", Type.String, false, this, "Referenzdaten");
		reference.addChild(new Field("Easy", Type.Integer, true, this, 3));
		reference.addChild(new Field("Medium", Type.Integer, true, this, 4));
		reference.addChild(new Field("Complex", Type.Integer, true, this, 6));
		
		_fpParameters.addChild(input);
		_fpParameters.addChild(query);
		_fpParameters.addChild(output);
		_fpParameters.addChild(data);
		_fpParameters.addChild(reference);
		
		_fpParameters.addChild(new Field("Verflechtungen", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Dezentrale Daten", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Transaktionsrate", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Rechenoperationen", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Kontrollverfahren", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Ausnahmeregelungen", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Logik", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Wiederverwendbarkeit", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Datenbestandskonvertierung", Type.Integer, true, this, 0));
		_fpParameters.addChild(new Field("Anpassbarkeit", Type.Integer, true, this, 0));
		_projectFields.add(_fpParameters);
		
		// Calculation
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
	
	public void addFunctionRequirement(final String requirementID) {
		Field tempField = new Field("FunctionReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("FR Name", Type.String, true, this, "new Function Requirement"));
		tempField.addChild(new Field("FR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_functionReq.addChild(tempField);
	}
	
	public void deleteFunctionRequirement(final Field fReqToDelete) {
		if (_functionReq.contains(fReqToDelete)) {
			_functionReq.removeChild(fReqToDelete);
		}
	}
	
	public Field getFunctionRequirements() {
		return _functionReq;
	}
	
	public void addDataRequirement(final String requirementID) {
		Field tempField = new Field("DataReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("DR Name", Type.String, true, this, "new Data Requirement"));
		tempField.addChild(new Field("DR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("DR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("DR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_dataReq.addChild(tempField);
	}
	
	public void deleteDataRequirement(final Field dReqToDelete) {
		if (_dataReq.contains(dReqToDelete)) {
			_dataReq.removeChild(dReqToDelete);
		}
	}
	
	public Field getDataRequirements() {
		return _dataReq;
	}
	
	public void addPerformanceRequirement(final String requirementID) {
		Field tempField = new Field("PerformanceReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("PR Name", Type.String, true, this, "new Performance Requirement"));
		tempField.addChild(new Field("PR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("PR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("PR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_performanceReq.addChild(tempField);
	}
	
	public void deletePerformanceRequirement(final Field dReqToDelete) {
		if (_performanceReq.contains(dReqToDelete)) {
			_performanceReq.removeChild(dReqToDelete);
		}
	}
	
	public Field getPerformanceRequirements() {
		return _performanceReq;
	}
	
	public void addGlossaryEntry(final String keyword, final String description) {
		Field tempField = new Field("Glossary Entry", Type.String, true, this, keyword);
		tempField.addChild(new Field("Description", Type.Text, true, this, description));
		_glossary.addChild(tempField);
	}
	
	public void deleteGlossaryEntry(final Field entryToDelete) {
		if (_glossary.contains(entryToDelete)) {
			_glossary.removeChild(entryToDelete);
		}
	}
	
	public Field getFPParameters() {
		return _fpParameters;
	}
	
	public void setCalcMethod(final CalculationEnum calcMethodEnum, final AbstractCalculationMethod calcMethodInstance) {
		this._calcMethodEnum = calcMethodEnum;
		this._calcMethodInstance = calcMethodInstance;
	}
	
	public Field calculate() {
		if (_calcMethodInstance != null) {
			return _calcMethodInstance.calculate(this);
		}
		else {
			return null;
		}
	}
	
	protected void setProjectFields(final ArrayList<Field> fields) {
		this._projectFields = fields;
		
		
		// Assign all special fields to their corresponding variables
		for (Field field : fields) {
			if (field.getName().equals("Function Requirements")) {
				_functionReq = field;
			}
			else if (field.getName().equals("Data Requirements")) {
				_dataReq = field;
				
			}
			else if (field.getName().equals("Performance Requirements")) {
				_performanceReq = field;
			}
			else if (field.getName().equals("Glossary")) {
				_glossary = field;
			}
			else if (field.getName().equals("FP Parameters")) {
				_fpParameters = field;
			}
			
		}
		
		
		// Calculation
		_calcMethodEnum = CalculationEnum.notSet;
		_calcMethodInstance = null;
		
	}
	
}
