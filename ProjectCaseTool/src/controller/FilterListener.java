/**
 * 
 */
package controller;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

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
		switch (_filterMode){
		case(FilterListener.INT):
								e.doit = e.text.matches("d+");
								break;
		default:
								break;
		}
		
	}

}
