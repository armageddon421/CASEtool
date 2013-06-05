package model;

import java.util.ArrayList;

import additional.CalculationEnum;
import additional.Field;


/**
 * Facade for the model
 * 
 * @author Robert
 * 
 */
public class ModelFacade implements IModelFacade {
	
	private static ModelFacade			_modelFacadeInstance;
	private final Data					_dataContainer	= Data.getInstance();
	private final CalculationFactory	_calcFactory;
	private final IExport				_exporter;
	private final IImport				_importer;
	
	/**
	 * Constructor, prepares Calculation Factory and Import/Export classes
	 */
	private ModelFacade() {
		_calcFactory = new CalculationFactory();
		XML ietemp = new XML();
		// same class for Import and Export
		_exporter = ietemp;
		_importer = ietemp;
	}
	
	/**
	 * Gets and eventually creates the instance of this singleton class
	 * 
	 * @return singleton for this class
	 */
	public static ModelFacade getInstance() {
		if (_modelFacadeInstance == null) {
			_modelFacadeInstance = new ModelFacade();
		}
		return _modelFacadeInstance;
	}
	
	@Override
	public void loadProject(final String filepath) {
		this._importer.importProject(filepath, this._dataContainer.createProjectForImport());
	}
	
	@Override
	public void saveProject(final String filepath) {
		Project exportProject = this._dataContainer.getCurrentProject();
		if (exportProject != null) {
			this._exporter.exportProject(filepath, exportProject);
		}
	}
	
	@Override
	public void setCurrentProject(final Field fieldOfProject) {
		_dataContainer.setCurrentProject(fieldOfProject);
	}
	
	@Override
	public ArrayList<Field> getCurrentProjectFields() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().getFields();
		}
		else {
			return null;
		}
	}
	
	@Override
	public ArrayList<ArrayList<Field>> getAllProjectFields() {
		return _dataContainer.getAllProjectFields();
	}
	
	@Override
	public void setCalculationMethod(final CalculationEnum calcMethod) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().setCalcMethod(calcMethod,
					_calcFactory.createCalculationMethod(calcMethod));
		}
	}
	
	@Override
	public Field calculate() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().calculate();
		}
		else {
			return null;
		}
	}
	
	@Override
	public CalculationEnum getCalculationMethod() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().getCalcMethod();
		}
		else {
			return null;
		}
	}
	
	@Override
	public void createProject(final String projectName) {
		_dataContainer.createProject(projectName);
	}
	
	@Override
	public void deleteCurrentProject() {
		_dataContainer.deleteCurrentProject();
	}
	
	@Override
	public void deleteProject(final Field fieldOfProject) {
		_dataContainer.deleteProject(fieldOfProject);
	}
	
	@Override
	public Field getFunctionRequirements() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().getFunctionRequirements();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Field getDataRequirements() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().getDataRequirements();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Field getPerformanceRequirements() {
		if (_dataContainer.getCurrentProject() != null) {
			return _dataContainer.getCurrentProject().getPerformanceRequirements();
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public void addFunctionRequirement(final String id) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().addFunctionRequirement(id);
		}
	}
	
	@Override
	public void addDataRequirement(final String id) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().addDataRequirement(id);
		}
	}
	
	@Override
	public void addPerformanceRequirement(final String id) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().addPerformanceRequirement(id);
		}
	}
	
	@Override
	public void addGlossaryEntry(final String keyword, final String description) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().addGlossaryEntry(keyword, description);
		}
	}
	
	@Override
	public void deleteFunctionRequirement(final Field fReqToDelete) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().deleteFunctionRequirement(fReqToDelete);
		}
	}
	
	@Override
	public void deleteDataRequirement(final Field dReqToDelete) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().deleteDataRequirement(dReqToDelete);
		}
	}
	
	@Override
	public void deletePerformanceRequirement(final Field pReqToDelete) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().deletePerformanceRequirement(pReqToDelete);
		}
	}
	
	@Override
	public void deleteGlossaryEntry(final Field entryToDelete) {
		if (_dataContainer.getCurrentProject() != null) {
			_dataContainer.getCurrentProject().deleteGlossaryEntry(entryToDelete);
		}
	}
	
	
}
