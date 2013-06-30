package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import additional.Field;

public class ResultViewer extends Dialog {

	protected Field _results;
	protected Shell _shlResultViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ResultViewer(final Shell parent, final Field result) {
		super(parent); // , SWT.NONE);
		this.setText("Function Point Ergebnisse");
		this._results = result;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public void open() {
		this.createContents();
		this._shlResultViewer.open();
		this._shlResultViewer.layout();
		Display display = this.getParent().getDisplay();
		while (!this._shlResultViewer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		this._shlResultViewer = new Shell();
		this._shlResultViewer.setSize(533, 461);
		this._shlResultViewer.setText("Function Point Ergebnisse");
		this._shlResultViewer.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this._shlResultViewer, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		for (Field result : this._results.getChildren()) {
			Label lblName = new Label(composite, SWT.NONE);
			lblName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			lblName.setText(result.getName());

			Label lblValue = new Label(composite, SWT.NONE);
			lblValue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			lblValue.setText(result.getValue().toString());

		}
		Composite composite_1 = new Composite(this._shlResultViewer, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnExit = new Button(composite_1, SWT.NONE);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(final MouseEvent e) {
				ResultViewer.this._shlResultViewer.dispose();
				// Controller.getInstance().loadContentCurProject();
			}
		});
		btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		btnExit.setText("schlieﬂen");
		new Label(composite_1, SWT.NONE);

	}
}
