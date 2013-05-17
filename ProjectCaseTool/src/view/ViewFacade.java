package view;

import controller.Controller;

public class ViewFacade {
	
	private MainWindow _mainView;
	/**
	 * @return the _mainView
	 */
	public MainWindow get_mainView() {
		return _mainView;
	}


	private Controller _controller;
	
	
	public ViewFacade(Controller controller) {
		
		try {
			this._mainView = new MainWindow(this);
			_mainView.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this._controller = controller;
	}
	

}
