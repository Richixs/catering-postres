package cateringpostres.model.dao;

import cateringpostres.model.DatabaseConnection;
import cateringpostres.model.Dessert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link DessertDao}.
 * Proporciona operaciones para acceder y manipular datos de postres en la base de datos.
 */
public class DessertDaoImpl extends DatabaseConnection implements DessertDao {

    /**
     * Obtiene una lista con todos los postres registrados en la base de datos.
     *
     * @return una lista de objetos {@link Dessert}
     */
    @Override
    public List<Dessert> getList() {
        List<Dessert> dessertList = new ArrayList<Dessert>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                "SELECT * FROM dessert"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dessertList.add(new Dessert(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getBytes("image")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dessertList;
    }

    /**
     * Obtiene un postre a partir de su ID.
     *
     * @param id el ID del postre a buscar
     * @return el objeto {@link Dessert} si se encuentra, de lo contrario {@code null}
     */
    @Override
    public Dessert getById(int id) {
        Dessert dessert = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                "SELECT * FROM dessert WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dessert = new Dessert(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getBytes("image")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dessert;
    }

    /**
     * Agrega un nuevo postre a la base de datos.
     *
     * @param objectT el objeto {@link Dessert} que se desea agregar
     * @return {@code true} si la inserción fue exitosa, de lo contrario {@code false}
     */
    @Override
    public boolean add(Dessert objectT) {
        boolean status = false;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT INTO dessert(name,description,price,image) VALUES(?, ?, ?, ?)"
            );
            preparedStatement.setString(1, objectT.getName());
            preparedStatement.setString(2, objectT.getDescription());
            preparedStatement.setDouble(3, objectT.getPrice());
            preparedStatement.setBytes(4, objectT.getImage());
            status = preparedStatement.executeUpdate() > 0;
            objectT.setId(getIdByDessert(objectT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    /**
     * Método no implementado para actualizar un postre.
     *
     * @param objectT el objeto {@link Dessert} con los datos a actualizar
     * @return siempre lanza {@link UnsupportedOperationException}
     */
    @Override
    public boolean update(Dessert objectT) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Metodo elimina un postre de la base de datos.
     *
     * @param objectT el objeto {@link Dessert} a eliminar
     * @return {@code true} si el delete fue exitoso, de lo contrario {@code false}
     */
    @Override
    public boolean delete(Dessert objectT) {
        int id = getIdByDessert(objectT);
        if (id == -1) {
            return false;
        }
        
        String query = "DELETE FROM dessert WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0; // true si se eliminó al menos una fila
        } catch (Exception e) {
            System.out.println("Error eliminando postre: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el ID de un postre a partir de sus atributos (excepto la imagen).
     *
     * @param dessert el objeto {@link Dessert} del cual se quiere obtener el ID
     * @return el ID si se encuentra en la base de datos, de lo contrario {@code -1}
     */
    @Override
    public int getIdByDessert(Dessert dessert) {
        int id = -1;
        String query = "SELECT id FROM dessert WHERE name = ? AND description = ? AND price = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, dessert.getName());
            preparedStatement.setString(2, dessert.getDescription());
            preparedStatement.setDouble(3, dessert.getPrice());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

}
