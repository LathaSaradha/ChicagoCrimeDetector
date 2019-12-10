import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
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
            Stage loaderWindow = null;
                    //System.out.println("inside handle method");

                    try {
                        CharSequence districtNum = districtText.getCharacters();
                        CharSequence yearNum = yearText.getCharacters();
                        DecimalFormat df = new DecimalFormat("000");
                        double num=Double.parseDouble(districtNum+"");
                        String dNum=df.format(num);
                        int district = Integer.parseInt( df.format(num));
                        int year = Integer.parseInt(yearNum + "");
                       // System.out.println(district);
                       // System.out.println(year);
                        //districtNum=num;

                        if ((district <= 25) && year >= 2001 && year <= 2019) {

                             loaderWindow = new Stage();
                            loaderWindow.setTitle("Fetching Data");
                            VBox vBox2 = new VBox();
                            ProgressIndicator progressIndicator = new ProgressIndicator();
                            vBox2.setAlignment(Pos.CENTER);
                            Label label = new Label("Please wait while fetching data from City of Chicago data set");
                            vBox2.getChildren().addAll(progressIndicator,label);
                            Scene scene2 = new Scene(vBox2);
                            loaderWindow.setScene(scene2);
                            loaderWindow.show();
                            
                            
                            percent = PredictCrimeInNextYear.numberofCrimesInaDistrict(dNum, yearNum+"");
                            loaderWindow.close();
                            displayResultDialogueBox(districtNum, yearNum, percent);
                        } else {
                            JFrame f = new JFrame();
                            JOptionPane.showMessageDialog(f, "Invalid Choice. \n");
                        }

                    } catch (StringIndexOutOfBoundsException | IllegalArgumentException |NullPointerException s) {
                        JFrame f = new JFrame();
                        JOptionPane.showMessageDialog(f, "Enter Valid Values. \n");

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
