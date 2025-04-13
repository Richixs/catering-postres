package cateringpostres.controller;

import cateringpostres.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for the home view.
 */
public class HomeController {

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

