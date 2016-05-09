/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase para poder crear coches y almacenarlos en una lista de coches.
 * @author christian begines
 */
public class Coche {
    
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected String color;
    protected Integer año;
    protected Integer precio;
    /**
     * Construcotor del coche.
     * @param matricula Matricula del coche.
     * @param marca Marca del coche.
     * @param modelo Modelo del coche
     * @param color Color del coche
     * @param año Año de fabricacion.
     * @param precio Precio de venta en el coche.
     */
    public Coche(String matricula, String marca, String modelo, String color, int año, int precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.año = año;
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
