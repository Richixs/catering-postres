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
    private List<Dessert> dessertList;

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     * Inicializa la lista de postres desde la capa DAO.
     */
    private DataManager() {
        DessertDaoImpl dessertDb = new DessertDaoImpl();
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
}
