import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {
    Button button;
    Button button1;
    public static void main(String[] args) {
        launch(args);
        FileReader fr= new FileReader("Crimes2018.csv");

        //fr.timeOfCrime();
        //fr.arrestRate();
        fr.CrimeTypeInCommunityArea("43");

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Crime Detector");
        button = new Button();
        button.setText("Percentage of Last Week Crimes");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label label = new Label();
                label.setText("Crime");
                StackPane layout = new StackPane();
                layout.getChildren().add(button);
                InputHBox hb= new InputHBox();
                layout.getChildren().add(hb);
                FileReader fr= new FileReader("Crimes2018.csv");
                hb.addMoveButtonAction(fr);
            }
        });
        StackPane layout = new StackPane();
        //button1 = new Button("Zipcode");
        //button.setLayoutX(500);
        //button.setLayoutY(530);
        //button1.setLayoutX(500);
        //button1.setLayoutY(630);
        layout.getChildren().add(button);

        //layout.getChildren().add(button1);

        Scene scene = new Scene(layout,300,250);
        primaryStage.setScene(scene);
       primaryStage.show();

        FileReader fr= new FileReader("Crimes2018.csv");
        fr.readFile();

      InputHBox hb= new InputHBox();
      layout.getChildren().add(hb);
      hb.addMoveButtonAction(fr);

    }
}
