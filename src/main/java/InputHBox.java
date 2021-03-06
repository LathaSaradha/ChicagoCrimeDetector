import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class InputHBox extends HBox {
    private Label districtLabel;
    private Label yearLabel;


    private TextField districtText;
    private TextField yearText;

    private Button btnMove;

    public InputHBox() {
        setSpacing(10);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        createAndAddChildren();
    }

    private void createLabels() {
        this.districtLabel = new Label("District");
        this.yearLabel = new Label("Year");
        this.districtText = new TextField("District Number");
        this.yearText = new TextField("Year");
    }

    private void createTextFields() {
        this.districtText = new TextField();
        this.yearText = new TextField();
        this.districtText.setPrefWidth(50);
        this.yearText.setPrefWidth(50);

    }

    private void createMoveButton() {
        this.btnMove = new Button("Find");
    }

    private void createAndAddChildren() {
        createLabels();
        createTextFields();
        createMoveButton();

        getChildren().addAll(this.districtLabel, this.districtText, this.yearLabel, this.yearText,
                this.btnMove);

        addMoveButtonAction();

    }

    public void addMoveButtonAction() {

        btnMove.setOnAction(event -> {
                    double percent;
                    try {
                        CharSequence districtNum = districtText.getCharacters();
                        CharSequence yearNum = yearText.getCharacters();
                        DecimalFormat df = new DecimalFormat("000");
                        double num = Double.parseDouble(districtNum + "");
                        String dNum = df.format(num);
                        int district = Integer.parseInt(df.format(num));
                        int year = Integer.parseInt(yearNum + "");

                        if ( district>=1 && district <= 25 && year >= 2001 && year <= 2019) {
                            percent = CrimePercentageInADistrict.numberOfCrimesInADistrict(dNum, yearNum + "");
                            displayResultDialogueBox(districtNum, yearNum, percent);
                        } else {
                            throw new IllegalArgumentException();
                        }

                    } catch (StringIndexOutOfBoundsException | IllegalArgumentException | NullPointerException s) {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        Label label = new Label("Please enter valid input");
                        label.setWrapText(true);
                        a.getDialogPane().setContent(label);
                        a.show();

                    }

                    districtText.clear();
                    yearText.clear();
                }
        );

    }

    public void displayResultDialogueBox(CharSequence districtNum, CharSequence yearNum, double percent) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button okButton = new Button("OK");

        okButton.setOnAction(arg0 -> dialogStage.close());

        String display = "Percentage of crimes in district " + districtNum + " in year " + yearNum + " is " + new DecimalFormat("#.##").format(percent) + " %";

        VBox vbox = new VBox(new Text(display), okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

}
