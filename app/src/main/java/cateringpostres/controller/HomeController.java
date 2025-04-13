package cateringpostres.controller;

import cateringpostres.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Controller for the home view.
 */
public class HomeController extends TopBarController implements Initializable {

    @FXML
    private Text homeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        homeButton.setUnderline(true);
    }

    /**
     * Switches to the secondary view.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/view/secondary");
    }

}

