/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Producto {
    private int id;
    private String nombre;
    private String codigo;
    private String tipo;
    private int cantidad;
    private double precio;
    private boolean disponibilidad;
    
  
    public Producto(int id, String nombre, String codigo, String tipo, int cantidad, double precio, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public Producto(String nombre, String codigo, String tipo, int cantidad, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(String nombre, String codigo, String tipo, int cantidad, double precio, boolean disponibilidad) {
        this.codigo=codigo;        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }
    

    public Producto() {
    }
        
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
    
    
    
}
