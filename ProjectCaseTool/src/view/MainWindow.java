package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.SashForm;

public class MainWindow {
	
	protected Shell shlCase;
	private ViewFacade _viewFacade;
	
	
	/*Gui Objects, defined here for easier access */

	public List _projectList;
	public TabFolder tabFolder;

	

	public MainWindow(ViewFacade viewFacade) {
		super();
		this._viewFacade =viewFacade;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCase.open();
		shlCase.layout();
		while (!shlCase.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlCase = new Shell();
		shlCase.setSize(1000, 600);
		shlCase.setText("CASETOOL");
		shlCase.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shlCase, SWT.BAR);
		shlCase.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem mntmNeuesProjektAnlegen = new MenuItem(menu_1, SWT.NONE);
		mntmNeuesProjektAnlegen.setText("Neues Projekt anlegen");
		
	    mntmNeuesProjektAnlegen.addListener(SWT.Selection, _viewFacade.getController().getcreateProjectListener());
		
		MenuItem mntmGeffnetesProjektLschen = new MenuItem(menu_1, SWT.NONE);
		mntmGeffnetesProjektLschen.setText("ge\u00F6ffnetes Projekt l\u00F6schen");
		
		MenuItem mntmProjektffnen = new MenuItem(menu_1, SWT.NONE);
		mntmProjektffnen.setText("Projekt \u00F6ffnen");
		
		MenuItem mntmProjektSchlieen = new MenuItem(menu_1, SWT.NONE);
		mntmProjektSchlieen.setText("Projekt speichern");
		
		MenuItem mntmExport = new MenuItem(menu_1, SWT.CASCADE);
		mntmExport.setText("XML");
		
		Menu menu_2 = new Menu(mntmExport);
		mntmExport.setMenu(menu_2);
		
		MenuItem mntmExport_1 = new MenuItem(menu_2, SWT.NONE);
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
				
		SashForm sashForm = new SashForm(shlCase, SWT.BORDER);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

				
		_projectList = new List(sashForm, SWT.BORDER);
				
						
		tabFolder = new TabFolder(sashForm, SWT.NONE);

		sashForm.setWeights(new int[] {154, 809});
		_projectList.addSelectionListener(_viewFacade.getController().getProjectSelectionListener());
		
		
	}
}
