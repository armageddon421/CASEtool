/**
 * 
 */
package controller;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Raphael
 *
 */





public class FilterListener implements VerifyListener {
	
	
	//Constants to determine which Characters are not valid as an Input
	public static final int INT = 1;
	public static final int STRING = 2;	
	
	private int _filterMode;
	
	public FilterListener(int type){
		this._filterMode = type;
	}



	@Override
	public void verifyText(VerifyEvent e) {
		// TODO Auto-generated method stub
		switch (_filterMode){
		case(FilterListener.INT):
								e.doit = e.text.matches("d+");
								break;
		}
		
	}

}
