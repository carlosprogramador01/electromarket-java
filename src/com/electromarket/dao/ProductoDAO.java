package com.electromarket.dao; // dice a java que esta clase vive en el paquete dao

import java.sql.Connection; // importa las herramientas para la conexxion de base de datos
import java.sql.PreparedStatement; // importa las herramientas que ejcutan sql de forma segura
import java.sql.ResultSet; // importa las herramientas que  guarda los resultados  y devuelve la base de datos cuando consultamos
import java.sql.SQLException; // importa la herramienta que captura errores en la base  de datos 
import java.util.ArrayList; // importa las heraamientas para crera una lista dinamica donde guarda los productos
import java.util.List; // importa la interfaz list  que  es el tipo de  datos que usamos para declarar la lista

import com.electromarket.modelo.Producto; // importa la clase producto

public class ProductoDAO { // declara la clase "public" cualquier clase puede usarla
    private Connection conexion; // crear conexxion del DAO que solo esta  clase  puedo verlo y usarlo

    public ProductoDAO(Connection conexion) { // declara que el contructor recibe una conexion como parametro cada vez que se crea un objeto
        this.conexion = conexion; // guarda la conexion en el atributo de la clase this 
    }

    public void insertar(Producto producto) throws SQLException { // Declara el método. void significa que no devuelve nada. throws SQLException significa que puede lanzar un error de base de datos.
        String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, id_categoria, estado) VALUES (?, ?, ?, ?, ?, ?)"; // Plantilla SQL para insertar un producto con valores dinámicos


        PreparedStatement ps = conexion.prepareStatement(sql); // Le entrega la consulta SQL a la conexión para que la prepare y la deje lista para ejecutar.
        ps.setString(1, producto.getNombre()); // reemplaza el primer ? con el nombre del producto setstring por que es texto
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, producto.getStock());
        ps.setInt(5, producto.getIdCategoria());
        ps.setString(6, producto.getEstado());
        ps.executeUpdate(); // Ejecuta la consulta en la base de datos. Se usa executeUpdate para INSERT, UPDATE y DELETE.
        ps.close(); // es el cierre para liberar recursos eviat figas de memoria, cierra el ciclo de uso de este recurso
    }

    public List<Producto> consultarTodos() throws SQLException { // declara el metodo, devuelve una list de objetos productos
        List<Producto> lista = new ArrayList<>(); // crea una lista vacia, donde  van guardando  los productos que traiga db
        String sql = "SELECT * FROM productos"; // prepara la instrucción para leer toda la información de la tabla  en la base de datos.
        PreparedStatement ps = conexion.prepareStatement(sql); // prepara la consulta para ejecutarla
        ResultSet rs = ps.executeQuery();// ejecuta la consulta y guarda los resultados en rs se usa executeQuery para SELECT
        while (rs.next()) { // recorre  los resultados fila por  fila 
            Producto p = new Producto(); // Crea un objeto Producto vacío para llenarlo con los datos de cada fila.
            p.setIdProducto(rs.getInt("id_producto"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setEstado(rs.getString("estado"));
            // cada linea  toma un valor de una columna de la bd y las guarda dentro del objeto producto usando el metodo set
            lista.add(p); //  agrega el producto completo a la lista
        }
        rs.close(); //  cierra el cilo rs para liberar consumo cuando no s e use (liberar recursosos)
        ps.close(); // cierra cilo de ps para liberar consumo si no se usa (liberar recursosos)
        return lista; // devuleve la lista  con todos los productos
    }

    public void actualizar(Producto producto) throws SQLException { // Este método es la parte Update del CRUD. Sirve para actualizar registros que ya fueron creados 
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, id_categoria=?, estado=? WHERE id_producto=?"; // La consulta SQL para modificar. SET indica qué columnas cambian. WHERE indica cuál registro.( devuelve un numero que indica los datos que fueron actualizados)
        PreparedStatement ps = conexion.prepareStatement(sql); // con la conexión a la base de datos, prepara la consulta SQL y guárdala en la variable  para poder usarla después
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, producto.getStock());
        ps.setInt(5, producto.getIdCategoria());
        ps.setString(6, producto.getEstado());
        ps.setInt(7, producto.getIdProducto());
        ps.executeUpdate(); // ejhecuta el bortrado de la bd
        ps.close(); // libera los recurosos

    }

    public void eliminar(int idProducto) throws SQLException { // recibe solo el id del objeto para saber cual borrar
        String sql = "DELETE FROM productos WHERE id_producto=?"; //  borrar  un producto espesifico en la db
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idProducto);
        ps.executeUpdate(); // ejecuta borrado de la bd
        ps.close(); // libera  recurosos 
    }

}
