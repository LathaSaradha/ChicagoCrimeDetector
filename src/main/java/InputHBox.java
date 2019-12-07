import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputHBox extends HBox {
    private Label districtLabel;
    private Label YearLabel;


    private TextField districtText;
    private TextField yearText;

    private Button btnMove;

    public InputHBox() {
        setSpacing(10);
        setAlignment(Pos.BOTTOM_CENTER);
        setPadding(new Insets(10, 0, 0, 10));
        createAndAddChildren();
    }

    private void createLabels() {
        this.districtLabel = new Label("District");
        this.YearLabel = new Label("Year");
        this.districtText = new TextField("District Number");
        this.yearText = new TextField("Year");
    }

    private void createTextFields() {
        this.districtText = new TextField();
        this.yearText =new TextField();
       this.districtText.setPrefWidth(40);
        this.yearText.setPrefWidth(40);

    }

    private void createMoveButton() {
        this.btnMove = new Button("Find");
    }

    private void createAndAddChildren() {
        createLabels();
        createTextFields();
        createMoveButton();

        getChildren().addAll(this.districtLabel, this.districtText,this.yearText,this.YearLabel,
                this.btnMove);
    }

    public void addMoveButtonAction(FileReader fr)
    {

        btnMove.setOnAction(new EventHandler<ActionEvent>() {



            @Override
            public void handle(ActionEvent event) {


                CharSequence districtNum =districtText.getCharacters();
                CharSequence yearNum= yearText.getCharacters();

               //PredictCrimeInNextYear.numberofCrimesInaDistrict(districtNum,yearNum);
            }
        });
    }

}
