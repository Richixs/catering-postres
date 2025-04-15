package cateringpostres.controller;

import cateringpostres.App;
import cateringpostres.model.Dessert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    /**
     * Maneja el evento del botón del logo.
     * Redirige a la vista principal (home).
     */
    @FXML
    private void logoButtonClicked() throws IOException {
        App.setRoot("/view/home");
    }

    /**
     * Maneja el evento del botón de administrador.
     * Redirige a la vista de administración de postres.
     */
    @FXML
    private void administratorButton() throws IOException {
        App.setRoot("/view/adminDessert");
    }

    /**
     * Maneja el evento del botón de inicio.
     * Redirige a la vista principal (home).
     */
    @FXML
    private void homeButtonClicked() throws IOException {
        App.setRoot("/view/home");
    }

    /**
     * Maneja el evento del botón de postres.
     * Redirige a la vista donde se muestran los postres disponibles.
     */
    @FXML
    private void dessertButtonClicked() throws IOException {
        App.setRoot("/view/dessert");
    }

    /**
     * Maneja el evento del botón del carrito.
     * Muestra una ventana emergente con el contenido actual del carrito de postres.
     * Cada postre se muestra con su nombre, precio y un botón para eliminarlo del carrito.
     * También incluye un botón para cerrar la ventana.
     */
    @FXML
    private void cartButtonClicked() {
        Stage cartStage = new Stage();
        cartStage.setTitle("Carrito de Postres");
    
        VBox mainVerticalBox = new VBox(10);
        mainVerticalBox.setPadding(new Insets(15));
    
        // Texto centrado "Carrito"
        Label tituloLabel = new Label("Carrito");
        tituloLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        tituloLabel.setAlignment(Pos.CENTER);
    
        HBox tituloBox = new HBox(tituloLabel);
        tituloBox.setAlignment(Pos.CENTER);
        mainVerticalBox.getChildren().add(tituloBox);
    
        DataManager instance = DataManager.getInstance();
    
        // Label para mostrar el total
        Label totalLabel = new Label("Total: $" + String.format(
            "%.2f",
            getTotalPrice(instance.getCartList())
            ));
        totalLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        HBox totalBox = new HBox(totalLabel);
        totalBox.setAlignment(Pos.CENTER_RIGHT);
    
        // Lista auxiliar de HBox para evitar eliminar el título y total al recorrer
        List<Node> postreNodes = new ArrayList<>();
    
        for (Dessert dessert : new ArrayList<>(instance.getCartList())) {
            HBox dessertBox = new HBox(10);
    
            Label name = new Label(dessert.getName());
            Label price = new Label(String.format("$%.2f", dessert.getPrice()));
            Button deleteButton = new Button("Eliminar");
    
            deleteButton.setOnAction(e -> {
                instance.removeDessertToCart(dessert);
                mainVerticalBox.getChildren().remove(dessertBox);
                postreNodes.remove(dessertBox);
                // Actualizar total
                totalLabel.setText(
                    "Total: $" 
                    + String.format("%.2f", getTotalPrice(instance.getCartList())));
            });
    
            dessertBox.getChildren().addAll(name, price, deleteButton);
            postreNodes.add(dessertBox);
        }
    
        mainVerticalBox.getChildren().addAll(postreNodes);
    
        // Añadir el total antes del botón cerrar
        mainVerticalBox.getChildren().add(totalBox);
    
        // Botón para cerrar
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> cartStage.close());
        mainVerticalBox.getChildren().add(closeButton);
    
        Scene scene = new Scene(mainVerticalBox);
        cartStage.setScene(scene);
        cartStage.initModality(Modality.APPLICATION_MODAL);
        cartStage.show();
    }

    private double getTotalPrice(List<Dessert> cartList) {
        double total = 0.00;
        for (Dessert dessert : cartList) {
            total += dessert.getPrice();
        }
        return total;
    }
    
}

