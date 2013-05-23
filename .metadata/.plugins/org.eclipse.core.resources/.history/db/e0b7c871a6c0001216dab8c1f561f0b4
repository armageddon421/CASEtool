package controller;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import additional.Field;

import model.IModelFacade;
import model.ModelFacade;
import view.ViewFacade;

public class Controller {
	
	private ViewFacade _view;
	private IModelFacade _model;
	private Listener _projectListListener;
	
	
	public Controller() {		
		this._model = ModelFacade.getInstance() ;
		this._view = new ViewFacade(this);
		this._view.init();
	}
	
	
	public Listener getcreateProjectListener(){
		_projectListListener = new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				_model.createProject("Hallo Welt");
				
				listProjects();
				
			}

			
		};
		return _projectListListener;
	}


	private void listProjects() {
		ArrayList<ArrayList<Field>> _projectList = _model.getAllProjectFields();
		if (_projectList.isEmpty()){
			
		}
		else{
			for (ArrayList<Field>curProject : _projectList){
					_view.get_mainView()._projectList.add((curProject.get(0).getValue()).toString());
			}

		}
	}
	
	


}
