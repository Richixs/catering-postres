package cateringpostres.controller;

import cateringpostres.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador para la barra superior que contiene el logo, 
 * el botón del carrito y el menú de perfil.
 */
public class TopBarController {

    @FXML
    private ImageView logoButton;

    @FXML
    private ImageView cartButton;

    @FXML
    private MenuButton profileMenu;

    /**
     * Inicializa los elementos gráficos de la barra superior,
     * asignando las imágenes correspondientes.
     */
    protected void initializeTopBar() {
        logoButton.setImage(
            new Image(getClass().getResourceAsStream("/assets/topBar/logo.png"))
        );
        cartButton.setImage(
            new Image(getClass().getResourceAsStream("/assets/topBar/cart.png"))
        );
        ImageView profileIcon = new ImageView(
            new Image(getClass().getResourceAsStream("/assets/topBar/profile.png"))
        );
        profileIcon.setFitHeight(32);
        profileIcon.setFitWidth(32);
        profileIcon.setPreserveRatio(true);
        profileMenu.setGraphic(profileIcon);
    }

    @FXML
    private void logoButtonClicked() throws IOException {
        App.setRoot("/view/home");
    }

    @FXML
    private void administratorButton() throws IOException {
        App.setRoot("/view/adminDessert");
    }
}

