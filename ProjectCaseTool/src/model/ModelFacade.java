package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;


public class ModelFacade implements IModelFacade {

	private static ModelFacade _modelFacadeInstance;
	private Data _dataContainer = Data.getInstance();
	private CalculationFactory _calcFactory;
	
	private ModelFacade () {
		_calcFactory = new CalculationFactory();
	}
	
	public static ModelFacade getInstance(){
		if(_modelFacadeInstance == null){
			_modelFacadeInstance = new ModelFacade();
		}
		return _modelFacadeInstance;
	}

	@Override
	public void loadProject(String filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveProject(String filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentProject(Field fieldOfProject) {
		_dataContainer.setCurrentProject(fieldOfProject);
	}

	@Override
	public ArrayList<Field> getCurrentProjectFields() {		
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().getFields();
		}
		else{
			return null;
		}
	}

	@Override
	public ArrayList<ArrayList<Field>> getAllProjectFields() {
		return _dataContainer.getAllProjectFields();
	}

	@Override
	public void setCalculationMethod(CalculationEnum calcMethod) {
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().setCalcMethod(calcMethod, _calcFactory.createCalculationMethod(calcMethod));
		}
	}

	@Override
	public Field calculate() {
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().calculate();
		}
		else{
			return null;
		}
	}

	@Override
	public CalculationEnum getCalculationMethod() {
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().getCalcMethod();
		}
		else{
			return null;
		}
	}

	@Override
	public void createProject(String projectName) {
		_dataContainer.createProject(projectName);
	}

	@Override
	public void deleteCurrentProject() {
		_dataContainer.deleteCurrentProject();
	}
	
	@Override
	public void deleteProject(Field fieldOfProject){
		_dataContainer.deleteProject(fieldOfProject);
	}

	@Override
	public Field getFunctionRequirements() {
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().getFunctionRequirements();
		}
		else{
			return null;
		}
	}

	@Override
	public Field getDataRequirements() {
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().getDataRequirements();
		}
		else{
			return null;
		}
	}

	@Override
	public Field getPerformanceRequirements() {
		if(_dataContainer.getCurrentProject() != null){
			return _dataContainer.getCurrentProject().getPerformanceRequirements();
		}
		else{
			return null;
		}
	}

	
	@Override
	public void addFunctionRequirement(String id){
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().addFunctionRequirement(id);
		}
	}
	
	@Override
	public void addDataRequirement(String id){
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().addDataRequirement(id);
		}
	}
	
	@Override
	public void addPerformanceRequirement(String id){
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().addPerformanceRequirement(id);
		}
	}
	
	@Override
	public void addGlossaryEntry(String keyword, String description){
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().addGlossaryEntry(keyword, description);
		}
	}
	
	@Override
	public void deleteFunctionRequirement(Field fReqToDelete) {
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().deleteFunctionRequirement(fReqToDelete);
		}
	}

	@Override
	public void deleteDataRequirement(Field dReqToDelete) {
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().deleteDataRequirement(dReqToDelete);
		}
	}

	@Override
	public void deletePerformanceRequirement(Field pReqToDelete) {
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().deletePerformanceRequirement(pReqToDelete);
		}
	}
	
	@Override
	public void deleteGlossaryEntry(Field entryToDelete){
		if(_dataContainer.getCurrentProject() != null){
			_dataContainer.getCurrentProject().deleteGlossaryEntry(entryToDelete);
		}
	}
	
	
}
