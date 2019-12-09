import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public enum PredictionChart
{
    ;

    public static void display()
    { long start=System.currentTimeMillis();
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

        new PredictCrimeInNextYear();
        int predictedIn2020 = PredictCrimeInNextYear.predictTotalCrimes();

        //Removing the checked exception for raw types.

        PredictCrimeInNextYear.getTotalCrimesInYears().
                forEach((key, value) -> dataSeries.getData().add(new XYChart.Data<>(key.toString(), value)));


        dataSeries.getData().add(new XYChart.Data<>("2020 (Predicted)",predictedIn2020));
        barChart.getData().add(dataSeries);


        VBox vBox = new VBox(barChart);
        Scene scene = new Scene(vBox,400,200);
        window.setScene(scene);
        window.setHeight(500);
        window.setWidth(1200);
        long stop=System.currentTimeMillis();
        System.out.println("time to calculate crime prediction "+(stop-start)+" milliseconds");
        window.showAndWait();
    }

    public static void districtPercentage(){

        Stage window = new Stage();
        System.out.println("inside districtPercentage");
    window.setTitle("Input to be entered");

        TextArea area= new TextArea();
        InputHBox hb = new InputHBox();
area.setMaxHeight(25.0);

        area.appendText("Please enter District num between 001 and 025.\n Enter the Year from 2001 to 2019. ");
        /*
        StackPane layout = new StackPane();
        layout.getChildren().addAll(hb,area);


         */
        VBox layout= new VBox();
        layout.getChildren().addAll(area,hb);
        Scene scene = new Scene(layout,400,200);
        window.setScene(scene);
        window.showAndWait();
    }
}