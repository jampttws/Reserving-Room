package bookingRoom;

import javafx.stage.Stage;

/**
 * Superclass for fxml controller of a single view.
 * The fxml scene controller should extend this class.
 * 
 * @author Narisa Singngam
 *
 */
public class ViewController {
	private Stage stage = new Stage();
	private PageController controller = new PageController(stage);

	public PageController getController() {
		return controller;
	}

	public void setController(PageController controller) {
		this.controller = controller;
	}
}
