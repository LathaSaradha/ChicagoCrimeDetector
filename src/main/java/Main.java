import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    Button predictCrimesButton;
    Button topFiveArrestsButton;
    Button buttonForDistrict;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Crime Detector");
        //Predict Crimes Using Linear Regression
       // int predictedCrimes = PredictCrimeInNextYear.predictTotalCrimes();
       // String message = "Total Number of Predicted Crimes in 2020 are "+predictedCrimes;
        predictCrimesButton = new Button("Predict Total Number of Crimes in 2020");
        topFiveArrestsButton = new Button("View Arrests For Top Five Crimes Types");
        buttonForDistrict = new Button ("Find crime percentage in a district and year");

        StackPane layout = new StackPane();
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(predictCrimesButton,topFiveArrestsButton,buttonForDistrict);
        layout.getChildren().add(vBox);
        layout.setAlignment(Pos.CENTER);




        predictCrimesButton.setOnAction(e->PredictionChart.display());
        topFiveArrestsButton.setOnAction(e->ArrestsPieChart.display());


        buttonForDistrict.setOnAction(e->PredictionChart.districtPercentage());

        Scene scene = new Scene(layout,300,100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
