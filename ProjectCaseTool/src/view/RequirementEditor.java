package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;
import additional.Type;
import controller.FieldListener;

public class RequirementEditor extends Dialog {
	
	protected Object	_result;
	protected Shell		_shlAnforderungBearbeiten;
	private Button		_btnConfirm;
	private final Field	_Requirement;
	
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public RequirementEditor(final Shell parent, final int style, final Field requirement) {
		super(parent, style);
		this.setText("Requirement bearbeiten");
		this._Requirement = requirement;
	}
	
	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		this.createContents();
		this._shlAnforderungBearbeiten.open();
		this._shlAnforderungBearbeiten.layout();
		Display display = this.getParent().getDisplay();
		while (!this._shlAnforderungBearbeiten.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return this._result;
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		this._shlAnforderungBearbeiten = new Shell(this.getParent(),
				this.getStyle());
		this._shlAnforderungBearbeiten.setSize(533, 461);
		this._shlAnforderungBearbeiten.setText("Anforderung bearbeiten");
		this._shlAnforderungBearbeiten.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this._shlAnforderungBearbeiten,
				SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		for (Field property : this._Requirement.getChildren()) {
			if(property.getType() == Type.ComplexityEnum){
				Label label = new Label(composite, SWT.NONE);
				label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				label.setText(property.getName());
				
				Composite togglecomposite = new Composite(composite,
						SWT.NONE);
				togglecomposite.setLayout(new GridLayout(3, false));
				togglecomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
						1));
				
				Button b1 = new Button (togglecomposite, SWT.RADIO);
				b1.setText(ComplexityEnum.Easy.toString());
				b1.addListener(SWT.Selection, new FieldListener(property, b1));
				
				Button b2 = new Button (togglecomposite, SWT.RADIO);
				b2.setText(ComplexityEnum.Medium.toString());
				b2.addListener(SWT.Selection, new FieldListener(property, b2));
				
				Button b3 = new Button (togglecomposite, SWT.RADIO);
				b3.setText(ComplexityEnum.Complex.toString());
				b3.addListener(SWT.Selection, new FieldListener(property, b3));
				
				switch ((ComplexityEnum) property.getValue()){
					case Easy: 
						b1.setSelection(true);
						break;
					case Medium: 
						b2.setSelection(true);
						break;
					case Complex: 
						b3.setSelection(true);
						break;
					default: 
						break;
				}
				
			}
			else if(property.getType() == Type.FunctionPointEnum){
				Label label = new Label(composite, SWT.NONE);
				label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				label.setText(property.getName());
				
				Composite togglecomposite = new Composite(composite,
						SWT.NONE);
				togglecomposite.setLayout(new GridLayout(5, false));
				togglecomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
						1));
				
				Button b1 = new Button (togglecomposite, SWT.RADIO);
				b1.setText(FunctionPointEnum.Input.toString());
				b1.addListener(SWT.Selection, new FieldListener(property, b1));
				
				Button b2 = new Button (togglecomposite, SWT.RADIO);
				b2.setText(FunctionPointEnum.Output.toString());
				b2.addListener(SWT.Selection, new FieldListener(property, b2));
				
				Button b3 = new Button (togglecomposite, SWT.RADIO);
				b3.setText(FunctionPointEnum.InternalData.toString());
				b3.addListener(SWT.Selection, new FieldListener(property, b3));
				
				Button b4 = new Button (togglecomposite, SWT.RADIO);
				b4.setText(FunctionPointEnum.ExternalData.toString());
				b4.addListener(SWT.Selection, new FieldListener(property, b4));
				
				Button b5 = new Button (togglecomposite, SWT.RADIO);
				b5.setText(FunctionPointEnum.Query.toString());
				b5.addListener(SWT.Selection, new FieldListener(property, b5));
				
				switch ((FunctionPointEnum) property.getValue()){
				case Input: 
					b1.setSelection(true);
					break;
				case Output: 
					b2.setSelection(true);
					break;
				case InternalData: 
					b3.setSelection(true);
					break;
				case ExternalData:
					b4.setSelection(true);
					break;
				case Query:
					b5.setSelection(true);
					break;
				default: 
					break;
				}
					
			}
			else {
				Label label = new Label(composite, SWT.NONE);
				label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				label.setText(property.getName());
				Text edit = new Text(composite, SWT.NONE);
				edit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				edit.setText(property.getValue().toString());
				edit.addListener(SWT.CHANGED, new FieldListener(property, edit));
				// edit.addVerifyListener(new
				// FilterListener(FilterListener.STRING));
			}

		}
		Composite composite_1 = new Composite(this._shlAnforderungBearbeiten,
				SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		
		this._btnConfirm = new Button(composite_1, SWT.NONE);
		this._btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(final MouseEvent e) {
				RequirementEditor.this._shlAnforderungBearbeiten.dispose();
				// Controller.getInstance().loadContentCurProject();
			}
		});
		this._btnConfirm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		this._btnConfirm.setText("fertig");
		new Label(composite_1, SWT.NONE);
		
	}
}
