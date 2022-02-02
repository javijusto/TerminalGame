/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import entorno.Objeto;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Mochila {
    private ArrayList <Objeto> objetos;
    private int peso;
    private int cantidad;

    public Mochila(int peso, int cantidad) {
        this.objetos=new ArrayList<>();
        setPeso(peso);
        setCantidad(cantidad);
    }

    
    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        if(peso>0)
            this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if(cantidad>0)
            this.cantidad = cantidad;
    }
    
    public boolean meterObjeto(Objeto obj) {
        int p=0;
        boolean res=false;
        if(cantidad<20){
            for(int i=0;i<objetos.size();i++){
                p+=objetos.get(i).getPeso();
            }
            if((p+obj.getPeso())<=peso){
                objetos.add(obj);
                res=true;
            }
        }
        return res;
    }
    
    public Objeto sacarObjeto(String nombre) {
        Objeto obj=null;
        for(int i=0;i<objetos.size();i++){
            if(nombre.equals(objetos.get(i).getNombre())){
                obj=objetos.get(i);
                objetos.remove(i);
                break;
            }
        }
        return obj;
    }
    public void impObjetos(){
        int p=0;
        for(int i=0;i<objetos.size();i++){
            p+=objetos.get(i).getPeso();
        }
        System.out.println("##MOCHILA##");
        System.out.println("Peso Maximo:"+this.peso+"\tPeso Actual:"+p);
        for(int i=0;i<objetos.size();i++){
            System.out.println(""+(i+1)+":"+objetos.get(i).getNombre()+" Efecto:"+this.objetos.get(i).getEfecto());
        }
    }
    
    Objeto tengoObjeto(String nombreobj){
        int i;
        boolean encontrado=false;
        for(i=0;i<objetos.size();i++){
            if(objetos.get(i).getNombre().equals(nombreobj)){
                encontrado=true;
                break;
            }
        }
        if(encontrado)
            return this.objetos.get(i);
        else
            return null;
    }   
}
