package cateringpostres.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * Controller for the home view.
 */
public class AdminDessertController extends TopBarController implements Initializable {

    @FXML
    private Text adminDessertButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        adminDessertButton.setUnderline(true);
    }
}