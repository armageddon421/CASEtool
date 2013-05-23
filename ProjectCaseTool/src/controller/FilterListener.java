/**
 * 
 */
package controller;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Raphael
 *
 */





public class FilterListener implements KeyListener {
	
	
	//Constants to determine which Characters are not valid as an Input
	public static final int INT = 1;
	public static final int STRING = 2;
	
	
	private int _filterMode;
	/**
	 * 
	 */
	public FilterListener(int filter) {
		this._filterMode = filter;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
