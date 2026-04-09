import com.electromarket.conexion.ConexionDB; //  conectarse a la bd
import com.electromarket.dao.ProductoDAO; // para usar los  crud
import com.electromarket.modelo.Producto; // crera  objetos producto
import java.sql.Connection; // guardar la  conexion
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;// leer  loq ue el usuario escrine en la consola

public class Main { // declarar la clase principal del programa en Java.
    public static void main(String[] args) { // punto inicial que arranca el programa
        Scanner scanner = new Scanner(System.in); // lee todo lo que escribe el usuario en el panel
        Connection conexion = null;// es como tener un cable desconectado (null) que luego enchufas a la base de
                                   // datos para poder trabajar con ella.

        try { // bloque para manejo de errores
            conexion = ConexionDB.obtenerConexion();// Sirve para inicializar la conexión a la base de datos usando un
                                                    // método ya preparado.

            System.out.println("Conexion exitosa a la base de datos.");// si no da errores y conecta nos muestra este
                                                                       // mensaje
            ProductoDAO dao = new ProductoDAO(conexion); // Sirve para crear el objeto que se encargará de hablar con la
                                                         // base de datos sobre productos, usando la conexión que ya
                                                         // abriste.

            boolean continuar = true;// el menu esta activo sigue mostrando opciones al usuario, para que no se
                                     // cierre esto permite que el usuario haga varias operaciones antes de salir
            while (continuar) {
                System.out.println("=== MENÚ ELECTROMARKET ===");// titulo estetico

                System.out.println("1. Insertar producto");// la primera opcion del menu
                System.out.println("2. Ver todos los productos");// segunda opcion del menu
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                int opcion = scanner.nextInt();// lee lo que escribe el usuario en la consola y espera quee escriba un
                                               // numero (1,2,3,4,5)
                switch (opcion) {// abre un bloque switch segun el numero que escoja el usuario hara esto o
                                 // aquello
                    case 1: // entra a case
                        System.out.println("--- Insertar Producto ---");// opcion que muetra que es para insertar un
                                                                        // producto
                        System.out.print("Nombre: ");// escribir el nombre del producto
                        scanner.nextLine();
                        String nombre = scanner.nextLine();// lee loq ue el usuario escribe por completo con line y lo
                                                           // guarda en la variable nombre
                        System.out.print("Descripción: ");// pregunta al usuario que escriba la descripcion del producto
                        String descripcion = scanner.nextLine();// lee lo quw escribe el usuario y lo guarda en la
                                                                // variable
                        System.out.print("Precio: "); // pregunta al usuario que ecriba le precio
                        double precio = scanner.nextDouble();// espera que el usuario escriba un numero decimal
                        System.out.print("Stock: ");// le dice al usuario que escriba la cantidad del producto o
                                                    // unidades del producto
                        int stock = scanner.nextInt();// lee lo que escribe el usuario y lo guarda en la variable stock
                        System.out.println(
                                "Categorías disponibles: 1=Computadores, 2=Accesorios, 3=Smartphones, 4=Alto Rendimiento, 5=Hardware, 6=Software, 7=Consultas");// muestra
                                                                                                                                                                // al
                                                                                                                                                                // usuario
                                                                                                                                                                // administrador
                                                                                                                                                                // las
                                                                                                                                                                // categorias
                        System.out.print("ID Categoría: ");// le dice al usuario que escriba el id de la categoria
                                                           // (1=smartphones, 2=accesorios, 3= computadores etc..)
                        int idCategoria = scanner.nextInt(); // lee lo que el usuario escribe y lo guarda en la variable
                        System.out.print("Estado (activo/inactivo): ");// pidee al usuario que escriba el estado del
                                                                       // producto
                        String estado = scanner.nextLine();// lee lo que el usurio escribe y lo guarda en la variable
                        Producto producto = new Producto(0, nombre, descripcion, precio, stock, idCategoria, estado);// crea
                                                                                                                     // un
                                                                                                                     // nuevo
                                                                                                                     // objeto
                                                                                                                     // en
                                                                                                                     // producto
                                                                                                                     // con
                                                                                                                     // todos
                                                                                                                     // los
                                                                                                                     // datos
                                                                                                                     // el
                                                                                                                     // usuario
                                                                                                                     // escribio
                        dao.insertar(producto);// llama al metodo insertar del DAO pasandole el objeto al producto y
                                               // guardandolo en la bd
                        System.out.println("Producto insertado correctamente.");// muestra un mensaje al usurio de
                                                                                // confrimacion
                        break;// detiene el case 1
                    case 2:// anbre el case 2 si el usuario eligio la opcion 2
                        System.out.println("--- Ver Todos los Productos ---");// titulo
                        List<Producto> productos = dao.consultarTodos();// Llama al método consultarTodos() del DAO, que
                                                                        // consulta todos los productos de la base de
                                                                        // datos y retorna una List<Producto>. Guarda
                                                                        // esa lista en la variable productos.
                        for (Producto p : productos) {// Abre un bucle for-each que recorre cada producto de la lista
                                                      // productos. Por cada iteración, la variable p contiene un
                                                      // producto diferente. Es la forma más simple de recorrer una
                                                      // lista.
                            System.out.println(p);// Imprime el producto p. Usa el método toString() que definimos en la
                                                  // clase Producto para mostrar los datos de forma legible (ID, nombre,
                                                  // precio, stock, estado).
                        }
                        break; // detiene el case 2

                    case 3:// abre el case 3 si el usuario eligio la opcion 3
                        System.out.println("--- Actualizar Producto ---");// titulo

                        System.out.print("ID del producto a actualizar: ");// Pregunta al usuario que escriba el ID del
                                                                           // producto que desea actualizar. El usuario
                                                                           // debe saber qué ID tiene el producto (puede
                                                                           // verlo primero con la opción 2).

                        int idProductoActualizar = scanner.nextInt();// Lee lo que el usuario escribe (el ID del
                                                                     // producto que quiere actualizar) y lo guarda en
                                                                     // la variable idProductoActualizar. nextInt() lee
                                                                     // números enteros.
                        scanner.nextLine();// limpia el buffer
                                                                     System.out.print("Nombre: ");// Pregunta al usuario que escriba el nuevo nombre del producto.
                                                     // Este nombre reemplazará al anterior en la BD.
                        String nombreActualizar = scanner.nextLine();// Lee lo que el usuario escribe (el nuevo nombre
                                                                     // del
                        // producto) y lo guarda en la variable nombre. nextLine()
                        // permite texto completo.
                        System.out.print("Descripción: ");// Pregunta al usuario que escriba la nueva descripción del
                                                          // producto. Esta descripción reemplazará a la anterior en la
                                                          // BD.
                        String descripcionActualizar = scanner.nextLine();// Lee lo que el usuario escribe (la nueva
                                                                          // descripción del producto) y lo guarda en la
                                                                          // variable descripcionActualizar. nextLine()
                                                                          // permite texto completo.
                        System.out.print("Precio: ");// Pregunta al usuario que escriba el nuevo precio del producto.
                                                     // Este precio reemplazará al anterior en la BD.
                        double precioActualizar = scanner.nextDouble();// Lee lo que el usuario escribe (el nuevo precio
                                                                       // del producto) y lo guarda en la variable
                                                                       // precioActualizar. nextDouble() lee números
                                                                       // decimales como 99.99 o 150.50.
                        System.out.print("Stock: ");// Pregunta al usuario que escriba la nueva cantidad de productos
                                                    // disponibles en stock. Este stock reemplazará al anterior en la
                                                    // BD.
                        int stockActualizar = scanner.nextInt();// ee lo que el usuario escribe (la nueva cantidad en
                                                                // stock) y lo guarda en la variable stockActualizar.
                                                                // nextInt() lee números enteros como 50, 100, 25.
                        System.out.println(
                                "Categorías disponibles: 1=Computadores, 2=Accesorios, 3=Smartphones, 4=Alto Rendimiento, 5=Hardware, 6=Software, 7=Consultas");// muestra
                                                                                                                                                                // de
                                                                                                                                                                // nuevo
                                                                                                                                                                // la
                                                                                                                                                                // categorias
                                                                                                                                                                // para
                                                                                                                                                                // que
                                                                                                                                                                // el
                                                                                                                                                                // usuario
                                                                                                                                                                // debe
                                                                                                                                                                // elegir
                                                                                                                                                                // una
                        System.out.print("ID Categoría: ");// Pregunta al usuario que escriba el ID de la nueva
                                                           // categoría. El usuario acaba de ver todas las opciones
                                                           // disponibles.
                        int idCategoriaActualizar = scanner.nextInt();// Lee lo que el usuario escribe (el ID de la
                                                                      // nueva categoría) y lo guarda en la variable
                                                                      // idCategoriaActualizar. nextInt() lee números
                                                                      // enteros del 1 al 7.
                        System.out.print("Estado (activo/inactivo): "); // Pregunta al usuario que escriba el nuevo
                                                                        // estado del producto. El estado puede ser
                                                                        // "activo" o "inactivo".
                        String estadoActualizar = scanner.nextLine();// Lee lo que el usuario escribe (el nuevo estado:
                                                                     // "activo" o "inactivo") y lo guarda en la
                                                                     // variable estadoActualizar. nextLine() permite
                                                                     // texto completo
                        Producto productoActualizar = new Producto(idProductoActualizar, nombreActualizar,
                                descripcionActualizar, precioActualizar, stockActualizar, idCategoriaActualizar,
                                estadoActualizar);// Crea un nuevo objeto Producto con TODOS los datos actualizados que
                                                  // el usuario escribió. El ID del producto es el que el usuario
                                                  // ingresó al principio para saber cuál actualizar.
                        dao.actualizar(productoActualizar);// Llama al método actualizar() del DAO, pasándole el objeto
                                                           // productoActualizar que acabas de crear. Esto actualiza el
                                                           // producto en la base de datos.
                        System.out.println("Producto actualizado correctamente.");// Muestra un mensaje de confirmación
                                                                                  // al usuario indicando que el
                                                                                  // producto se actualizó exitosamente
                                                                                  // en la base de datos.

                        break; // cierra el case 3

                    case 4:// abre el case 4 solo si el usuario escoge la opcion 4
                        System.out.println("--- Eliminar Producto ---");// titulo
                        System.out.print("ID del producto a eliminar: ");// Pregunta al usuario que escriba el ID del
                                                                         // producto que desea eliminar. El usuario debe
                                                                         // saber qué ID tiene el producto (puede verlo
                                                                         // primero con la opción 2).
                        int idProductoEliminar = scanner.nextInt();// lee lo que el usuario escribe el id que el usuario
                                                                   // desea aliminar y lo guarda en la variable
                        dao.eliminar(idProductoEliminar);// llama al metodo eliminar del DAO, lo elimina de la BD
                        System.out.println("Producto eliminado correctamente.");// muestra mensaje de eliminacion
                        break; // cierra el case 4
                

                    case 5: // abre el case 5 si el usuario escogio la opcion 5
                        System.out.println("¡Hasta luego!");// muestra mensaje de despedida  al salir del programa
                        continuar = false; // cambia la variable continuar al false esto detiene el while (continuar)
                        break; // cierra el case 5
                    default: // default es como un "else" en el switch. Si el usuario escribe un número que NO es 1, 2, 3, 4 o 5, entra aquí. Maneja opciones inválidas.
                        System.out.println("Opción inválida. Intenta de nuevo.");// muestra mensaje de error
                    
                }
            }
        } catch (SQLException e) { // bloque que se ejecuta si corre un error " catch", errores relacionados con la
                                   // base de datos en cualquier consulta
            System.out.println("Error de conexion: " + e.getMessage());// Sirve para mostrar en pantalla un mensaje de
                                                                       // error junto con la descripción real del
                                                                       // problema que ocurrió al intentar conectarse a
                                                                       // la base de datos.

        } finally {// bloque que indica haya salido bien o mal, al final haz esto de todas formas.
            ConexionDB.cerrarConexion(conexion);// cierra de forma segura la conexxion a la bd
            scanner.close();// cerrara el lector de entrada
            System.exit(0);// terminar el programa sin errores  ni bugs de cierre limpio
        }
    }

}
