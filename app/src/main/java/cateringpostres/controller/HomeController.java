package cateringpostres.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * Controlador para la vista del home.
 * Extiende la funcionalidad de {@link TopBarController} para incluir 
 * elementos de interfaz relacionados con la barra superior.
 */
public class HomeController extends TopBarController implements Initializable {

    @FXML
    private Text homeButton;

    /**
     * Método que se ejecuta al inicializar la vista.
     * Inicializa la barra superior y subraya el texto correspondiente a la vista actual.
     *
     * @param location  ubicación del archivo FXML
     * @param resources recursos utilizados para internacionalización (i18n)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        homeButton.setUnderline(true);
    }
}
