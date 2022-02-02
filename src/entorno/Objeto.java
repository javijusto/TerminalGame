/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

/**
 *
 * @author Pablo
 */
public class Objeto {
    private String nombre;
    private int peso;
    private String tipo;
    private int efecto;

    public Objeto(String nombre, int peso, String tipo, int efecto) {
        this.nombre = new String();
        setNombre(nombre);
        setPeso(peso);
        this.tipo = new String();
        setTipo(tipo);
        setEfecto(efecto);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        if(peso>0)
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }    
}
