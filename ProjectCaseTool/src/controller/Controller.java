package controller;

import java.util.ArrayList;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.Field;
import model.IModelFacade;
import model.ModelFacade;
import view.ViewFacade;

public class Controller {
	
	private ViewFacade _view;
	private IModelFacade _model;
	
	
	public Controller() {
		this._view = new ViewFacade(this);
		this._model = new ModelFacade() ;
	}
	
	
	public Listener getListProjectsListener(){
		Listener  _projectListListener = new Listener() {
		   	@Override
			public void handleEvent(Event arg0) {
				 ArrayList<ArrayList<Field>> _projectList = _model.getAllProjectFields();
		   		if (_projectList.isEmpty()){
		   			
		   		}
		   		else{
		   			for (ArrayList<Field>curProject : _projectList){
		   				/* Search for the names of the project */
		   				
		   			}

		   		}
			}
		};
		return _projectListListener;
	}


}
