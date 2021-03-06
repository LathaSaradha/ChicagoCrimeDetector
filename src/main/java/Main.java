import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Crime Detector");
        Stage stage = new Stage();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        VBox vBox2 = new VBox();
        vBox2.getChildren().add(progressIndicator);
        Scene scene2 = new Scene(vBox2, 300, 300);
        stage.setScene(scene2);
        //Predict Crimes Using Linear Regression


        Button predictCrimesButton = new Button("Predict Total Number of Crimes in 2020");
        Button topFiveArrestsButton = new Button("View Arrests For Top Five Crimes Types");
        Button buttonForDistrict = new Button("Find the crime percentage in a district and year");

        predictCrimesButton.setPrefWidth(270.0);
        topFiveArrestsButton.setPrefWidth(270.0);
        buttonForDistrict.setPrefWidth(270.0);

        StackPane layout = new StackPane();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(predictCrimesButton, topFiveArrestsButton, buttonForDistrict);
        layout.getChildren().add(vBox);
        layout.setAlignment(Pos.CENTER);


        predictCrimesButton.setOnAction(e -> PredictionChart.display());
        topFiveArrestsButton.setOnAction(e -> new ArrestsPieChart());
        buttonForDistrict.setOnAction(e -> CrimeRateInDistrictUI.display());

        Scene scene = new Scene(layout, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

    }
}
