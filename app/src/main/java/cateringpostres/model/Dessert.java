package cateringpostres.model;

/**
 * Representa un postre dentro del sistema.
 * Contiene atributos como nombre, descripción, precio e imagen.
 */
public class Dessert {
    private int id;
    private String name;
    private String description;
    private double price;
    private byte[] image;

    /**
     * Constructor para crear un postre sin ID.
     *
     * @param name        nombre del postre
     * @param description descripción del postre
     * @param price       precio del postre
     * @param image       imagen del postre en bytes
     */
    public Dessert(String name, String description, double price, byte[] image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    /**
     * Constructor completo con ID (usado al recuperar datos de la base de datos).
     *
     * @param id          identificador único del postre
     * @param name        nombre del postre
     * @param description descripción del postre
     * @param price       precio del postre
     * @param image       imagen del postre en bytes
     */
    public Dessert(int id, String name, String description, double price, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    /**
     * Obtiene el ID del postre.
     *
     * @return ID del postre
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del postre.
     *
     * @param id nuevo ID del postre
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del postre.
     *
     * @return nombre del postre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del postre.
     *
     * @param name nuevo nombre del postre
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción del postre.
     *
     * @return descripción del postre
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del postre.
     *
     * @param description nueva descripción del postre
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio del postre.
     *
     * @return precio del postre
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio del postre.
     *
     * @param price nuevo precio del postre
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene la imagen del postre.
     *
     * @return imagen del postre como arreglo de bytes
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Establece la imagen del postre.
     *
     * @param image nueva imagen del postre como arreglo de bytes
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}
