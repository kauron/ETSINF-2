package controlador;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class VistaListaControlador implements Initializable {

    private ObservableList<Persona> datos = null; // Colecci�n vinculada a la vista.

    @FXML
    private ListView<Persona> listView;
    @FXML
    private RadioMenuItem menuOrdenReverso;
    @FXML
    private Button borrarButton, editarButton, duplicarButton;
    @FXML
    private Button subirButton, bajarButton;
    final private SimpleBooleanProperty apellidos = new SimpleBooleanProperty(true);

    @FXML
    void addAccion(ActionEvent event) {
        // Codigo para mostrar la ventana de edicion
        try {

            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/VistaEditar.fxml"));
            Parent root = (Parent) cargador.load();

            ((VistaEditarControlador) cargador.getController()).setList(datos);

            Scene scene = new Scene(root);

            Stage ventana2 = new Stage();
            ventana2.setTitle("Insertar registro");
            ventana2.initModality(Modality.WINDOW_MODAL);
            ventana2.setScene(scene);
            ventana2.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void duplicarAccion(ActionEvent event) {
        datos.addAll(listView.getSelectionModel().getSelectedItems());
    }

    @FXML
    void borrarAccion(ActionEvent event) {
        datos.removeAll(listView.getSelectionModel().getSelectedItems());
    }
    
    @FXML
    void editarAccion(ActionEvent event) {
        int i = listView.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/VistaEditar.fxml"));
                Parent root = (Parent) cargador.load();

                ((VistaEditarControlador) cargador.getController()).setList(datos, i);

                Scene scene = new Scene(root);

                Stage ventana2 = new Stage();
                ventana2.setTitle("Editar registro");
                ventana2.initModality(Modality.WINDOW_MODAL);
                ventana2.setScene(scene);
                ventana2.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @FXML
    void mostrarAccion(ActionEvent event) {
        String id = ((Node) event.getSource()).getId();
        apellidos.set(menuOrdenReverso.getId().equals(id));
    }
    
    @FXML
    void ordenarAccion(ActionEvent event) {
        listView.getItems().sort(
                (p1, p2) -> {
                    int res;
                    if (apellidos.get()) {
                        res = p1.getApellidos().compareToIgnoreCase(p2.getApellidos());
                        if (res == 0)
                            res = p1.getNombre().compareToIgnoreCase(p2.getNombre());
                    } else {
                        res = p1.getNombre().compareToIgnoreCase(p2.getNombre());
                        if (res == 0)
                            res = p1.getApellidos().compareToIgnoreCase(p2.getApellidos());
                    }
                    return res;
                }
        );
    }
    
    @FXML
    void moverAccion(ActionEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();
        Persona persona = datos.remove(index);
        if (((Button) event.getSource()).equals(subirButton)) index--;
        else index++;
        datos.add(index, persona);
        listView.getSelectionModel().clearAndSelect(index);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        borrarButton.setDisable(true);
        editarButton.setDisable(true);
        duplicarButton.setDisable(true);
        subirButton.setDisable(true);
        bajarButton.setDisable(true);
        
        ArrayList<Persona> misdatos = new ArrayList<>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        datos = FXCollections.observableArrayList(misdatos);
        listView.setItems(datos); // vinculaci�n entre la vista y el modelo
        
        /* asignar aqui el nuevo estilo de la celda*/
        /*-------------------------------------------------------*/
        listView.setCellFactory(c -> new PersonListCell());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel().getSelectedIndices()
                .addListener((Change<? extends Integer> c) -> {
                    int size = c.getList().size();
                    duplicarButton.setDisable(size == 0);
                    borrarButton.setDisable(size == 0);
                    editarButton.setDisable(size != 1);
                    if (size == 1) {
                        int position = listView.getSelectionModel().getSelectedIndex();
                        subirButton.setDisable(position == 0);
                        bajarButton.setDisable(position == datos.size() - 1);
                    } else {
                        subirButton.setDisable(true);
                        bajarButton.setDisable(true);
                    }
        });
        apellidos.addListener(
                (observer, oldValue, newValue) -> {
                    if (!Objects.equals(newValue, oldValue)) listView.refresh();
                }
        );
    }
    
    /* crear aqui la nueva clase que extiende a ListCell*/
    /*-------------------------------------------------------*/
    class PersonListCell extends ListCell<Persona> {
    @Override
        protected void updateItem(Persona item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) setText(null);
            else 
                if (apellidos.get()) setText(item.getApellidos() + ", " + item.getNombre());
                else setText(item.getNombre() + " " + item.getApellidos());
        }
    }
}