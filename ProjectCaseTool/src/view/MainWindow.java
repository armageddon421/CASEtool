package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabItem;

public class MainWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(563, 421);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("Datei");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmSpeichern = new MenuItem(menu_1, SWT.NONE);
		mntmSpeichern.setText("Speichern");
		
		MenuItem mntmSpeichernUnter = new MenuItem(menu_1, SWT.NONE);
		mntmSpeichernUnter.setText("Speichern unter");
		
		MenuItem mntmffnen = new MenuItem(menu_1, SWT.NONE);
		mntmffnen.setText("\u00D6ffnen");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmBeenden = new MenuItem(menu_1, SWT.NONE);
		mntmBeenden.setText("Beenden");
		
		MenuItem mntmProjekt = new MenuItem(menu, SWT.CASCADE);
		mntmProjekt.setText("Projekt");
		
		Menu menu_3 = new Menu(mntmProjekt);
		mntmProjekt.setMenu(menu_3);
		
		MenuItem mntmNeuesProjekt = new MenuItem(menu_3, SWT.NONE);
		mntmNeuesProjekt.setText("Neues Projekt");
		
		MenuItem mntmProjektLschen = new MenuItem(menu_3, SWT.NONE);
		mntmProjektLschen.setText("Projekt L\u00F6schen");
		
		MenuItem mntmExport = new MenuItem(menu_3, SWT.CASCADE);
		mntmExport.setText("Export");
		
		Menu menu_2 = new Menu(mntmExport);
		mntmExport.setMenu(menu_2);
		
		MenuItem mntmXml = new MenuItem(menu_2, SWT.NONE);
		mntmXml.setText("XML");
		
		MenuItem mntmPdf = new MenuItem(menu_2, SWT.NONE);
		mntmPdf.setText("PDF");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_tabFolder.heightHint = 92;
		gd_tabFolder.widthHint = 433;
		tabFolder.setLayoutData(gd_tabFolder);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		CTabFolder tabFolder_1 = new CTabFolder(shell, SWT.BORDER);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmTest = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmTest.setText("test");

	}
}
