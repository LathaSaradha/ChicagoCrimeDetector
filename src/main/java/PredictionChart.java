import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class PredictionChart
{
    public static void display()
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crimes in Different Years");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("YEAR");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Crimes");

        BarChart barChart = new BarChart(xAxis,yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Total Crimes in Year");
        int predictedIn2020 = PredictCrimeInNextYear.predictTotalCrimes();
        for(Map.Entry<Integer,Integer> pair : PredictCrimeInNextYear.getTotalCrimesInYears().entrySet())
        {
            dataSeries.getData().add(new XYChart.Data(pair.getKey().toString(),pair.getValue()));
        }
        dataSeries.getData().add(new XYChart.Data("2020 (Predicted)",predictedIn2020));
        barChart.getData().add(dataSeries);

        VBox vBox = new VBox(barChart);
        Scene scene = new Scene(vBox,400,200);
        window.setScene(scene);
        window.setHeight(500);
        window.setWidth(1200);
        window.showAndWait();
    }
}
