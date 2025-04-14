package cateringpostres.controller;

import cateringpostres.model.Dessert;
import cateringpostres.model.dao.DessertDaoImpl;
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

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     * Inicializa la lista de postres desde la capa DAO.
     */
    private DataManager() {
        this.dessertList = dessertDb.getList();
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

    public void addDessert(Dessert newDessert) {
        dessertDb.add(newDessert);
        dessertList.add(newDessert);
    }

    public void removeDessert(Dessert dessert) {
        dessertDb.delete(dessert);
        dessertList.remove(dessert);
    }
}
