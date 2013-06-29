package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

public class MainWindow {
	
	protected Shell	_shlCase;
	
	/**
	 * @return the _shlCase
	 */
	public Shell getshlCase() {
		return this._shlCase;
	}
	
	/**
	 * viewfacade is used to keep a link to the controller
	 */
	private final ViewFacade	_viewFacade;
	
	/* Gui Objects, defined here for easier access */
	
	public List					_projectList;
	public TabFolder			_tabFolder;
	
	public MainWindow(final ViewFacade viewFacade) {
		super();
		this._viewFacade = viewFacade;
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		this.createContents();
		this._shlCase.open();
		this._shlCase.layout();
		while (!this._shlCase.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		this._shlCase = new Shell();
		this._shlCase.setSize(1000, 600);
		this._shlCase.setText("CASETOOL");
		this._shlCase.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(this._shlCase, SWT.BAR);
		this._shlCase.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem mntmNeuesProjektAnlegen = new MenuItem(menu_1, SWT.NONE);
		mntmNeuesProjektAnlegen.setText("Neues Projekt anlegen");
		
		mntmNeuesProjektAnlegen.addListener(SWT.Selection, this._viewFacade
				.getController().getcreateProjectListener());
		
		MenuItem mntmGeffnetesProjektLschen = new MenuItem(menu_1, SWT.NONE);
		mntmGeffnetesProjektLschen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this._viewFacade.getController().deleteCurProject();
			}
		});
		mntmGeffnetesProjektLschen
				.setText("ge\u00F6ffnetes Projekt l\u00F6schen");
		
		MenuItem mntmProjektffnen = new MenuItem(menu_1, SWT.NONE);
		mntmProjektffnen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this.load();
			}
			
		});
		mntmProjektffnen.setText("Projekt \u00F6ffnen");
		
		MenuItem mntmProjektSchlieen = new MenuItem(menu_1, SWT.NONE);
		mntmProjektSchlieen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this.save();
			}
			
		});
		mntmProjektSchlieen.setText("Projekt speichern");
		
		MenuItem mntmExport = new MenuItem(menu_1, SWT.CASCADE);
		mntmExport.setText("XML");
		
		Menu menu_2 = new Menu(mntmExport);
		mntmExport.setMenu(menu_2);
		
		MenuItem mntmExport_1 = new MenuItem(menu_2, SWT.NONE);
		mntmExport_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this.save();
			}
		});
		mntmExport_1.setText("Export");
		
		MenuItem mntmImport = new MenuItem(menu_2, SWT.NONE);
		mntmImport.setText("Import");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmBeenden = new MenuItem(menu_1, SWT.NONE);
		mntmBeenden.setText("Beenden");
		
		MenuItem mntmProjekt = new MenuItem(menu, SWT.CASCADE);
		mntmProjekt.setText("Projekt");
		
		Menu menu_3 = new Menu(mntmProjekt);
		mntmProjekt.setMenu(menu_3);
		
		MenuItem mntmAufwandsmethodeAuswhlen = new MenuItem(menu_3, SWT.NONE);
		mntmAufwandsmethodeAuswhlen.setText("Aufwandsmethode ausw\u00E4hlen");
		
		MenuItem mntmAufwandBerechnen = new MenuItem(menu_3, SWT.NONE);
		mntmAufwandBerechnen.setText("Aufwand berechnen");
		
		MenuItem mntmDummy = new MenuItem(menu, SWT.CASCADE);
		mntmDummy.setText("Hinzuf\u00FCgen");
		
		Menu menu_4 = new Menu(mntmDummy);
		mntmDummy.setMenu(menu_4);
		
		MenuItem mntmNeuReq = new MenuItem(menu_4, SWT.NONE);
		mntmNeuReq.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this._viewFacade.getController().addRequirement();
			}
		});
		mntmNeuReq.setText("Produktfunktion");
		
		MenuItem mntmProduktleistung = new MenuItem(menu_4, SWT.NONE);
		mntmProduktleistung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this._viewFacade.getController().addPerformanceReq();
			}
		});
		mntmProduktleistung.setText("Produktleistung");
		
		MenuItem mntmProduktdatum = new MenuItem(menu_4, SWT.NONE);
		mntmProduktdatum.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MainWindow.this._viewFacade.getController().addDataReq();
			}
		});
		mntmProduktdatum.setText("Produktdatum");
		
		MenuItem mntmGlossareintrag = new MenuItem(menu_4, SWT.NONE);
		mntmGlossareintrag.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				new GlossaryInput(MainWindow.this._viewFacade);
			}
		});
		mntmGlossareintrag.setText("Glossareintrag");
		
		SashForm sashForm = new SashForm(this._shlCase, SWT.BORDER);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		
		this._projectList = new List(sashForm, SWT.BORDER);
		
		this._tabFolder = new TabFolder(sashForm, SWT.NONE);
				
		sashForm.setWeights(new int[] { 154, 809 });
		this._projectList.addSelectionListener(this._viewFacade.getController()
				.getProjectSelectionListener());
		
	}
	
	// Helper Method to save the current project in a XML File
	private void save() {
		String path = new FileDialog(MainWindow.this._shlCase, SWT.SAVE).open();
		if (path == null || path == "") {
			return;
		}
		MainWindow.this._viewFacade.getController().saveToXML(path);
	}
	
	// Helper Method to load a project from a XML File and make it to the
	// current project
	private void load() {
		String path = new FileDialog(MainWindow.this._shlCase, SWT.OPEN).open();
		if (path == null || path == "") {
			return;
		}
		MainWindow.this._viewFacade.getController().openProject(path);
	}
}
