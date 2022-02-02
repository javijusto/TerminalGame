/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import entorno.*;
import java.awt.Point;
import java.security.SecureRandom;

/**
 *
 * @author javijusto
 */
public class Persona {
    private String nombre;
    private int salud;
    private int energia;
    private Mochila mochila;
    private Point posicion;
    private int arma;
    private int vista;
    private Armadura armadura;
    
    public Persona(String nombre, int x, int y, int efecto){
        setSalud(100);
        setEnergia(100);
        this.nombre = new String();
        setNombre(nombre);
        posicion=new Point(x,y);
        mochila=new Mochila(30,10);
        setArma();
        setVista(2);
        armadura=new Armadura(efecto);
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

    public void setEnergia(int energia) {
        if(energia>=0 && energia<=100)
            this.energia = energia;
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

    public int getEnergia() {
        return energia;
    }

    public void cambiaEnergia(int energia) {
        this.energia+=energia;
        if(this.energia<0){
            this.energia=0;
        }
        if(this.energia>100){
            this.energia=100;
        }
    }
    
    public void reestablecerEnergia(){
        this.energia=100;
    }

    public void meteMochila(Objeto obj){
        mochila.meterObjeto(obj);
    }

    public Objeto sacaMochila(String nombre){
        Objeto obj=mochila.sacarObjeto(nombre);
        return obj;
    }
    
    public void impMochila(){
    mochila.impObjetos();
}
    
    public Point getPosicon(){
        return posicion;
    }

    public void setPosicion(int x, int y) {
        posicion.setLocation(x,y);
    }
    
    public void impPosicion() {
        int x,y;
        x=(int)posicion.getX();
        y=(int)posicion.getY();
        System.out.println("["+(x+1)+","+(y+1)+"]");
    }
    
    public void setArma() {
        SecureRandom rnd = new SecureRandom();
        this.arma = -(rnd.nextInt(6)+20);
    }
    
    public int efectoArma(){
        return arma;
    }

    public int getVista() {
        return vista;
    }

    public void setVista(int vista) {
        if(vista>=2)
            this.vista = vista;
    }

    public void setArmadura(Objeto armadura) {
        this.armadura=new Armadura(armadura.getNombre(),armadura.getEfecto());
    }
    
    public int usarArmadura(int efecto){
        armadura.cambiaSalud(efecto);
        if(armadura.getSalud()<=0){
            if(armadura.getEfecto()!=0){
                System.out.println("La armadura de "+this.nombre+" ha sido destruida");
            }
            armadura.setEfecto(0);
        }
        return armadura.getEfecto();
    }
    
    public Objeto encontrarObjeto(String n){
        Objeto obj=mochila.tengoObjeto(n);
        return obj;
    }
    
    public void activarObjeto(String nombre){
        Objeto obj;
        obj=mochila.tengoObjeto(nombre);
        if(obj!=null){
            switch(obj.getTipo()){
                case "Binocular":
                    setVista(obj.getEfecto());
                    break;
                case "Botiquin":
                    cambiaSalud(obj.getEfecto());
                    break;
                case "Armadura":
                    setArmadura(obj);
                    break;
            }
            mochila.sacarObjeto(obj.getNombre());
        }else{
            System.out.println("Ese objeto no existe");
        }
    }
}
