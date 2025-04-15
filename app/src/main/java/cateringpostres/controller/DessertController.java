package cateringpostres.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class DessertController extends TopBarController implements Initializable {
    @FXML
    private Text dessertButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        dessertButton.setUnderline(true);
    }
    
}