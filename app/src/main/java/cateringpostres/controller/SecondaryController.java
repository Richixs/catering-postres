package cateringpostres.controller;

import cateringpostres.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for the secondary view.
 */
public class SecondaryController {

    /**
     * Switches to the primary view.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/view/home");
    }
}

