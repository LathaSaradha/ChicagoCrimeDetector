import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Map;

public class ArrestsPieChart {
    private ArrayList<PieChart.Data> pieChartData = new ArrayList<>();
    private final int[] totalArrestsInTopFive = new int[1];

    ArrestsPieChart() {
        displaySpinner();
    }

    private void displaySpinner() {
        //Spinner
        Stage loaderWindow = new Stage();
        loaderWindow.setTitle("Fetching Data");
        VBox vBox2 = new VBox();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        vBox2.setAlignment(Pos.CENTER);
        Label label = new Label("Please wait while fetching data from City of Chicago data set");
        vBox2.getChildren().addAll(progressIndicator, label);
        Scene scene2 = new Scene(vBox2);
        loaderWindow.setScene(scene2);
        loaderWindow.initStyle(StageStyle.UNDECORATED);
        loaderWindow.show();


        Thread thread = new Thread(
                () -> {
                    Arrests arrests = new Arrests();

                    totalArrestsInTopFive[0] = arrests.getArrests()
                            .entrySet()
                            .stream()
                            .limit(5)
                            .mapToInt(Map.Entry::getValue)
                            .sum();

                    arrests.getArrests()
                            .entrySet()
                            .stream()
                            .limit(5)
                            .forEach((e) -> pieChartData.add(new PieChart.Data(e.getKey(), e.getValue())));

                }
        );
        thread.start();


        Thread thread1 = new Thread(
                () -> {
                    while (thread.isAlive()) {
                    }

                    Platform.runLater(() -> {
                        //Pie chart
                        loaderWindow.close();
                        pieChart();

                    });
                }
        );
        thread1.start();


    }

    private void pieChart() {
        //Pie Chart
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Arrests In Top Five Crime Types");
        window.setHeight(400);
        window.setWidth(800);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(pieChartData);
        PieChart pieChart = new PieChart(data);
        pieChart.setTitle("Total arrests in top five crimes: " + totalArrestsInTopFive[0]);
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);

        VBox vBox = new VBox(pieChart);
        Scene scene = new Scene(vBox, 600, 300);
        window.setScene(scene);
        window.showAndWait();
    }


}
