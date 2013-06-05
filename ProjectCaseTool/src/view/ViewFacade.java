package view;

import controller.Controller;

public class ViewFacade {

	private final Controller _controller;

	private MainWindow _mainView;

	/**
	 * @return the _mainView
	 */
	public MainWindow get_mainView() {
		return this._mainView;
	}

	public ViewFacade(Controller controller) {
		this._controller = controller;
	}

	/*
	 * opens the window and waits until the user responds
	 */
	public void init() {
		try {
			this._mainView = new MainWindow(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this._mainView.open();
	}

	/*
	 * @return the controller, for access more functions
	 */
	public Controller getController() {
		return this._controller;
	}

}
