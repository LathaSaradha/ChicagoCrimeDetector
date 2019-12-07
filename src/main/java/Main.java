import javafx.application.Application;
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
        int predictedCrimes = PredictCrimeInNextYear.predictTotalCrimes();
        String message = "Total Number of Predicted Crimes in 2020 are "+predictedCrimes;
        button = new Button("Predict Total Number of Crimes in 2020");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        button.setOnAction(e->AlertBox.display("Predicted Crimes Using Linear Regression",message));



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
