/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author daw1
 */
public class Coche {
    
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected String color;
    protected int año;
    protected int precio;

    public Coche(String matricula, String marca, String modelo, String color, int año, int precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.año = año;
        this.precio = precio;
    }
    public Coche(){
        
    }
}
