package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GlossaryInput {
	private Text _text;
	private Text _text_1;
	private final ViewFacade _viewFacade;
	private final Shell shell;
	private final Display display = Display.getDefault();

	/**
	 * Launch the application.
	 */
	public void open() {
		try {
			this.shell.open();
			this.shell.layout();
			while (!this.shell.isDisposed()) {
				if (!this.display.readAndDispatch()) {
					this.display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public GlossaryInput(ViewFacade view) {

		this._viewFacade = view;
		this.shell = new Shell(this.display);
		this.createContents();

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		this.shell.setLayout(new GridLayout(2, false));

		Label lblBezeichnung = new Label(this.shell, SWT.NONE);
		lblBezeichnung.setText("Bezeichnung");

		this._text = new Text(this.shell, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 34;
		this._text.setLayoutData(gd_text);

		Label lblBeschreibung = new Label(this.shell, SWT.NONE);
		lblBeschreibung.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblBeschreibung.setText("Beschreibung");

		this._text_1 = new Text(this.shell, SWT.BORDER | SWT.MULTI);
		this._text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		new Label(this.shell, SWT.NONE);

		Composite composite = new Composite(this.shell, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GlossaryInput.this.shell.dispose();
			}
		});
		btnNewButton_1.setText("abbrechen");

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("best\u00E4tigen");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GlossaryInput.this._viewFacade.getController().addGlossary(
						GlossaryInput.this._text.getText(),
						GlossaryInput.this._text_1.getText());
				GlossaryInput.this.shell.dispose();
			}
		});
		this.shell.setText("SWT Application");
		this.shell.setSize(450, 300);
		this.open();

	}

}
