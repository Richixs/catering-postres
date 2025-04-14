package cateringpostres.model.dao;

import cateringpostres.model.Dessert;

/**
 * Interfaz específica para operaciones con objetos de tipo {@link Dessert}.
 * Extiende las operaciones CRUD básicas.
 */
public interface DessertDao extends Crud<Dessert> {

    /**
     * Obtiene el ID de un postre específico.
     *
     * @param dessert el objeto {@link Dessert} del cual se desea obtener el ID
     * @return el ID del postre si existe, o -1 si no se encuentra
     */
    public int getIdByDessert(Dessert dessert);
}
