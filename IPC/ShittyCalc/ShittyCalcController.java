/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xml.internal.txw2.annotation.XmlNamespace;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ShittyCalcController implements Initializable {
    @FXML
    Text valueText, restandoText;
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
        double add =
        if (restando)
    }
}
