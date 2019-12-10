import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrimeRateInDistrictUI
{
    public static void display()
    {
        Stage window = new Stage();
        window.setTitle("Input to be entered");
        window.initModality(Modality.APPLICATION_MODAL);

        TextArea area= new TextArea();
        InputHBox hb = new InputHBox();
        area.setMaxHeight(25.0);

        area.appendText("Please enter district number between 001 and 025.\n Enter the Year from 2001 to 2019. ");

        VBox layout= new VBox();
        layout.getChildren().addAll(area,hb);
        Scene scene = new Scene(layout,400,200);
        window.setScene(scene);
        window.showAndWait();
    }
}
