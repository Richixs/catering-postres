package cateringpostres.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TopBarController {
    @FXML
    private ImageView logoButton;
    @FXML
    private ImageView cartButton;
    @FXML
    private MenuButton profileMenu;

    protected void initializeTopBar() {
        logoButton.setImage(new Image(getClass().getResourceAsStream("/assets/topBar/logo.png")));
        cartButton.setImage(new Image(getClass().getResourceAsStream("/assets/topBar/cart.png")));
        ImageView profileIcon = new ImageView(new Image(getClass().getResourceAsStream("/assets/topBar/profile.png")));
        profileIcon.setFitHeight(32);
        profileIcon.setFitWidth(32);
        profileIcon.setPreserveRatio(true);
        profileMenu.setGraphic(profileIcon);
    }
}
