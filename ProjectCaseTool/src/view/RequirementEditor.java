package view;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

import additional.Field;

public class RequirementEditor extends Dialog {

	protected Object result;
	protected Shell shlAnforderungBearbeiten;
	private Button btnConfirm;
	private Button btnCancelEdit;
	private Field Requirement;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RequirementEditor(Shell parent, int style, Field requirement) {
		super(parent, style);
		setText("Requirement bearbeiten");
		this.Requirement = requirement;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAnforderungBearbeiten.open();
		shlAnforderungBearbeiten.layout();
		Display display = getParent().getDisplay();
		while (!shlAnforderungBearbeiten.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAnforderungBearbeiten = new Shell(getParent(), getStyle());
		shlAnforderungBearbeiten.setSize(533, 461);
		shlAnforderungBearbeiten.setText("Anforderung bearbeiten");
		shlAnforderungBearbeiten.setLayout(new GridLayout(1, false));
		
		
		
		Composite composite = new Composite(shlAnforderungBearbeiten, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		for (Field property : this.Requirement.getChildren()){
			Label label = new Label(composite, SWT.NONE);
			label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
			label.setText(property.getName());
			Text edit = new Text(composite, SWT.NONE);
			edit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
			edit.setText(property.getValue().toString());
		}
		Composite composite_1 = new Composite(shlAnforderungBearbeiten, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnCancelEdit= new Button(composite_1, SWT.NONE);
		btnCancelEdit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnCancelEdit.setText("\u00C4nderung best\u00E4tigen");
		
		btnConfirm = new Button(composite_1, SWT.NONE);
		btnConfirm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnConfirm.setText("\u00C4nderung best\u00E4tigen");

	}
}
