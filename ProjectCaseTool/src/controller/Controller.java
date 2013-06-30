package controller;

import java.util.ArrayList;

import model.IModelFacade;
import model.ModelFacade;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import view.RequirementEditor;
import view.ResultViewer;
import view.ViewFacade;
import additional.CalculationEnum;
import additional.Field;

public class Controller {
	private static Controller			_instance;
	private final ViewFacade			_view;
	private final IModelFacade			_model;
	private Listener					_projectListListener;
	private int							_numNewProject;
	private final ArrayList<Field>		_availableProjectsInList	= new ArrayList<>();
	private SelectionListener			_projectSelectionListener;
	private final ArrayList<TabItem>	_openTabs					= new ArrayList<>();
	
	private Controller() {
		this._model = ModelFacade.getInstance();
		this._view = new ViewFacade(this);
		
		// must be the last command, since this will trap the program in a
		// infinite loop
		this._view.init();
	}
	
	public static Controller getInstance() {
		if (_instance == null) {
			_instance = new Controller();
		}
		return _instance;
	}
	
	public Listener getcreateProjectListener() {
		this._projectListListener = new Listener() {
			
			@Override
			public void handleEvent(final Event arg0) {
				Controller.this._numNewProject = Controller.this._numNewProject + 1;
				Controller.this._model.createProject("Unbenanntes Projekt "
						+ Controller.this._numNewProject);
				
				Controller.this.listProjects();
				
			}
			
		};
		return this._projectListListener;
	}
	
	private void listProjects() {
		ArrayList<ArrayList<Field>> _projectList = this._model
				.getAllProjectFields();
		this._view.get_mainView()._projectList.removeAll();
		this._availableProjectsInList.clear();
		if (_projectList.isEmpty()) {
			
		} else {
			for (ArrayList<Field> curProject : _projectList) {
				this._view.get_mainView()._projectList.add((curProject.get(0)
						.getValue()).toString());
				this._availableProjectsInList.add(curProject.get(0));
			}
			
		}
	}
	
	public SelectionListener getProjectSelectionListener() {
		this._projectSelectionListener = new SelectionListener() {
			
			@Override
			public void widgetSelected(final SelectionEvent arg0) {
				int selection = Controller.this._view.get_mainView()._projectList
						.getSelectionIndex();
				if (selection != -1) {
					Controller.this._view.get_mainView()._projectList.setItem(
							selection,
							(String) Controller.this._availableProjectsInList
									.get(selection).getValue());
					Controller.this._model
							.setCurrentProject(Controller.this._availableProjectsInList
									.get(selection));
					Controller.this.loadContentCurProject();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(final SelectionEvent arg0) {
				
			}
		};
		return this._projectSelectionListener;
	}
	
	private void loadContentCurProject() {
		
		int oldTab = 0;
		if (this._view.get_mainView()._tabFolder != null && !this._view.get_mainView()._tabFolder.isDisposed()) {
			oldTab = this._view.get_mainView()._tabFolder.getSelectionIndex();
		}
		
		this.deleteTabs();
		if (_model.getCurrentProjectFields() != null) {
			for (Field eachChapter : this._model.getCurrentProjectFields()) {
				TabItem chapterTab = new TabItem(
						this._view.get_mainView()._tabFolder, SWT.NONE);
				this._openTabs.add(chapterTab);
				String value = eachChapter.getName().toString();
				chapterTab.setText(value);
				this.loadChapterContents(eachChapter, chapterTab);
			}
		}
		
		
		if (oldTab > 0 && oldTab <= this._view.get_mainView()._tabFolder.getTabList().length) {
			this._view.get_mainView()._tabFolder.setSelection(oldTab);
		}
	}
	
	private void deleteTabs() {
		for (TabItem eachTab : this._openTabs) {
			eachTab.dispose();
		}
		this._openTabs.clear();
	}
	
	private void loadChapterContents(final Field field, final TabItem tab) {
		Composite tabComposite = new Composite(tab.getParent(), SWT.NONE);
		tab.setControl(tabComposite);
		
		
		if (field.getNumberOfChildren() == 0) {
			tabComposite.setLayout(new GridLayout(1, false));
			// Label description = new Label(tabComposite, SWT.NONE);
			// description.setText(field.getName().toString());
			// description.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
			// true,
			// true, 1, 1));
			Text value = new Text(tabComposite, SWT.MULTI | SWT.BORDER);
			value.setSize(300, 100);
			value.setText(field.getValue().toString());
			value.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			value.addListener(SWT.CHANGED, new FieldListener(field, value));
			// value.addVerifyListener(new
			// FilterListener(FilterListener.STRING));
			value.setEditable(true);
			
		} else {
			if (field.getNumberOfChildren() > 0) {
				
				// create a table to get a overview of everything
				tabComposite.setLayout(new GridLayout(1, false));
				final TableViewer tableviewer = new TableViewer(tabComposite,
						SWT.NONE);
				
				// to find out how many columns we need and how they should be
				// called
				// we look into the first child, e.g. Looking at the first
				// requirement and then determine how
				// many children there exist and what their names are.
				
				for (final Field column : (field.getChildren().get(0))
						.getChildren()) {
					
					
					TableViewerColumn tabCol = new TableViewerColumn(
							tableviewer, SWT.MULTI | SWT.FULL_SELECTION);
					tabCol.getColumn().setWidth(200);
					tabCol.getColumn().setText(column.getName().toString());
					tabCol.setLabelProvider(new ColumnLabelProvider() {
						
						
						@Override
						public String getText(final Object object) {
							return ((Field) object).getChildren()
									.get(field.getChildren().get(0).getChildren().indexOf(column)).getValue()
									.toString();
						}
					});
				}
				final Table table = tableviewer.getTable();
				table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				table.setHeaderVisible(true);
				table.setLinesVisible(true);
				tableviewer.setContentProvider(new ArrayContentProvider());
				tableviewer.setInput(field.getChildren());
				
				// Add a listener to open a new editor dialog with the
				// corresponding field
				table.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseDoubleClick(final MouseEvent arg0) {
						
						if (table.getSelectionIndex() < field.getChildren().size()) {
							Field selection = ((Field) tableviewer.getElementAt(table
									.getSelectionIndex()));
							if (selection != null) {
								new RequirementEditor(new Shell(Display.getDefault()),
										SWT.NONE, selection).open();
								loadContentCurProject();
							}
						}
					}
					
					@Override
					public void mouseDown(final MouseEvent arg0) {
						
					}
					
					@Override
					public void mouseUp(final MouseEvent arg0) {
						
					}
					
				});
				table.addKeyListener(new KeyListener() {
					
					@Override
					public void keyReleased(final KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(final KeyEvent arg0) {
						if (arg0.keyCode == SWT.DEL) {
							if (table.getSelectionIndex() < field.getChildren().size()) {
								Field selection = ((Field) tableviewer.getElementAt(table
										.getSelectionIndex()));
								if (selection != null) {
									deleteFromTable(selection);
								}
							}
						}
					}
				});
			}
		}
		
	}
	
	private void deleteFromTable(final Field fieldToDelete) {
		if (fieldToDelete.getName() == "FunctionReq") {
			this._model.deleteFunctionRequirement(fieldToDelete);
		}
		else if (fieldToDelete.getName() == "DataReq") {
			this._model.deleteDataRequirement(fieldToDelete);
		}
		else if (fieldToDelete.getName() == "PerformanceReq") {
			this._model.deletePerformanceRequirement(fieldToDelete);
		}
		else if (fieldToDelete.getName() == "Glossary Entry") {
			this._model.deleteGlossaryEntry(fieldToDelete);
		}
		loadContentCurProject();
	}
	
	/*
	 * Counters and Functions for enabling support for Adding additional
	 * entries...
	 */
	private Integer	_idFuncRec		= 0;
	private Integer	_idPerformance	= 0;
	private Integer	_idData			= 0;
	
	
	public void addRequirement() {
		this._model.addFunctionRequirement(Integer
				.toString(this._idFuncRec++ * 10));
		this.loadContentCurProject();
	}
	
	public void deleteCurProject() {
		this._model.deleteCurrentProject();
		this.loadContentCurProject();
		this.listProjects();
	}
	
	public void openProject(final String path) {
		this._model.loadProject(path);
		this.listProjects();
		
	}
	
	public void saveToXML(final String path) {
		this._model.saveProject(path);
	}
	
	public void addPerformanceReq() {
		this._model.addPerformanceRequirement(Integer
				.toString(this._idPerformance++ * 10));
		this.loadContentCurProject();
	}
	
	public void addDataReq() {
		this._model.addDataRequirement(Integer.toString(this._idData++ * 10));
		this.loadContentCurProject();
	}
	
	public void addGlossary(final String text, final String text2) {
		this._model.addGlossaryEntry(text, text2);
		this.loadContentCurProject();
	}

	public void showFPcalc() {
		/* Check which calculation method is currently set. Current: Only FP implemented */
		this._model.setCalculationMethod(CalculationEnum.FunctionPoint);
		if(this._model.getCalculationMethod() == CalculationEnum.FunctionPoint){
			Field results = this._model.calculate();
			ResultViewer rv = new ResultViewer(new Shell(Display.getDefault()), results);
			rv.open();
		} 

	}
	
}
