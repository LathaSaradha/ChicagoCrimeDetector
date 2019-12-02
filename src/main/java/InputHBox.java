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

    private TextField districtText;

    private Button btnMove;

    public InputHBox() {
        setSpacing(10);
        setAlignment(Pos.BOTTOM_CENTER);
        setPadding(new Insets(10, 0, 0, 10));
        createAndAddChildren();
    }

    private void createLabels() {
        this.districtLabel = new Label("District");
        this.districtText = new TextField("District Number");
    }

    private void createTextFields() {
        this.districtText = new TextField();

        this.districtText.setPrefWidth(40);

    }

    private void createMoveButton() {
        this.btnMove = new Button("Find");
    }

    private void createAndAddChildren() {
        createLabels();
        createTextFields();
        createMoveButton();

        getChildren().addAll(this.districtLabel, this.districtText,
                this.btnMove);
    }

    public void addMoveButtonAction(FileReader fr)
    {

        btnMove.setOnAction(new EventHandler<ActionEvent>() {



            @Override
            public void handle(ActionEvent event) {


                CharSequence districtNum =districtText.getCharacters();

                fr.NumberofCrimesInaDistrict(districtNum+"");
            }
        });
    }

}
