import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PredictionChart
{
    public static void display()
    {
        //Spinner
        Stage loaderWindow = new Stage();
        loaderWindow.setTitle("Fetching Data");
        VBox vBox2 = new VBox();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        vBox2.setAlignment(Pos.CENTER);
        Label label = new Label("Please wait while fetching data from City of Chicago data set");
        vBox2.getChildren().addAll(progressIndicator,label);
        Scene scene2 = new Scene(vBox2);
        loaderWindow.setScene(scene2);
        loaderWindow.initStyle(StageStyle.UNDECORATED);
        loaderWindow.show();

        //Bar Graph
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crimes in Different Years");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("YEAR");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Crimes");
        BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String,Number>  dataSeries = new XYChart.Series<>();
        dataSeries.setName("Total Crimes in Year");
        VBox vBox = new VBox(barChart);
        Scene scene = new Scene(vBox,400,200);
        window.setScene(scene);
        window.setHeight(500);
        window.setWidth(1200);

        //Non-javaFX thread - Generates necessary data
        Thread thread = new Thread(()->
        {
            new PredictCrimeInNextYear();
            int predictedIn2020 = PredictCrimeInNextYear.predictTotalCrimes();
            PredictCrimeInNextYear.getTotalCrimesInYears().
                    forEach((key, value) -> dataSeries.getData().add(new XYChart.Data<>(key.toString(), value)));
            dataSeries.getData().add(new XYChart.Data<>("2020 (Predicted)",predictedIn2020));
            barChart.getData().add(dataSeries);
        });
       thread.start();

       //Non javafx thread to check if data collection is completed
       Thread thread1 = new Thread(
               ()->
               {
                   while (thread.isAlive())
                   {
                    // Stay in loop while collecting data
                   }
                   Platform.runLater(()->{window.show();loaderWindow.close();}); //Collecting finished - Show bar graph and close spinner
               }
       );
      thread1.start();
    }
}