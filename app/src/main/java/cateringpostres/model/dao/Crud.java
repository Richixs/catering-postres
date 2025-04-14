package cateringpostres.model.dao;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD básicas.
 *
 * @param <T> el tipo de objeto sobre el que se realizarán las operaciones
 */
public interface Crud<T> {
    
    /**
     * Obtiene una lista de todos los objetos del tipo T.
     *
     * @return una lista con todos los objetos encontrados
     */
    public List<T> getList();

    /**
     * Busca un objeto por su ID.
     *
     * @param id el identificador del objeto a buscar
     * @return el objeto correspondiente al ID, o null si no se encuentra
     */
    public T getById(int id);

    /**
     * Agrega un nuevo objeto.
     *
     * @param objectT el objeto a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean add(T objectT);

    /**
     * Actualiza un objeto existente.
     *
     * @param objectT el objeto con los nuevos datos
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean update(T objectT);

    /**
     * Elimina un objeto.
     *
     * @param objectT el objeto a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(T objectT);
}
