package cateringpostres.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * Clase utilitaria para operaciones relacionadas con imágenes.
 * Ofrece métodos para convertir archivos de imagen a bytes y viceversa.
 */
public class ImageUtils {

    /**
     * Convierte un archivo de imagen a un arreglo de bytes.
     *
     * @param file archivo de imagen a convertir
     * @return arreglo de bytes que representa la imagen
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static byte[] imageFileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    /**
     * Convierte un arreglo de bytes a un objeto {@link javafx.scene.image.Image}.
     *
     * @param imageBytes arreglo de bytes que representa la imagen
     * @return imagen de JavaFX creada a partir de los bytes
     */
    public static Image bytesToFxImage(byte[] imageBytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        return new Image(bais);
    }
}
