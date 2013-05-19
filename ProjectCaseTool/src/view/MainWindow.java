package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;

import controller.Controller;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Label;

public class MainWindow {
	
//	public static void main(String[] args) {
//		try {
//			MainWindow window = new MainWindow(new ViewFacade(new Controller()));
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	protected Shell shlCase;
	private ViewFacade _viewFacade;
	
	
	/*Gui Objects, defined here for easier access */

	public List _projectList;
	

	
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
	 */
	protected void createContents() {
		shlCase = new Shell();
		shlCase.setSize(1000, 600);
		shlCase.setText("CASETOOL");
		shlCase.setLayout(new GridLayout(2, false));
		
		_projectList = new List(shlCase, SWT.BORDER);
		GridData gd__projectList = new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1);
		gd__projectList.widthHint = 207;
		_projectList.setLayoutData(gd__projectList);
		_projectList.addSelectionListener(_viewFacade.getController().getProjectSelectionListener());

		
		TabFolder tabFolder = new TabFolder(shlCase, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		

		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Zielbestimmung");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Produktanforderungen");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_3);
		composite_3.setLayout(new GridLayout(1, false));
		
		TabItem tbtmProduktdaten = new TabItem(tabFolder, SWT.NONE);
		tbtmProduktdaten.setText("ProduktDaten");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmProduktdaten.setControl(composite_4);
		composite_4.setLayout(new GridLayout(1, false));
		
		TabItem tbtmProduktleistungen = new TabItem(tabFolder, SWT.NONE);
		tbtmProduktleistungen.setText("Produktleistungen");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmProduktleistungen.setControl(composite_5);
		composite_5.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shlCase, SWT.BAR);
		shlCase.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu menu_1 = new Menu(mntmDatei);
		mntmDatei.setMenu(menu_1);
		
		MenuItem mntmNeuesProjektAnlegen = new MenuItem(menu_1, SWT.NONE);
		mntmNeuesProjektAnlegen.setText("Neues Projekt anlegen");
		
	    mntmNeuesProjektAnlegen.addListener(SWT.Selection, _viewFacade.getController().getcreateProjectListener());
		
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
		
		
	}
}
