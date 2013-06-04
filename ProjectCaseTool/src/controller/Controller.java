package controller;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
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
				int selection = _view.get_mainView()._projectList.getSelectionIndex();
				if(selection != -1){
					_view.get_mainView()._projectList.setItem(selection, (String) _availableProjectsInList.get(selection).getValue());
					_model.setCurrentProject(_availableProjectsInList.get(selection));
					loadContentCurProject();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
								
			}
		};
		return _projectSelectionListener;
	}
	
	
	
private void loadContentCurProject (){
	deleteTabs();
	for(Field eachChapter :_model.getCurrentProjectFields()){
		TabItem chapterTab = new TabItem(_view.get_mainView().tabFolder, SWT.NONE);
		_openTabs.add(chapterTab);
		String value = eachChapter.getValue().toString();
		chapterTab.setText(value);
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
	
	//only for Testpurpose, normally the if-clause should never come true
	if(field.getNumberOfChildren()==0){
		tabComposite.setLayout(new GridLayout(2, false));
		Label description = new Label(tabComposite, SWT.NONE);
		description.setText(field.getValue().toString());
		description.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 1));
		Text value = new Text(tabComposite, SWT.MULTI|SWT.BORDER);
		value.setSize(300, 100);
		value.setText(field.getValue().toString());
		value.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		value.addListener(SWT.CHANGED, new FieldListener(field, value));
		value.addVerifyListener(new FilterListener(FilterListener.STRING));
		
	}
	else{
		if(field.getNumberOfChildren() < 1){
			
		}
		
		//create a table to get a overview of everything
		else{
			tabComposite.setLayout(new GridLayout(1, false));
			TableViewer tableviewer = new TableViewer(tabComposite, SWT.NONE);
			
			
			//to find out how many columns we need and how they should be called
			//we look into the first child, e.g. Looking at the first requirement and then determine how
			//many children there exist and what their names are.
			
			for (Field column : (field.getChildren().get(0)).getChildren()){
				
				TableViewerColumn tabCol = new TableViewerColumn(tableviewer, SWT.NONE);
				tabCol.getColumn().setWidth(200);
				tabCol.getColumn().setText(column.getType().toString());
				tabCol.setLabelProvider(new ColumnLabelProvider());
			}
			Table table = tableviewer.getTable();
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			table.setHeaderVisible(true);
			table.setLinesVisible(true); 
			tableviewer.setContentProvider(new ArrayContentProvider());
			tableviewer.setInput(field.getChildren());
		}
	}
		
	}




///////////////////////DUMMY
private Integer idzaehler = 0;
public void addRequirement(){
	this._model.addFunctionRequirement(Integer.toString(idzaehler++*10));
	this.loadContentCurProject();
}




}



