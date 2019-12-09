import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum ArrestsPieChart {
    ;

    public static void display() {
        long start=System.currentTimeMillis();
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Total Arrests for each Crime Type");
        List<PieChart.Data> pieChartData = new ArrayList<>();

        Arrests arrests = new Arrests();
        int totalArrestsInTopFive =  arrests.getArrests()
                .entrySet()
                .stream()
                .limit(5)
                .mapToInt(Map.Entry::getValue)
                .sum();

        arrests.getArrests()
                .entrySet()
                .stream()
                .limit(5)
                .forEach((e) -> {
                    System.out.println(e);
                    pieChartData.add(new PieChart.Data(e.getKey(),e.getValue()));
        });

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(pieChartData);
        PieChart pieChart = new PieChart(data);
        pieChart.setTitle("Total Arrests: "+totalArrestsInTopFive);
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);

        VBox vBox = new VBox(pieChart);
        Scene scene = new Scene(vBox,600,300);
        window.setScene(scene);
        window.setTitle("Arrests In Top Five Crime Types");
        window.setHeight(400);
        window.setWidth(800);

        long stop=System.currentTimeMillis();
        System.out.println("time to calculate Arrests "+(stop-start)+" milliseconds");

        window.showAndWait();

    }
}
