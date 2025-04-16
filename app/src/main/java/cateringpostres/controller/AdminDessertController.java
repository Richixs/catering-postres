package cateringpostres.controller;

import cateringpostres.model.Dessert;
import cateringpostres.util.ImageUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        // Crear un nuevo Stage (ventana)
        Stage formStage = new Stage();
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.setTitle("Agregar nuevo postre");
        // Campos del formulario
        TextField nameField = new TextField();
        nameField.setPromptText("Nombre del postre");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Descripción");

        TextField priceField = new TextField();
        priceField.setPromptText("Precio");

        Label imageLabel = new Label("Ninguna imagen seleccionada");
        Button selectImageButton = new Button("Seleccionar imagen");
        final byte[][] selectedImageBytes = new byte[1][]; // Almacenar imagen

        selectImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar imagen");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(formStage);
            if (selectedFile != null) {
                try {
                    selectedImageBytes[0] = Files.readAllBytes(selectedFile.toPath());
                    imageLabel.setText(selectedFile.getName());
                } catch (IOException ex) {
                    imageLabel.setText("Error al cargar imagen");
                    ex.printStackTrace();
                }
            }
        });

        // Botones de acción
        Button saveButton = new Button("Guardar");
        Button cancelButton = new Button("Cancelar");

        saveButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String description = descriptionArea.getText();
                double price = Double.parseDouble(priceField.getText());

                byte[] image = selectedImageBytes[0];
                if (name.isEmpty() || description.isEmpty() || image == null) {
                    // Validación simple
                    showAlert("Faltan datos", "Por favor completa todos los campos.");
                    return;
                }

                Dessert newDessert = new Dessert(name, description, price, image);
                DataManager.getInstance().addDessert(newDessert);
                loadDesserts(DataManager.getInstance().getDessertList());
                formStage.close();

            } catch (NumberFormatException ex) {
                showAlert("Precio inválido", "Introduce un precio válido (ej: 25.50).");
            }
        });

        cancelButton.setOnAction(e -> formStage.close());

        // Layout del formulario
        VBox layout = new VBox(10,
            new Label("Nombre:"), nameField,
            new Label("Descripción:"), descriptionArea,
            new Label("Precio:"), priceField,
            selectImageButton, imageLabel,
            new HBox(10, saveButton, cancelButton)
        );
        layout.setPadding(new Insets(20));

        formStage.setScene(new Scene(layout, 400, 450));
        formStage.showAndWait();
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
        dessertBox.setMaxWidth(Double.MAX_VALUE);

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
        description.setWrappingWidth(800);

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

        edit.setOnAction(e -> {
            Stage editStage = new Stage();
            editStage.setTitle("Editar Postre");

            // Campos con datos actuales
            TextField nameField = new TextField(dessert.getName());
            TextArea descriptionArea = new TextArea(dessert.getDescription());
            TextField priceField = new TextField(String.valueOf(dessert.getPrice()));

            Button saveButton = new Button("Guardar");
            Button cancelButton = new Button("Cancelar");

            saveButton.setOnAction(event -> {
                // Validaciones simples
                if (nameField.getText().isEmpty() || priceField.getText().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, 
                        "Nombre y precio no pueden estar vacíos"
                    ).showAndWait();
                    return;
                }

                try {
                    // Actualizar el objeto
                    dessert.setName(nameField.getText());
                    dessert.setDescription(descriptionArea.getText());
                    dessert.setPrice(Double.parseDouble(priceField.getText()));

                    // Guardar cambios
                    DataManager.getInstance().updateDessert(dessert);
                    loadDesserts(DataManager.getInstance().getDessertList());

                    editStage.close();
                } catch (NumberFormatException ex) {
                    new Alert(Alert.AlertType.ERROR, "Precio inválido").showAndWait();
                }
            });

            cancelButton.setOnAction(event -> editStage.close());

            HBox.setHgrow(textBox, Priority.ALWAYS);
            HBox.setHgrow(buttonBox, Priority.ALWAYS);
            HBox.setHgrow(dessertBox, Priority.ALWAYS);


            // Layout
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));
            
            grid.add(new Label("Nombre:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Descripción:"), 0, 1);
            grid.add(descriptionArea, 1, 1);
            grid.add(new Label("Precio:"), 0, 2);
            grid.add(priceField, 1, 2);
            
            HBox buttons = new HBox(10, saveButton, cancelButton);
            buttons.setAlignment(Pos.CENTER_RIGHT);
            grid.add(buttons, 1, 3);

            Scene scene = new Scene(grid, 400, 250);
            editStage.setScene(scene);
            editStage.initModality(Modality.APPLICATION_MODAL); // bloquea la ventana principal
            editStage.showAndWait();
        });

        Button delete = new Button("Eliminar");

        delete.setOnAction(e -> {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmar eliminación");
            confirmAlert.setHeaderText("¿Estás seguro que deseas eliminar este postre?");
            confirmAlert.setContentText("Esta acción no se puede deshacer.");
        
            // Mostrar y esperar respuesta del usuario
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response.getButtonData().isDefaultButton()) {
                    DataManager.getInstance().removeDessert(dessert);
                    loadDesserts(DataManager.getInstance().getDessertList());
                }
            });
        });

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
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}