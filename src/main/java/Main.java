import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    Button button;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Crime Detector");
        //Predict Crimes Using Linear Regression
       // int predictedCrimes = PredictCrimeInNextYear.predictTotalCrimes();
       // String message = "Total Number of Predicted Crimes in 2020 are "+predictedCrimes;
        button = new Button("Predict Total Number of Crimes in 2020");

        Button buttonForDistrict = new Button ("To find the crime percentage in a district and year");

        StackPane layout = new StackPane();
        layout.getChildren().addAll(button,buttonForDistrict);
        layout.setAlignment(buttonForDistrict,Pos.BOTTOM_CENTER);
        layout.setAlignment(button,Pos.TOP_CENTER);


        button.setOnAction(e->PredictionChart.display());


        buttonForDistrict.setOnAction(e->PredictionChart.districtPercentage());

        Scene scene = new Scene(layout,300,250);
        primaryStage.setScene(scene);
       primaryStage.show();

        //Another input
//        FileReader fr= new FileReader("Crimes2018.csv");
//        // fr.readFile();
//
//        InputHBox hb= new InputHBox();
//        layout.getChildren().add(hb);
//        hb.addMoveButtonAction(fr);
//        //Input completed



    }
}
