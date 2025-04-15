package cateringpostres.controller;

import cateringpostres.model.Dessert;
import cateringpostres.model.dao.DessertDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar y centralizar el acceso a los datos.
 * Utiliza el patrón Singleton para asegurar que solo exista una instancia  
 * de esta clase durante la ejecución del programa.
 */
public class DataManager {

    private static final DataManager instance = new DataManager();
    private DessertDaoImpl dessertDb = new DessertDaoImpl();
    private List<Dessert> dessertList;
    private List<Dessert> cartList;

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     * Inicializa la lista de postres desde la capa DAO.
     */
    private DataManager() {
        this.dessertList = dessertDb.getList();
        this.cartList = new ArrayList<Dessert>();
    }

    /**
     * Devuelve la instancia única de la clase DataManager.
     *
     * @return instancia de DataManager.
     */
    public static DataManager getInstance() {
        return instance;
    }

    /**
     * Devuelve la lista de postres actualmente almacenada.
     *
     * @return lista de objetos {@link Dessert}.
     */
    public List<Dessert> getDessertList() {
        return this.dessertList;
    }

    /**
     * Agrega un nuevo postre tanto a la base de datos como a la lista en memoria.
     *
     * @param newDessert El objeto {@link Dessert} que se desea agregar.
     */
    public void addDessert(Dessert newDessert) {
        dessertDb.add(newDessert);
        dessertList.add(newDessert);
    }

    /**
     * Elimina un postre de la base de datos y de la lista en memoria.
     *
     * @param dessert El objeto {@link Dessert} que se desea eliminar.
     */
    public void removeDessert(Dessert dessert) {
        dessertDb.delete(dessert);
        dessertList.remove(dessert);
    }

    /**
     * Actualiza la información de un postre en la base de datos y en la lista en memoria.
     *
     * @param dessert El objeto {@link Dessert} que contiene la información actualizada.
     */
    public void updateDessert(Dessert dessert) {
        dessertDb.update(dessert);
        for (Dessert dessertToBeUpdate : dessertList) {
            if (dessertToBeUpdate.getId() == dessert.getId()) {
                dessertToBeUpdate = dessert;
                break;
            }
        }
    }

    public List<Dessert> getCartList() {
        return this.cartList;
    }

    public void addDessertToCart(Dessert dessert) {
        this.cartList.add(dessert);
    }

    public void removeDessertToCart(Dessert dessert) {
        this.cartList.remove(dessert);
    }
}
