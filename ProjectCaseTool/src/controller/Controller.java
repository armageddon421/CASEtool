package controller;

import java.util.ArrayList;

import javax.swing.text.View;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import additional.Field;


import model.IModelFacade;
import model.ModelFacade;
import view.ViewFacade;

public class Controller {
	
	private ViewFacade _view;
	private IModelFacade _model;
	
	
	public Controller() {
		this._view = new ViewFacade(this);
		this._model = ModelFacade.getInstance() ;
	}
	
	
	public Listener getcreateProjectListener(){
		Listener  _projectListListener = new Listener() {

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
					_view.get_mainView()._projectList.add(curProject.get(0).toString());
			}

		}
	}
	
	


}
