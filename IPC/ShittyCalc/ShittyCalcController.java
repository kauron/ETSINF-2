import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ShittyCalcController implements Initializable {
    @FXML
    Text valueText, restandoText;
    @FXML
    TextField editText;
    boolean restando;
    double value;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restando = false;
        value = 0;
    }

    @FXML
    public void numberClick(ActionEvent event) {

    }

    @FXML
    public void restarClick(ActionEvent event) {

    }

    @FXML
    public void sumarClick(ActionEvent event) {
        double add;
        try {
            add = Double.parseDouble(editText.getText());
        } catch (NumberFormatException e) {
            return;
        }
        if (restando) add *= -1;
        value += add;
        valueText.setText(String.valueOf(value));
    }
}
