/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import java.security.SecureRandom;

/**
 *
 * @author pablo
 */
public class Armadura {
    private String nombre;
    private int salud;
    private int efecto;

    public Armadura(int efecto) {
        this.nombre = new String();
        SecureRandom rnd = new SecureRandom();
        setNombre("Armadura"+(rnd.nextInt(100)+1));
        setSalud(100);
        setEfecto(efecto);
    }
    
    public Armadura(String nombre,int efecto){
       this.nombre = new String();
       setNombre(nombre);
       setSalud(100);
       setEfecto(efecto); 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if(salud>=0 && salud<=100)
            this.salud = salud;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        if(efecto>0)
            this.efecto = efecto;
    }
    
    public void cambiaSalud(int salud) {
        this.salud+=salud;
        if(this.salud<0){
            this.salud=0;
        }
        if(this.salud>100){
            this.salud=100;
        }
    }
}
