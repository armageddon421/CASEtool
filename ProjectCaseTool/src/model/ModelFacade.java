package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;


public class ModelFacade implements IModelFacade {

	private static ModelFacade _modelFacadeInstance;
	private Data _dataContainer = Data.getInstance();
	private CalculationEnum _activeCalculationMethod;
	private CalculationFactory _calcFactory;
	
	private ModelFacade () {
		_activeCalculationMethod = CalculationEnum.FunctionPoint;
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
		return _dataContainer.getCurrentProjectFields();
	}

	@Override
	public ArrayList<ArrayList<Field>> getAllProjectFields() {
		return _dataContainer.getAllProjectFields();
	}

	@Override
	public ArrayList<ArrayList<Field>> getFunctionRequirementFields() {
		return _dataContainer.getFunctionRequirementFields();
	}

	@Override
	public ArrayList<ArrayList<Field>> getDataRequirementFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArrayList<Field>> getPerformanceRequirementFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCalculationMethod(CalculationEnum calcMethod) {
		_activeCalculationMethod = calcMethod;
	}

	//TODO
	@Override
	public ArrayList<Field> calculate() {
		AbstractCalculationMethod _calculationMethod = _calcFactory.createCalculationMethod(_activeCalculationMethod);
		return null ;
	}

	@Override
	public CalculationEnum getCalculationMethod() {
		return _activeCalculationMethod;
	}

	@Override
	public void createProject(String projectName) {
		_dataContainer.createProject(projectName);
	}
	
	
}
