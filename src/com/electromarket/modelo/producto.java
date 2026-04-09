package com.electromarket.modelo;

public class Producto {

    private int idProducto;    // solo esta clase puede ver este dato
    private String nombre;     // texto para el nombre
    private String descripcion;// texto para la descripcion
    private double precio;     // decimal para el precio
    private int stock;         // entero para el stock
    private int idCategoria;   // entero para la categoria
    private String estado;     // texto para el estado

    public Producto() {} // constructor vacio

    public Producto(int idProducto, String nombre, String descripcion,
                    double precio, int stock, int idCategoria, String estado) {
        this.idProducto  = idProducto;  // asigna el id
        this.nombre      = nombre;      // asigna el nombre
        this.descripcion = descripcion; // asigna la descripcion
        this.precio      = precio;      // asigna el precio
        this.stock       = stock;       // asigna el stock
        this.idCategoria = idCategoria; // asigna la categoria
        this.estado      = estado;      // asigna el estado
    }

    // getters y setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() { // muestra el producto en pantalla
        return "ID: " + idProducto +
               " | Nombre: " + nombre +
               " | Precio: $" + precio +
               " | Stock: " + stock +
               " | Estado: " + estado;
    } // cierra toString

} // cierra la clase Productola  clase
