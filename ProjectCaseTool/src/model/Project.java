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
	/**
	 * Holds the Field containing all Function Requirements
	 */
	private Field						_functionReq;
	/**
	 * Holds the Field containing all Data Requirements
	 */
	private Field						_dataReq;
	/**
	 * Holds the Field containing all Performance Requirements
	 */
	private Field						_performanceReq;
	/**
	 * Holds the Field containing all Glossary Entries
	 */
	private Field						_glossary;
	/**
	 * Holds the Field containing all FunctionPoint Calculation Parameters
	 */
	private Field						_fpParameters;
	
	/**
	 * Holds the field containing the influences concerning the FunctionPoint calculation.
	 */
	private Field						_fpInfluences;
	
	
	/**
	 * Enumerates the currently selected Calculation Method
	 */
	private CalculationEnum				_calcMethodEnum;
	
	/**
	 * Holds the object that should do the calculation
	 */
	private AbstractCalculationMethod	_calcMethodInstance;

	private String functionReqDescr = "\nHinzufügen von Produktfunktionen unter Hinzufügen -> Produktfunktionen." +
			"\n\nLöschen durch Selektieren und Drücken von ENTF." ;
	private String dataReqDescr = "\nHinzufügen von Produktdaten unter Hinzufügen -> Produktdaten." +
			"\n\nLöschen durch Selektieren und Drücken von ENTF." ;
	private String perReqDescr = "\nHinzufügen von Produktleistungen unter Hinzufügen -> Produktleistungen." +
			"\n\nLöschen durch Selektieren und Drücken von ENTF." ;
	private String glossaryDescr = "\nHinzufügen eines Glossareintrages unter Hinzufügen -> Glossareintrag." +
			"\n\nLöschen durch Selektieren und Drücken von ENTF." ;
	
	/**
	 * Empty constructor to be used with Import only
	 */
	public Project() {
		
	}
	
	/**
	 * Constructor that creates a regular new Project and fills it with the
	 * necessary Fields
	 * 
	 * @param projectName
	 *            Name the project should be given
	 */
	public Project(final String projectName) {
		// Standard project fields
		_projectFields.add(new Field("Project Name", Type.String, true, this, projectName));
		_projectFields.add(new Field("Project Additions", Type.Text, true, this, "Ergänzungen"));
		_projectFields.add(new Field("Project Objectives", Type.Text, true, this, "Produktziele"));
		_projectFields.add(new Field("Project Use", Type.Text, true, this, "Produkteinsatz"));
		_projectFields.add(new Field("Summary", Type.Text, true, this, "Zusammenfassung"));
		
		// Requirement Fields
		_functionReq = new Field("Function Requirements", Type.String, false, this, functionReqDescr);
		_projectFields.add(_functionReq);
		_dataReq = new Field("Data Requirements", Type.String, false, this, dataReqDescr);
		_projectFields.add(_dataReq);
		_performanceReq = new Field("Performance Requirements", Type.String, false, this, perReqDescr);
		_projectFields.add(_performanceReq);
		
		// Glossary
		_glossary = new Field("Glossary", Type.String, false, this, glossaryDescr);
		_projectFields.add(_glossary);
		
		// FP Parameters
		_fpParameters = new Field("FP Parameters", Type.String, false, this, "FP Parameter");
		_fpInfluences = new Field("FP Influences", Type.String, false, this, "FP Einflussfaktoren");
		
		Field input = new Field("Input", Type.String, false, this, "Eingabedaten");
		input.addChild(new Field("", Type.String, false, this, "Eingabedaten"));
		input.addChild(new Field("Easy", Type.Float, true, this, 3));
		input.addChild(new Field("Medium", Type.Float, true, this, 4));
		input.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field query = new Field("Query", Type.String, false, this, "Abfragen");
		query.addChild(new Field("Query", Type.String, false, this, "Abfragen"));
		query.addChild(new Field("Easy", Type.Float, true, this, 3));
		query.addChild(new Field("Medium", Type.Float, true, this, 4));
		query.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field output = new Field("Output", Type.String, false, this, "Ausgaben");
		output.addChild(new Field("Output", Type.String, false, this, "Ausgaben"));
		output.addChild(new Field("Easy", Type.Float, true, this, 3));
		output.addChild(new Field("Medium", Type.Float, true, this, 4));
		output.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field data = new Field("Internal Data", Type.String, false, this, "Datenbestände");
		data.addChild(new Field("Internal Data", Type.String, false, this, "Datenbestände"));
		data.addChild(new Field("Easy", Type.Float, true, this, 3));
		data.addChild(new Field("Medium", Type.Float, true, this, 4));
		data.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field reference = new Field("External Data", Type.String, false, this, "Referenzdaten");
		reference.addChild(new Field("External Data", Type.String, false, this, "Referenzdaten"));
		reference.addChild(new Field("Easy", Type.Float, true, this, 3));
		reference.addChild(new Field("Medium", Type.Float, true, this, 4));
		reference.addChild(new Field("Complex", Type.Float, true, this, 6));
		
			
		_fpParameters.addChild(input);
		_fpParameters.addChild(query);
		_fpParameters.addChild(output);
		_fpParameters.addChild(data);
		_fpParameters.addChild(reference);
		
		_projectFields.add(_fpParameters);
		
//		Field influences = new Field("Influences", Type.String, false, this, "Einflussfaktoren");
		
		_fpInfluences.addChild(new Field("Verflechtungen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Dezentrale Daten", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Transaktionsrate", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Rechenoperationen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Kontrollverfahren", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Ausnahmeregelungen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Logik", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Wiederverwendbarkeit", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Datenbestandskonvertierung", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Anpassbarkeit", Type.Float, true, this, 0));
		
//		_fpInfluences.addChild(influences);
		
		_projectFields.add(_fpInfluences);
		
		// Calculation
		_calcMethodEnum = CalculationEnum.notSet;
		_calcMethodInstance = null;
	}
	
	@Override
	public ArrayList<Field> getFields() {
		return _projectFields;
	}
	
	/**
	 * Returns the Enum of the currently selected Calculation Method
	 * 
	 * @return CalculationEnum
	 */
	public CalculationEnum getCalcMethod() {
		return _calcMethodEnum;
	}
	
	/**
	 * add a Function Requirement to the Project
	 * 
	 * @param requirementID
	 *            ID for the Requirement
	 */
	public void addFunctionRequirement(final String requirementID) {
		Field tempField = new Field("FunctionReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("ID", Type.String, true, this, requirementID));
		tempField.addChild(new Field("FR Name", Type.String, true, this, "new Function Requirement"));
		tempField.addChild(new Field("FR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("FR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("FR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_functionReq.addChild(tempField);
	}
	
	/**
	 * Delete a Function Requirement from the Project
	 * 
	 * @param fReqToDelete
	 *            Field of the Requirement to delete
	 */
	public void deleteFunctionRequirement(final Field fReqToDelete) {
		if (_functionReq.contains(fReqToDelete)) {
			_functionReq.removeChild(fReqToDelete);
		}
	}
	
	/**
	 * Returns the field containing all Function Requirements as Children
	 * 
	 * @return Field
	 */
	public Field getFunctionRequirements() {
		return _functionReq;
	}
	
	/**
	 * add a Data Requirement to the Project
	 * 
	 * @param requirementID
	 *            ID for the Requirement
	 */
	public void addDataRequirement(final String requirementID) {
		Field tempField = new Field("DataReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("ID", Type.String, true, this, requirementID));
		tempField.addChild(new Field("DR Name", Type.String, true, this, "new Data Requirement"));
		tempField.addChild(new Field("DR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("DR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("DR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_dataReq.addChild(tempField);
	}
	
	/**
	 * Delete a Data Requirement from the Project
	 * 
	 * @param fReqToDelete
	 *            Field of the Requirement to delete
	 */
	public void deleteDataRequirement(final Field dReqToDelete) {
		if (_dataReq.contains(dReqToDelete)) {
			_dataReq.removeChild(dReqToDelete);
		}
	}
	
	/**
	 * Returns the field containing all Data Requirements as Children
	 * 
	 * @return Field
	 */
	public Field getDataRequirements() {
		return _dataReq;
	}
	
	/**
	 * add a Performance Requirement to the Project
	 * 
	 * @param requirementID
	 *            ID for the Requirement
	 */
	public void addPerformanceRequirement(final String requirementID) {
		Field tempField = new Field("PerformanceReq", Type.String, true, this, requirementID);
		tempField.addChild(new Field("ID", Type.String, true, this, requirementID));
		tempField.addChild(new Field("PR Name", Type.String, true, this, "new Performance Requirement"));
		tempField.addChild(new Field("PR Description", Type.Text, true, this, ""));
		tempField.addChild(new Field("PR Complexity", Type.ComplexityEnum, true, this, ComplexityEnum.Easy));
		tempField.addChild(new Field("PR FP Type", Type.FunctionPointEnum, true, this, FunctionPointEnum.Query));
		_performanceReq.addChild(tempField);
	}
	
	/**
	 * Delete a Performance Requirement from the Project
	 * 
	 * @param fReqToDelete
	 *            Field of the Requirement to delete
	 */
	public void deletePerformanceRequirement(final Field dReqToDelete) {
		if (_performanceReq.contains(dReqToDelete)) {
			_performanceReq.removeChild(dReqToDelete);
		}
	}
	
	/**
	 * Returns the field containing all Performance Requirements as Children
	 * 
	 * @return Field
	 */
	public Field getPerformanceRequirements() {
		return _performanceReq;
	}
	
	/**
	 * Add a Glossary entry with the given Keyword and Description.
	 * 
	 * @param keyword
	 *            Keyword for the Entry
	 * @param description
	 *            Description to assign to this Entry
	 */
	public void addGlossaryEntry(final String keyword, final String description) {
		Field tempField = new Field("Glossary Entry", Type.String, false, this, "Glossareintrag");
		tempField.addChild(new Field("Key", Type.String, true, this, keyword));
		tempField.addChild(new Field("Description", Type.Text, true, this, description));
		_glossary.addChild(tempField);
	}
	
	/**
	 * Delete a glossary Entry
	 * 
	 * @param entryToDelete
	 *            Field corresponding to the entry to be deleted
	 */
	public void deleteGlossaryEntry(final Field entryToDelete) {
		if (_glossary.contains(entryToDelete)) {
			_glossary.removeChild(entryToDelete);
		}
	}
	
	/**
	 * Returns the field containing all FunctionPoint Calculation Parameters as
	 * Children
	 * 
	 * @return Field
	 */
	public Field getFPParameters() {
		return _fpParameters;
	}
	
	/**
	 * Set the Calculation Method to be used.
	 * 
	 * @param calcMethodEnum
	 *            Enum of the Calculation Type
	 * @param calcMethodInstance
	 *            Instance to be used
	 */
	public void setCalcMethod(final CalculationEnum calcMethodEnum, final AbstractCalculationMethod calcMethodInstance) {
		this._calcMethodEnum = calcMethodEnum;
		this._calcMethodInstance = calcMethodInstance;
	}
	
	
	/**
	 * Execute the calculation.
	 * 
	 * @return Field containing the calculation results
	 */
	public Field calculate() {
		if (_calcMethodInstance != null) {
			return _calcMethodInstance.calculate(this);
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Set the fields for an Empty Project. This is to be used with Import only.
	 * It filters the necessary fields from the list and assigns them to the
	 * correct variables.
	 * 
	 * @param fields
	 *            ArrayList of all Fields that were imported.
	 */
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
			else if (field.getName().equals("FP Influences")){
				_fpInfluences = field;
			}
			
		}
		
		
		// Calculation
		_calcMethodEnum = CalculationEnum.notSet;
		_calcMethodInstance = null;
		
	}
	
	private void initFPFields(){
		// FP Parameters
		_fpParameters = new Field("FP Parameters", Type.String, false, this, "FP Parameter");
		_fpInfluences = new Field("FP Influences", Type.String, false, this, "FP Einflussfaktoren");
		
		Field input = new Field("Input", Type.String, false, this, "Eingabedaten");
		input.addChild(new Field("", Type.String, false, this, "Eingabedaten"));
		input.addChild(new Field("Easy", Type.Float, true, this, 3));
		input.addChild(new Field("Medium", Type.Float, true, this, 4));
		input.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field query = new Field("Query", Type.String, false, this, "Abfragen");
		query.addChild(new Field("Query", Type.String, false, this, "Abfragen"));
		query.addChild(new Field("Easy", Type.Float, true, this, 3));
		query.addChild(new Field("Medium", Type.Float, true, this, 4));
		query.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field output = new Field("Output", Type.String, false, this, "Ausgaben");
		output.addChild(new Field("Output", Type.String, false, this, "Ausgaben"));
		output.addChild(new Field("Easy", Type.Float, true, this, 3));
		output.addChild(new Field("Medium", Type.Float, true, this, 4));
		output.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field data = new Field("Internal Data", Type.String, false, this, "Datenbestände");
		data.addChild(new Field("Internal Data", Type.String, false, this, "Datenbestände"));
		data.addChild(new Field("Easy", Type.Float, true, this, 3));
		data.addChild(new Field("Medium", Type.Float, true, this, 4));
		data.addChild(new Field("Complex", Type.Float, true, this, 6));
		
		Field reference = new Field("External Data", Type.String, false, this, "Referenzdaten");
		reference.addChild(new Field("External Data", Type.String, false, this, "Referenzdaten"));
		reference.addChild(new Field("Easy", Type.Float, true, this, 3));
		reference.addChild(new Field("Medium", Type.Float, true, this, 4));
		reference.addChild(new Field("Complex", Type.Float, true, this, 6));
		
			
		_fpParameters.addChild(input);
		_fpParameters.addChild(query);
		_fpParameters.addChild(output);
		_fpParameters.addChild(data);
		_fpParameters.addChild(reference);
		
		_projectFields.add(_fpParameters);
		
//				Field influences = new Field("Influences", Type.String, false, this, "Einflussfaktoren");
		
		_fpInfluences.addChild(new Field("Verflechtungen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Dezentrale Daten", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Transaktionsrate", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Rechenoperationen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Kontrollverfahren", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Ausnahmeregelungen", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Logik", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Wiederverwendbarkeit", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Datenbestandskonvertierung", Type.Float, true, this, 0));
		_fpInfluences.addChild(new Field("Anpassbarkeit", Type.Float, true, this, 0));
		
//				_fpInfluences.addChild(influences);
		
		_projectFields.add(_fpInfluences);
	}
	
}
