package controller;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import additional.Field;

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
				_attachedField.setValue(((Text) _observedWidget).getText());
				break;
			
			default:
				break;
		}
		
	}
	
}
