package app;

/*
* Java program to rename IMG, VID and DSC files into an appropriate name for a slideshow
*
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;import java.lang.Override;import java.lang.String;

public class RenameMyCameraPhotos extends Application {
	public static void main(String[] args) {
		launch(args);
	}
		@Override
		public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("rename_my_photos.fxml"));

			primaryStage.setTitle("Photo renaming!");



			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));

			Text scenetitle = new Text("Welcome");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Label location = new Label("Location:");
			grid.add(location, 0, 1);

			TextField userTextField = new TextField();
			grid.add(userTextField, 1, 1);

			Button btn = new Button();
			btn.setText("Rename my photos!");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn);
			grid.add(hbBtn, 1, 4);

			final Text actiontarget = new Text();
			grid.add(actiontarget, 1, 6);


			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Button pressed");
					new App().run("");
				}
			});

			Scene scene = new Scene(grid, 300, 275);
			primaryStage.setScene(scene);

			primaryStage.show();

		}
}
