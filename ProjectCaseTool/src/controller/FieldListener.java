package controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;
import additional.Type;

public class FieldListener implements Listener {
	
	private final Field		_attachedField;
	private final Widget	_observedWidget;
	
	
	public FieldListener(final Field attachedField, final Widget observedWidget) {
		super();
		this._attachedField = attachedField;
		this._observedWidget = observedWidget;
	}
	
	
	@Override
	public void handleEvent(final Event arg0) {
		
		Class<? extends Widget> typeWidget = _observedWidget.getClass();
		
		switch (typeWidget.getName()) {
			case "org.eclipse.swt.widgets.Text":
				if(_attachedField.getType() == Type.Float){
					try{
						float value = Float.parseFloat(((Text) _observedWidget).getText());
						_attachedField.setValue(value);
					}
					catch (NumberFormatException e){
					}
				}
				else if(_attachedField.getType() == Type.Integer){
					try{
						int value = Integer.parseInt(((Text) _observedWidget).getText());
						_attachedField.setValue(value);
					}
					catch (NumberFormatException e){						
					}
				}
				else {
					_attachedField.setValue(((Text) _observedWidget).getText());
				}
				break;
				
			case "org.eclipse.swt.widgets.Button":
				if(_attachedField.getType() == Type.ComplexityEnum){
					_attachedField.setValue(ComplexityEnum.valueOf(((Button) _observedWidget).getText()));
				}
				else if(_attachedField.getType() == Type.FunctionPointEnum){
					_attachedField.setValue(FunctionPointEnum.valueOf(((Button) _observedWidget).getText()));
				}
				break;
			
			default:
				break;
		}
		
	}
	
}
