package view;

import controller.Controller;

public class ViewFacade {

	private Controller _controller;
	
	
	private MainWindow _mainView;
	/**
	 * @return the _mainView
	 */
	public MainWindow get_mainView() {
		return _mainView;
	}	
	
	public ViewFacade(Controller controller) {
		this._controller = controller;		
	}
	public void init(){
		try {
			this._mainView = new MainWindow(this);
			} catch (Exception e) {
			e.printStackTrace();
		}
		_mainView.open();
	}
	
	public Controller getController(){
		return _controller;
	}
	

}
