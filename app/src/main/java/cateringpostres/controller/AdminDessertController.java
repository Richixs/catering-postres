package cateringpostres.controller;

import cateringpostres.model.Dessert;
import cateringpostres.util.ImageUtils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Controlador para la vista de administración de postres.
 * Extiende la funcionalidad de {@link TopBarController} para incluir 
 * elementos de interfaz relacionados con la barra superior.
 */
public class AdminDessertController extends TopBarController implements Initializable {

    @FXML
    private Text adminDessertButton;
    @FXML
    private VBox dessertAdminVerticalBox;

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
        adminDessertButton.setUnderline(true);
        loadDesserts(DataManager.getInstance().getDessertList());
    }

    /**
     * Acción invocada al presionar el botón "Agregar postre".
     */ 
    @FXML
    private void addDessertAction() {
        // Método para agregar un nuevo postre (pendiente de implementación)
    }

    /**
     * Carga y muestra todos los postres en la interfaz gráfica.
     *
     * @param dessertList Lista de objetos {@link Dessert} a mostrar.
     */
    private void loadDesserts(List<Dessert> dessertList) {
        dessertAdminVerticalBox.getChildren().clear();
        for (Dessert dessert : dessertList) {
            addDessert(dessert);
        }
    }

    /**
     * Agrega un componente visual (HBox) representando un postre a la vista.
     * Incluye imagen, nombre, descripción, precio y botones de acción.
     *
     * @param dessert Objeto {@link Dessert} que se desea mostrar.
     */
    private void addDessert(Dessert dessert) {
        HBox dessertBox = new HBox(18);
        dessertBox.setAlignment(Pos.CENTER);
        dessertBox.setPadding(new Insets(30, 15, 30, 15));
        dessertBox.setMinHeight(230);
        dessertBox.setMaxHeight(230);
        dessertBox.setPrefHeight(100);
        dessertBox.setPrefWidth(200);

        // Imagen del postre
        ImageView imageView = new ImageView(ImageUtils.bytesToFxImage(
            dessert.getImage()
        ));
        imageView.setFitHeight(170);
        imageView.setFitWidth(160);
        imageView.setPreserveRatio(true);
        HBox.setHgrow(imageView, Priority.ALWAYS);

        // Caja de texto con nombre, descripción y precio
        VBox textBox = new VBox();
        textBox.setPrefHeight(200);
        textBox.setPrefWidth(100);
        HBox.setHgrow(textBox, Priority.ALWAYS);

        Text name = new Text(dessert.getName());
        name.setFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 20));
        VBox.setVgrow(name, Priority.ALWAYS);

        Text description = new Text(dessert.getDescription());
        description.setFill(Color.WHITE);
        description.setFont(Font.font(16));

        Text price = new Text(String.valueOf(dessert.getPrice()));
        price.setFill(Color.WHITE);
        price.setFont(Font.font(16));
        VBox.setVgrow(price, Priority.ALWAYS);

        Region regionText1 = new Region();
        Region regionText2 = new Region();
        regionText1.setMaxHeight(20);
        regionText2.setPrefHeight(200);

        textBox.getChildren().addAll(name, regionText1, description, regionText2, price);

        // Botones de editar y eliminar
        HBox buttonBox = new HBox(14);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPrefHeight(100);
        buttonBox.setPrefWidth(200);

        Button edit = new Button("Editar");
        Button delete = new Button("Eliminar");

        buttonBox.getChildren().addAll(edit, delete);

        // Se agregan los elementos al contenedor del postre
        dessertBox.getChildren().addAll(imageView, textBox, buttonBox);

        // Línea divisoria entre elementos
        Pane line = new Pane();
        line.setMinHeight(2);
        line.setMaxHeight(2);
        line.setStyle("-fx-background-color: #8B5E3C;");

        // Se agrega el postre y la línea a la vista principal
        dessertAdminVerticalBox.getChildren().addAll(dessertBox, line);
    }
}