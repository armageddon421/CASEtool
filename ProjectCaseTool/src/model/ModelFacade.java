package model;

import java.util.ArrayList;


public class ModelFacade implements IModelFacade {

	private Data _dataContainer = Data.getData();
	private Project _currentProject;
	private CalculationEnum _activeCalculationMethod;
	private CalculationFactory _calcFactory;
	
	public ModelFacade () {
		_activeCalculationMethod = CalculationEnum.FunctionPoint;
		_calcFactory = new CalculationFactory();
	}

	@Override
	public void loadProject(String _filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveProject(String _filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentProject(Field _fieldOfProject) {
		if(_fieldOfProject.getOwner() instanceof Project){
			_currentProject = (Project) _fieldOfProject.getOwner();
		}
	}

	@Override
	public ArrayList<Field> getCurrentProjectFields() {
		ArrayList<Field> _returnList = new ArrayList<>();
		if(_currentProject != null) {
			_returnList = _currentProject.getFields();
		} 
		else {
			
		}
		return _returnList;
	}

	@Override
	public ArrayList<ArrayList<Field>> getAllProjectFields() {
		return _dataContainer.getAllProjectFields();
	}

	@Override
	public ArrayList<ArrayList<Field>> getFunctionRequirementFields() {
		return _currentProject.getFunctionRequirements();
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
	public void setCalculationMethod(CalculationEnum _calcMethod) {
		_activeCalculationMethod = _calcMethod;
	}

	@Override
	public ArrayList<Field> calculate() {
		AbstractCalculationMethod _calculationMethod = _calcFactory.createCalculationMethod(_activeCalculationMethod);
		return _currentProject.calculate(_calculationMethod) ;
	}

	@Override
	public CalculationEnum getCalculationMethod() {
		return _activeCalculationMethod;
	}
	
	
}
