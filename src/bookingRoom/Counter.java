package bookingRoom;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Add notify from observable class.
 * 
 * @author Narisa and Tanasorn
 *
 */
public class Counter implements Observer {

	private Total total = Total.getinstance();

	String concat = "";

	private TextArea area;
	private Stage stage;

	public Counter() {
		stage = new Stage();
		area = new TextArea();
		area.setEditable(false);
		HBox box = new HBox();
		box.setPrefSize(400, 500);
		box.setPadding(new Insets(10));
		box.setAlignment(Pos.TOP_LEFT);
		box.getChildren().add(area);
		Scene scene = new Scene(box);
		stage.setScene(scene);
	}

	/** Show scene on stage. */
	public void showAdd() {
		stage.show();
	}

	public void close() {
		stage.close();
	}

	@Override
	public void update(Observable o, Object arg) {

		if (total.countBreakfast() > 0)
			area.setText(concat = String.format("add Breakfast x%d\n", total.countBreakfast()));
		if (total.countExtraBed() > 0)
			area.setText(concat += String.format("add Extra bed x%d\n", total.countExtraBed()));
		for (int i : total.getCostRoom()) {
			if (i == 2000)
				area.setText(concat += "add standard room\n");
			if (i == 2500)
				area.setText(concat += "add superior room\n");
			if (i == 3000)
				area.setText(concat += "add deluxe room\n");
			if (i == 3500)
				area.setText(concat += "add suite room\n");
		}

	}
}