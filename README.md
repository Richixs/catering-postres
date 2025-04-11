# 🍰 Catering Postres

Proyecto de catering de postres desarrollado para la materia de Sistemas de Información II.

## 📡 Conexión a la Base de Datos

Para conectar el sistema a la base de datos, es necesario configurar las credenciales en un archivo de propiedades.

### 🔧 Configuración

1. **Crear el archivo de configuración:**  
   Dentro del directorio `src/main/java/cateringpostres/resources/`, cree un archivo llamado **`db.properties`**.

2. **Agregar las siguientes credenciales:**  
   ```properties
   db.url=jdbc:postgresql://url/mi_db
   db.user=usuario
   db.password=contraseña
   ```