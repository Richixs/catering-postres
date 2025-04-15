package cateringpostres.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cateringpostres.model.Dessert;
import cateringpostres.util.ImageUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DessertController extends TopBarController implements Initializable {
    @FXML
    private Text dessertButton;
    @FXML
    private VBox dessertBox;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        dessertButton.setUnderline(true);
        dessertPostBox();
    }

    private void dessertPostBox() {
        int columns = 4;
        HBox row = null;
        List<Dessert> dessertList = DataManager.getInstance().getDessertList();

        for (int i = 0; i < dessertList.size(); i++) {
            if (i % columns == 0) {
                row = new HBox(20); 
                row.setPadding(new Insets(10));
                row.setAlignment(Pos.TOP_CENTER);
                VBox.setVgrow(row, javafx.scene.layout.Priority.ALWAYS);
                VBox.setMargin(row, new Insets(0));
                dessertBox.getChildren().add(row); 
            }

            VBox dessertVerticalBox = dessertPost(dessertList.get(i));
            HBox.setMargin(dessertVerticalBox, new Insets(0));
            VBox.setVgrow(dessertVerticalBox, javafx.scene.layout.Priority.ALWAYS);
            dessertVerticalBox.setAlignment(Pos.TOP_CENTER);
            row.getChildren().add(dessertVerticalBox);
        }
    }
    
    private VBox dessertPost(Dessert dessert) {
        VBox box = new VBox(5); // Espacio entre elementos
        box.setStyle("-fx-background-color: transparent; -fx-alignment: center; -fx-padding: 10; -fx-background-radius: 12;");
        box.setPrefSize(300, 300);
        box.setMaxSize(300, 300);
        box.setMinSize(300, 300);

        ImageView image = new ImageView(ImageUtils.bytesToFxImage(
            dessert.getImage()
        ));
        image.setFitWidth(300);
        image.setFitHeight(250);
        image.setPreserveRatio(false);

        Text name = new Text(dessert.getName());
        name.setStyle("-fx-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        name.setWrappingWidth(280);
        name.setTextAlignment(TextAlignment.CENTER);

        Text price = new Text(String.format("%.2f $", dessert.getPrice()));
        price.setStyle("-fx-fill: black; -fx-font-size: 14px;");

        box.getChildren().addAll(image, name, price);

        box.setOnMouseClicked(event -> {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle(dessert.getName());

            VBox popupContent = new VBox(10);
            popupContent.setAlignment(Pos.CENTER);
            popupContent.setPadding(new Insets(20));

            ImageView popupImage = new ImageView(ImageUtils.bytesToFxImage(dessert.getImage()));
            popupImage.setFitWidth(200);
            popupImage.setPreserveRatio(true);

            Text popupName = new Text(dessert.getName());
            popupName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            Text popupDescription = new Text(dessert.getDescription());
            popupDescription.setWrappingWidth(250);
            popupDescription.setTextAlignment(TextAlignment.CENTER);

            Text popupPrice = new Text(String.format("%.2f $", dessert.getPrice()));
            popupPrice.setStyle("-fx-font-size: 16px;");

            HBox buttonBox = new HBox(10);
            buttonBox.setAlignment(Pos.CENTER);

            Button closeButton = new Button("Cerrar");
            closeButton.setOnAction(e -> popupStage.close());

            Button addToCartButton = new Button("Añadir al carrito");
            addToCartButton.setOnAction(e -> {
                DataManager.getInstance().addDessertToCart(dessert);
                popupStage.close();
            });

            buttonBox.getChildren().addAll(closeButton, addToCartButton);

            popupContent.getChildren().addAll(popupImage, popupName, popupDescription, popupPrice, buttonBox);

            Scene popupScene = new Scene(popupContent, 300, 400);
            popupStage.setScene(popupScene);
            popupStage.showAndWait();
        });

        return box;
    }
}