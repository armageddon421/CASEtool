package controller;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import additional.Field;

import model.IModelFacade;
import model.ModelFacade;
import view.ViewFacade;

public class Controller {
	private static Controller _instance;
	private ViewFacade _view;
	private IModelFacade _model;
	private Listener _projectListListener;
	private int _numNewProject;
	private ArrayList<Field> _availableProjectsInList = new ArrayList<>();
	private SelectionListener _projectSelectionListener;
	private ArrayList<TabItem> _openTabs = new ArrayList<>();
	
	
	private Controller(){
		this._model = ModelFacade.getInstance() ;
		this._view = new ViewFacade(this);
		
		//must be the last command, since this will trap the program in a infinite loop
		this._view.init();
	}
	
	public static Controller getInstance() {
		if (_instance == null){
			_instance = new Controller();
		}
		return _instance;
	}
	
	
	public Listener getcreateProjectListener(){
		_projectListListener = new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				_numNewProject = _numNewProject +1;
				_model.createProject("Unbenanntes Projekt " + _numNewProject);
				
				listProjects();
				
			}

			
		};
		return _projectListListener;
	}
	
	


	private void listProjects() {
		ArrayList<ArrayList<Field>> _projectList = _model.getAllProjectFields();
		_view.get_mainView()._projectList.removeAll();
		_availableProjectsInList.clear();
		if (_projectList.isEmpty()){
			
		}
		else{
			for (ArrayList<Field>curProject : _projectList){
					_view.get_mainView()._projectList.add((curProject.get(0).getValue()).toString());
					_availableProjectsInList.add(curProject.get(0));
			}

		}
	}
	public SelectionListener getProjectSelectionListener (){
		_projectSelectionListener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				_model.setCurrentProject(_availableProjectsInList.get(_view.get_mainView()._projectList.getSelectionIndex()));
				//TODO System.out.println(_model.getCurrentProjectFields().get(0).getValue());
				loadContentCurProject();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		return _projectSelectionListener;
	}
	
	
	
private void loadContentCurProject (){
	deleteTabs();
	for(Field eachChapter :_model.getCurrentProjectFields()){
		TabItem chapterTab = new TabItem(_view.get_mainView().tabFolder, SWT.NONE);
		_openTabs.add(chapterTab);
		chapterTab.setText(eachChapter.getName().toString());
		loadChapterContents(eachChapter, chapterTab);
	}
}

private void deleteTabs(){
	for (TabItem eachTab : _openTabs){
		eachTab.dispose();
	}
	_openTabs.clear();
}

private void loadChapterContents(Field field, TabItem tab){
	Composite tabComposite = new Composite(tab.getParent(), SWT.NONE);
	tab.setControl(tabComposite);

	if(field.getNumberOfChildren()==0){
		tabComposite.setLayout(new GridLayout(2, false));
		Label description = new Label(tabComposite, SWT.NONE);
		description.setText(field.getName());
		description.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 1));
		Text value = new Text(tabComposite, SWT.MULTI|SWT.BORDER);
		value.setSize(300, 100);
		value.setText(field.getValue().toString());
		value.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		value.addListener(SWT.CHANGED, new FieldListener(field, value));
		
	}
	else{
		
	}
}


}
