import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        valueText.setText("0.0");
    }

    @FXML
    public void numberClick(ActionEvent event) {
        sumar(Integer.valueOf(((Button) event.getSource()).getText()));
    }

    @FXML
    public void restarClick(ActionEvent event) {
        restando = ((CheckBox) event.getSource()).isSelected();
        restandoText.setVisible(restando);
    }

    @FXML
    public void sumarClick(ActionEvent event) {
        double add;
        try {
            add = Double.parseDouble(editText.getText());
        } catch (NumberFormatException e) {
            //TODO: show error message
            return;
        }
        sumar(add);
    }

    private void sumar(double add) {
        if (restando) add *= -1;
        value += add;
        valueText.setText(String.valueOf(value));
        editText.setText("");
    }
}
