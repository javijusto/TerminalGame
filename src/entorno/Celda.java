/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Celda {
    private boolean paso;
    private ArrayList<Objeto> objetos;
    
    public Celda(boolean transito){
        setPaso(transito);
        objetos=new ArrayList<>();
    }

    public void setPaso(boolean paso) {
        this.paso = paso;
    }
    
    public boolean getPaso() {
        return paso;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }
    
    public void meterObjeto(Objeto obj) {
        this.objetos.add(obj);
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
        if(objetos.size()>0){
            for(int i=0;i<objetos.size();i++){
                System.out.println((i+1)+":"+objetos.get(i).getNombre()+"\tEfecto:"+objetos.get(i).getEfecto());
            }
        }else
            System.out.println("La celda no contiene nigun objeto");
    }
}