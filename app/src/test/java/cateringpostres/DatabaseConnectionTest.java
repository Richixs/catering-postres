package cateringpostres;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import cateringpostres.model.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DatabaseConnectionTest {

    @Test
    void testConexionExitosa() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn, "La conexión no debería ser null");
            assertFalse(conn.isClosed(), "La conexión debería estar abierta");
        } catch (SQLException e) {
            fail("No se pudo establecer la conexión: " + e.getMessage());
        }
    }
}

