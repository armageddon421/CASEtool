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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainWindow {

	protected Shell shlCase;

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
		shlCase.setLayout(new GridLayout(1, false));
		
		TabFolder tabFolder = new TabFolder(shlCase, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmProjekt = new TabItem(tabFolder, SWT.NONE);
		tbtmProjekt.setText("Projekt");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmProjekt.setControl(composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 55, 15);
		lblNewLabel.setText("New Label");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Zielbestimmung");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		TabItem tbtmProdukteinsatz = new TabItem(tabFolder, SWT.NONE);
		tbtmProdukteinsatz.setText("Produkteinsatz");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmProdukteinsatz.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
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
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("Glossareintrag");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_6);
		composite_6.setLayout(new GridLayout(1, false));
		
		TabItem tbtmQualittsanforderung = new TabItem(tabFolder, SWT.NONE);
		tbtmQualittsanforderung.setText("Qualit\u00E4tsanforderung");
		
		Composite composite_7 = new Composite(tabFolder, SWT.NONE);
		tbtmQualittsanforderung.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		
		TabItem tbtmAufwandsschtzung = new TabItem(tabFolder, SWT.NONE);
		tbtmAufwandsschtzung.setText("Aufwandssch\u00E4tzung");
		
		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		tbtmAufwandsschtzung.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));

	}
}
